package com.ironhack.demosecurityjwt.services.impl;

import com.ironhack.demosecurityjwt.Enums.AdminModifyBalanceType;
import com.ironhack.demosecurityjwt.dtos.*;
import com.ironhack.demosecurityjwt.models.Accounts.BaseCheckingAccount;
import com.ironhack.demosecurityjwt.models.Accounts.CheckingAccount;
import com.ironhack.demosecurityjwt.models.Address.Address;
import com.ironhack.demosecurityjwt.models.BankRoles.AccountHolder;
import com.ironhack.demosecurityjwt.models.BankRoles.ThirdParty;
import com.ironhack.demosecurityjwt.models.Money.Money;
import com.ironhack.demosecurityjwt.models.Role;
import com.ironhack.demosecurityjwt.models.Users.User;

import com.ironhack.demosecurityjwt.repositories.*;
import com.ironhack.demosecurityjwt.services.interfaces.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserServiceInterface, UserDetailsService {

    /**
     * Autowired UserRepository for database operations.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Autowired RoleRepository for database operations.
     */
    @Autowired
    private RoleRepository roleRepository;

    /**
     * Injects a bean of type PasswordEncoder into this class.
     * The bean is used for encoding passwords before storing them.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;
    @Autowired
    CheckingAccountRepository checkingAccountRepository;
    @Autowired
    BaseCheckingAccountRepository baseCheckingAccountRepository;

    /**
     * Loads the user by its username
     *
     * @param username the username to search for
     * @return the UserDetails object that matches the given username
     * @throws UsernameNotFoundException if the user with the given username is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retrieve user with the given username
        User user = userRepository.findByUsername(username);
        // Check if user exists
        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
            // Create a collection of SimpleGrantedAuthority objects from the user's roles
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });
            // Return the user details, including the username, password, and authorities
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }
    }

    /**
     * Saves a new user to the database
     *
     * @param user the user to be saved
     * @return the saved user
     */
    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the database", user.getName());
        // Encode the user's password for security before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Saves a new role to the database
     *
     * @param role the role to be saved
     * @return the saved role
     */
    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    /**
     * Adds a role to the user with the given username
     *
     * @param username the username of the user to add the role to
     * @param roleName the name of the role to be added
     */
    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);

        // Retrieve the user and role objects from the repository
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);

        // Add the role to the user's role collection
        user.getRoles().add(role);

        // Save the user to persist the changes
        userRepository.save(user);
    }

    /**
     * Retrieves the user with the given username
     *
     * @param username the username to search for
     * @return the user with the given username
     */
    @Override
    public User getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepository.findByUsername(username);
    }

    /**
     * Retrieves all users from the database
     *
     * @return a list of all users
     */
    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }
    //New AccountHolder From 0
    public NewBankUserResponseDTO newAccountHolder(CreateCheckingAccountDTO checkingAccountDTO){
        Address address = new Address(
                checkingAccountDTO.getAddressDTO().getStreet(),
                checkingAccountDTO.getAddressDTO().getCity(),
                checkingAccountDTO.getAddressDTO().getCountry(),
                checkingAccountDTO.getAddressDTO().getZipCode()
        );
        addressRepository.save(address);
        AccountHolder accountHolder = new AccountHolder(
                null,
                checkingAccountDTO.getName(),
                checkingAccountDTO.getUsername(),
                passwordEncoder.encode(checkingAccountDTO.getPassword()),
                new ArrayList<>(),
                checkingAccountDTO.getDateOfBirth(),
                address,
                address
        );
        accountHolder = accountHolderRepository.save(accountHolder);
        addRoleToUser(accountHolder.getUsername(),"ROLE_ACCOUNT_HOLDER");

        NewBankUserResponseDTO newBankUserResponseDTO = new NewBankUserResponseDTO();
        newBankUserResponseDTO.setId(accountHolder.getId());
        newBankUserResponseDTO.setUserType("ACCOUNT_HOLDER");
        return  newBankUserResponseDTO;
    }
// New ThirdParty
    public NewBankUserResponseDTO newThirdParty(CreateThirdPartyAccountDTO thirdPartyAccountDTO){

        ThirdParty thirdParty = new ThirdParty(
                null,
                thirdPartyAccountDTO.getName(),
                thirdPartyAccountDTO.getUsername(),
                passwordEncoder.encode(thirdPartyAccountDTO.getPassword()),
                new ArrayList<>(),
                passwordEncoder.encode(thirdPartyAccountDTO.getHashKey())
        );
        thirdParty = thirdPartyRepository.save(thirdParty);
        addRoleToUser(thirdParty.getUsername(),"ROLE_THIRD_PARTY");

        NewBankUserResponseDTO newBankUserResponseDTO = new NewBankUserResponseDTO();
        newBankUserResponseDTO.setId(thirdParty.getId());
        newBankUserResponseDTO.setUserType("THIRD_PARTY");
        return  newBankUserResponseDTO;
    }

    public List<ThirdParty> getThirdParty() {
        log.info("Fetching all users");
        return thirdPartyRepository.findAll();
    }

   // Update Balance

    public BaseCheckingAccount modifyBalanceOfAccount (ModifyBalanceDTO modifyBalanceDTO, AdminModifyBalanceType modifyBalanceType){
        BaseCheckingAccount account = baseCheckingAccountRepository.findById(modifyBalanceDTO.getAccountId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"Account not found"));

        Money amount = new Money(new BigDecimal(modifyBalanceDTO.getAmount()));

        switch (modifyBalanceType){
            case INCREASE_AMOUNT:
                account.getBalance().increaseAmount(amount);
                break;
            case DECREASE_AMOUNT:
                if (account.getBalance().compareTo(amount.getAmount()) < 0 ){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Insufficient balance in the account");
                }
            account.getBalance().decreaseAmount(amount);
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid Option");
        }
            return baseCheckingAccountRepository.save(account);
    }


    //Delete
    public DeleteResponseDTO deleteCheckingAccount(Long id) {
        CheckingAccount checkingAccountToDelete  = checkingAccountRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Checking Account" + id+ " not found"));

        if(checkingAccountRepository.findById(id).isPresent()){
            checkingAccountRepository.delete(checkingAccountToDelete);
        }
        DeleteResponseDTO deleteResponseDTO = new DeleteResponseDTO();
        deleteResponseDTO.setAccountId("This Account, whit id number:"+ id);
        deleteResponseDTO.setMessage("Deleted");
        return deleteResponseDTO;
    }


}
