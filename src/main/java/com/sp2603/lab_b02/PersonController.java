package com.sp2603.lab_b02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;

    //Constructor injection
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/create")
    public ResponseEntity<PersonData> createPerson(@RequestBody PersonData newPerson) {
        PersonData person = personService.createPerson(newPerson);
        return ResponseEntity.status(HttpStatus.CREATED).body(person);
    }

    @GetMapping("/all")
    public List<PersonData> getAllPersons() {
        return personService.getAllPersons();
    }

    @PutMapping("/update")
    public PersonData updatePerson(@RequestBody PersonData updatePerson) {
        for(PersonData person: personService.getAllPersons()) {
            if(person.getHkid().equals(updatePerson.getHkid())) {
                person.setFirstName(updatePerson.getFirstName());
                person.setLastName(updatePerson.getLastName());
                return person;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete")
    public PersonData deletePerson(@RequestParam String hkid) {
        for(PersonData person: personService.getAllPersons()) {
            if(person.getHkid().equals(hkid)) {
                personService.getAllPersons().remove(person);
                return person;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/filter")
    public List<PersonData> filterByLastName(@RequestParam String lastName) {
        List<PersonData> personList = new ArrayList<>();
        for(PersonData person: personService.getAllPersons()) {
            if(person.getLastName().equals(lastName)) {
                personList.add(person);
            }
        }
        return personList;
    }
}
