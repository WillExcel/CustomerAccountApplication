package personal.account.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import personal.account.api.models.Account;
import personal.account.api.models.Personal;
import personal.account.api.repositories.PersonalRepository;

import java.time.LocalDate;
import java.util.LinkedHashMap;

@Service
public class AccountService {
    @Autowired
    PersonalRepository personalRepository;

    public ResponseEntity addAccount(Long id, Account account){
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        if(account !=null && id != null) {
            Personal personal = personalRepository.findById(id).get();
            if(personal != null){
                String accountCode = Account.getAccountCodePrefix()+account.getAccountCode();
                String accountNumber = Account.getAccountNoPrefix()+account.getAccountNumber();
                account.setAccountCode(accountCode);
                account.setAccountNumber(accountNumber);
                account.setCreatedDate(LocalDate.now());
                account.setLastUpdated(LocalDate.now());
                personal.setAccount(account);
                personalRepository.save(personal);
                response.put("status",200);
                response.put("success",true);
                response.put("message", "New account has been added successfully");
            }else {
                response.put("status",404);
                response.put("success",false);
                response.put("message", "Required data not found");
            }
        }else {
            response.put("status",400);
            response.put("success",false);
            response.put("message", "Data cannot be empty");
        }
        return ResponseEntity.status(Integer.parseInt(response.get("status").toString())).body(response);
    }

    public ResponseEntity deleteAccountById(Long id){
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        try {
            if(id !=null) {
                Personal personal = personalRepository.findById(id).get();
                if(personal != null){
                    personal.setAccount(null);
                    personalRepository.save(personal);
                }
                response.put("status",200);
                response.put("success",true);
                response.put("message", "Account has been deleted successfully");
            }else {
                response.put("status",404);
                response.put("success",false);
                response.put("message", "Account not found with id "+id);
            }
        }catch (EmptyResultDataAccessException e){
            response.put("status",404);
            response.put("success",false);
            response.put("message", "Account not found with id "+id);
        }
        return ResponseEntity.status(Integer.parseInt(response.get("status").toString())).body(response);
    }

    public ResponseEntity updateAccount(Long id, Account account){
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        if(id != null && account !=null) {
            Personal personal = personalRepository.findById(id).get();
            if(personal != null){
                account.setLastUpdated(LocalDate.now());
                personal.setAccount(account);
                personalRepository.save(personal);
                response.put("status",200);
                response.put("success",true);
                response.put("result", "Account has been updated successfully");
            }else {
                response.put("status",404);
                response.put("success",false);
                response.put("message", "Required record account not found");
            }
        }else {
            response.put("status",400);
            response.put("success",false);
            response.put("message", "Something went wrong please try again");
        }
        return ResponseEntity.status(Integer.parseInt(response.get("status").toString())).body(response);
    }

    public ResponseEntity getAccountById(Long id){
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        if(id != null) {
            Personal personal = personalRepository.findById(id).get();
            if(personal != null){
                response.put("status",200);
                response.put("success",true);
                response.put("result", personal.getAccount());
            }else {
                response.put("status",404);
                response.put("success",false);
                response.put("message", "Account not found with personal id "+id);
            }
        }else {
            response.put("status",400);
            response.put("success",false);
            response.put("message", "Something went wrong please try again");
        }
        return ResponseEntity.status(Integer.parseInt(response.get("status").toString())).body(response);
    }
}
