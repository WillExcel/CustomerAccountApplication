package personal.account.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import personal.account.api.models.Personal;
import personal.account.api.services.PersonalService;

import java.util.HashMap;

@RestController
public class PersonalController {

    @Autowired
    PersonalService personalService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity welcome(){
        HashMap<String, Object> welcomeMsg = new HashMap();
            welcomeMsg.put("message", "Welcome to Personal Account Management API");
            return ResponseEntity.ok(welcomeMsg);
    }

    @RequestMapping(path ="/add-personal", method = RequestMethod.POST)
    public ResponseEntity addPersonal(@RequestBody Personal personal) {
        return personalService.addPersonal(personal);
    }

    @RequestMapping(path ="/update-personal/{id}", method = RequestMethod.PUT)
    public ResponseEntity updatePersonal(@PathVariable(value = "id") Long id, @RequestBody Personal personal) {
        return personalService.updatePersonal(id,personal);
    }

    @RequestMapping(path ="/delete-personal/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deletePersonal(@PathVariable(value = "id") Long id) {
        return personalService.deletePersonalById(id);
    }

    @RequestMapping(path ="/personal/{id}", method = RequestMethod.GET)
    public ResponseEntity getPersonalById(@PathVariable(value = "id") Long id) {
        return personalService.getPersonalById(id);
    }

    @RequestMapping(path ="/all-personals", method = RequestMethod.GET)
    public ResponseEntity getAllPersonals() {
        return personalService.getAllPersonals();
    }
}
