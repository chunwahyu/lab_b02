package com.sp2603.lab_b02.controller;

import com.sp2603.lab_b02.data.person.domainObject.response.CreatePersonResponseData;
import com.sp2603.lab_b02.data.person.dto.response.CreatePersonResponseDto;
import com.sp2603.lab_b02.data.person.entity.PersonEntity;
import com.sp2603.lab_b02.data.person.domainObject.request.CreatePersonRequestData;
import com.sp2603.lab_b02.data.person.dto.request.CreatePersonRequestDto;
import com.sp2603.lab_b02.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {

    private final PersonService personService;

    //Constructor injection
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/people")
    public CreatePersonResponseDto createPerson(@RequestBody CreatePersonRequestDto createPersonRequestDto) {
        CreatePersonRequestData createPersonRequestData = new CreatePersonRequestData();
        createPersonRequestData.setFirstName(createPersonRequestDto.getFirstName());
        createPersonRequestData.setLastName(createPersonRequestDto.getLastName());
        createPersonRequestData.setHkid(createPersonRequestDto.getHkid());

        CreatePersonResponseData createPersonResponseData = personService.createPerson(createPersonRequestData);

        CreatePersonResponseDto createPersonResponseDto = new CreatePersonResponseDto();
        createPersonResponseDto.setFirstName(createPersonResponseData.getFirstName());
        createPersonResponseDto.setLastName(createPersonResponseData.getLastName());
        createPersonResponseDto.setHkid(createPersonResponseData.getHkid());

        return createPersonResponseDto;
    }

    /**
    @PostMapping("/create")
    public ResponseEntity<PersonEntity> createPerson(@RequestBody PersonEntity newPerson) {
        PersonEntity person = personService.createPerson(newPerson);
        return ResponseEntity.status(HttpStatus.CREATED).body(person);
    }

    @GetMapping("/all")
    public List<PersonEntity> getAllPersons() {
        return personService.getAllPersons();
    }

    @PutMapping("/update")
    public PersonEntity updatePerson(@RequestBody PersonEntity updatePerson) {
        for(PersonEntity person: personService.getAllPersons()) {
            if(person.getHkid().equals(updatePerson.getHkid())) {
                person.setFirstName(updatePerson.getFirstName());
                person.setLastName(updatePerson.getLastName());
                return person;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete")
    public PersonEntity deletePerson(@RequestParam String hkid) {
        for(PersonEntity person: personService.getAllPersons()) {
            if(person.getHkid().equals(hkid)) {
                personService.getAllPersons().remove(person);
                return person;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/filter")
    public List<PersonEntity> filterByLastName(@RequestParam String lastName) {
        List<PersonEntity> personList = new ArrayList<>();
        for(PersonEntity person: personService.getAllPersons()) {
            if(person.getLastName().equals(lastName)) {
                personList.add(person);
            }
        }
        return personList;
    }
    **/
}
