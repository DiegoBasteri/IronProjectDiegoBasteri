package com.ironhack.demosecurityjwt.controllers.impl;

import com.ironhack.demosecurityjwt.dtos.ThirdPartyTransactionDTO;
import com.ironhack.demosecurityjwt.dtos.TransactionResponseDTO;
import com.ironhack.demosecurityjwt.services.impl.ThirdPartyService;
import com.ironhack.demosecurityjwt.services.impl.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ThirdParty")
public class ThirdPartyController {
    @Autowired
    TransactionService transactionService;
    @Autowired
    ThirdPartyService thirdPartyService;

    @PostMapping("/newTransaction")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TransactionResponseDTO newThirdPartyTransaction(@RequestBody ThirdPartyTransactionDTO thirdPartyTransactionDTO){
        return thirdPartyService.createTransaction(thirdPartyTransactionDTO);
    }

}
