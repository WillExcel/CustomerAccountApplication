package personal.account.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import personal.account.api.models.Account;
import personal.account.api.services.AccountService;

@RestController
public class AccountController {
    @Autowired
    AccountService accountService;

    @RequestMapping(path ="/add-account/{id}", method = RequestMethod.POST)
    public ResponseEntity addAccount(@PathVariable(value = "id") Long id, @RequestBody Account account) {
        return accountService.addAccount(id,account);
    }

    @RequestMapping(path ="/update-account/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateAccount(@PathVariable(value = "id") Long id, @RequestBody Account account) {
        return accountService.updateAccount(id,account);
    }

    @RequestMapping(path ="/delete-account/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAccount(@PathVariable(value = "id") Long id) {
        return accountService.deleteAccountById(id);
    }

    @RequestMapping(path ="/account/{id}", method = RequestMethod.GET)
    public ResponseEntity getAccountByPersonalId(@PathVariable(value = "id") Long id) {
        return accountService.getAccountById(id);
    }
}
