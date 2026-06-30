package com.sp2603.lab_b02;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    private final List<PersonData> personList = new ArrayList<>();

    public PersonData createPerson(PersonData newPerson) {
        personList.add(newPerson);
        return newPerson;
    }

    public List<PersonData> getAllPersons() {
        return personList;
    }
}
