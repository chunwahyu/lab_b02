package com.sp2603.lab_b02.service.impl;

import com.sp2603.lab_b02.data.person.domainObject.request.CreatePersonRequestData;
import com.sp2603.lab_b02.data.person.domainObject.response.CreatePersonResponseData;
import com.sp2603.lab_b02.data.person.domainObject.response.GetAllPeopleResponseData;
import com.sp2603.lab_b02.data.person.dto.request.CreatePersonRequestDto;
import com.sp2603.lab_b02.data.person.entity.PersonEntity;
import com.sp2603.lab_b02.mapper.person.PersonDataMapper;
import com.sp2603.lab_b02.mapper.person.PersonEntityMapper;
import com.sp2603.lab_b02.service.PersonService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private List<PersonEntity> personEntityList = new ArrayList<>();
    private final PersonEntityMapper personEntityMapper;
    private final PersonDataMapper personDataMapper;

    //Constructor injection
    public PersonServiceImpl(PersonEntityMapper personEntityMapper, PersonDataMapper personDataMapper) {
        this.personEntityMapper = personEntityMapper;
        this.personDataMapper = personDataMapper;
    }

    @Override
    public CreatePersonResponseData createPerson(CreatePersonRequestData createPersonRequestData) {
        //PersonEntity personEntity = new PersonEntity();
        //personEntity.setFirstName(createPersonRequestData.getFirstName());
        //personEntity.setLastName(createPersonRequestData.getLastName());
        //personEntity.setHkid(createPersonRequestData.getHkid());

        //CreatePersonResponseData createPersonResponseData = new CreatePersonResponseData();
        //createPersonResponseData.setFirstName(personEntity.getFirstName());
        //createPersonResponseData.setLastName(personEntity.getLastName());
        //createPersonResponseData.setHkid(personEntity.getHkid());

        PersonEntity personEntity = personEntityMapper.toPersonEntity(createPersonRequestData);

        personEntityList.add(personEntity);

        CreatePersonResponseData createPersonResponseData = personDataMapper.toCreatePersonResponseData(personEntity);

        return createPersonResponseData;
    }

    @Override
    public List<GetAllPeopleResponseData> getAllPeopleResponseDataList() {
        List<GetAllPeopleResponseData> getAllPeopleResponseDataList = personDataMapper.toGetAllPeopleResponseDataList(personEntityList);

        return getAllPeopleResponseDataList;
    }
}
