package com.sp2603.lab_b02.service.impl;

import com.sp2603.lab_b02.data.course.domainObject.request.CreateCourseRequestData;
import com.sp2603.lab_b02.data.course.domainObject.response.CourseResponseData;
import com.sp2603.lab_b02.data.course.entity.CourseEntity;
import com.sp2603.lab_b02.data.person.entity.PersonEntity;
import com.sp2603.lab_b02.exception.course.CourseExistedException;
import com.sp2603.lab_b02.mapper.course.CourseDataMapper;
import com.sp2603.lab_b02.mapper.course.CourseEntityMapper;
import com.sp2603.lab_b02.service.CourseService;
import com.sp2603.lab_b02.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);
    private final PersonService personService;
    private final CourseEntityMapper courseEntityMapper;
    private final CourseDataMapper courseDataMapper;
    private List<CourseEntity> courseEntityList = new ArrayList<>();

    public CourseServiceImpl(PersonService personService, CourseEntityMapper courseEntityMapper, CourseDataMapper courseDataMapper) {
        this.personService = personService;
        this.courseEntityMapper = courseEntityMapper;
        this.courseDataMapper = courseDataMapper;
    }

    @Override
    public CourseResponseData createCourse(CreateCourseRequestData createCourseRequestData) {
        try {
            if(isCourseExist(createCourseRequestData.getCourseId())) {
                throw new CourseExistedException(createCourseRequestData.getCourseId());
            }
            /** Lv2
            PersonEntity teacherEntity = personService.getEntityByHkid(createCourseRequestData.getTeacherHkid());

            CourseEntity courseEntity = courseEntityMapper.toCourseEntity(createCourseRequestData,teacherEntity);
            courseEntityList.add(courseEntity);

            CourseResponseData courseResponseData = courseDataMapper.toCourseResponseData(courseEntity);

            return courseResponseData;
             **/

            CourseEntity courseEntity = courseEntityMapper.toCourseEntity(
                    createCourseRequestData,
                    personService.getEntityByHkid(createCourseRequestData.getTeacherHkid())
                    );

            courseEntityList.add(courseEntity);

            return courseDataMapper.toCourseResponseData(courseEntity);

        } catch(Exception exception) {
            log.warn("Create Course Failed: {}", exception.getMessage());
            throw exception;
        }
    }

    public boolean isCourseExist(String courseId) {
        for(CourseEntity courseEntity: courseEntityList) {
            if(courseEntity.getCourseId().equals(courseId)) {
                return true;
            }
        }
        return false;
    }
}
