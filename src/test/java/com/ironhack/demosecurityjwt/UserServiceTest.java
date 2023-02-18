/*package com.ironhack.demosecurityjwt;

import com.ironhack.demosecurityjwt.dtos.CreateThirdPartyAccountDTO;
import com.ironhack.demosecurityjwt.dtos.NewBankUserResponseDTO;
import com.ironhack.demosecurityjwt.repositories.ThirdPartyRepository;
import com.ironhack.demosecurityjwt.services.impl.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private ThirdPartyRepository thirdPartyRepository;

    @Test
    void newThirdParty_shouldReturnNewThirdParty() {
        // Create a new ThirdParty DTO
        CreateThirdPartyAccountDTO thirdPartyDTO = new CreateThirdPartyAccountDTO();
        thirdPartyDTO.setName("Test ThirdParty");
        thirdPartyDTO.setUsername("test_thirdparty");
        thirdPartyDTO.setPassword("test_password");
        thirdPartyDTO.setHashKey("test_hashed_key");

        // Call the service method to create a new ThirdParty
        CreateThirdPartyAccountDTO newThirdParty = userService.newThirdParty(thirdPartyDTO);

        // Check that the ThirdParty was created and returned correctly
        assertNotNull(newThirdParty);
        assertNotNull(newThirdParty.getId());
        assertEquals(thirdPartyDTO.getName(), newThirdParty.);
        assertEquals(thirdPartyDTO.getUsername(), newThirdParty.getUsername());
        assertEquals(BigDecimal.ZERO, newThirdParty.getBalance());

        // Check that the ThirdParty was saved to the database
        assertTrue(thirdPartyRepository.existsById(newThirdParty.getId()));
    }

}


 */