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
import java.util.List;

@Service
public class PersonalService {
    @Autowired
    PersonalRepository personalRepository;

    public ResponseEntity addPersonal(Personal personal){
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
            if(personal !=null) {
                if(personal.getAccount() != null){
                    String accountCode = Account.getAccountCodePrefix()+personal.getAccount().getAccountCode();
                    String accountNumber = Account.getAccountNoPrefix()+personal.getAccount().getAccountNumber();
                    personal.getAccount().setAccountCode(accountCode);
                    personal.getAccount().setAccountNumber(accountNumber);
                    personal.getAccount().setCreatedDate(LocalDate.now());
                    personal.getAccount().setLastUpdated(LocalDate.now());
                }
                personal.setCreatedDate(LocalDate.now());
                personal.setLastUpdated(LocalDate.now());
                personalRepository.save(personal);
                response.put("status",200);
                response.put("success",true);
                response.put("message", "New personal has been added successfully");
            }else {
                response.put("status",400);
                response.put("success",false);
                response.put("message", "Data cannot be empty");
            }
        return ResponseEntity.status(Integer.parseInt(response.get("status").toString())).body(response);
    }

    public ResponseEntity deletePersonalById(Long id){
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        try {
            if(id !=null) {
                personalRepository.deleteById(id);
                response.put("status",200);
                response.put("success",true);
                response.put("message", "Personal has been deleted successfully");
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

    public ResponseEntity updatePersonal(Long id, Personal personal){
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        if(id != null && personal !=null) {
            Personal oldPersonal = personalRepository.findById(id).get();
            if(oldPersonal != null){
                if(personal.getAccount() != null){
                    personal.getAccount().setId(oldPersonal.getId());
                    personal.getAccount().setCreatedDate(oldPersonal.getAccount().getCreatedDate());
                    personal.getAccount().setLastUpdated(LocalDate.now());
                }else if(personal.getAccount()==null){
                    personal.setAccount(oldPersonal.getAccount());
                }
                personal.setId(oldPersonal.getId());
                personal.setCreatedDate(oldPersonal.getCreatedDate());
                personal.setLastUpdated(LocalDate.now());
                personalRepository.save(personal);
                response.put("status",200);
                response.put("success",true);
                response.put("result", "Personal has been updated successfully");
            }else {
                response.put("status",404);
                response.put("success",false);
                response.put("message", "Required record personal not found");
            }
        }else {
            response.put("status",400);
            response.put("success",false);
            response.put("message", "Something went wrong please try again");
        }
        return ResponseEntity.status(Integer.parseInt(response.get("status").toString())).body(response);
    }

    public ResponseEntity getPersonalById(Long id){
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        if(id != null) {
            Personal personal = personalRepository.findById(id).get();
            if(personal != null){
                response.put("status",200);
                response.put("success",true);
                response.put("result", personal);
            }else {
                response.put("status",404);
                response.put("success",false);
                response.put("message", "Personal not found with id "+id);
            }
        }else {
            response.put("status",400);
            response.put("success",false);
            response.put("message", "Something went wrong please try again");
        }
        return ResponseEntity.status(Integer.parseInt(response.get("status").toString())).body(response);
    }

    public ResponseEntity getAllPersonals(){
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
            List<Personal> personalList = personalRepository.findAll();
            if((personalList != null && !personalList.isEmpty()) && personalList.size() != 0){
                response.put("status",200);
                response.put("success",true);
                response.put("result", personalList);
            }else {
                response.put("status",404);
                response.put("success",false);
                response.put("message", "There is no personal added yet");
            }
        return ResponseEntity.status(Integer.parseInt(response.get("status").toString())).body(response);
    }
}
