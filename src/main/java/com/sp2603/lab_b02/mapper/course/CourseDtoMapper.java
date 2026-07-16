package com.sp2603.lab_b02.mapper.course;

import com.sp2603.lab_b02.data.course.domainObject.response.CourseResponseData;
import com.sp2603.lab_b02.data.course.dto.response.CourseResponseDto;
import com.sp2603.lab_b02.mapper.person.PersonDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class CourseDtoMapper {
    private final PersonDtoMapper personDtoMapper;

    public CourseDtoMapper(PersonDtoMapper personDtoMapper) {
        this.personDtoMapper = personDtoMapper;
    }

    public CourseResponseDto toCourseResponseDto(CourseResponseData courseResponseData) {
        CourseResponseDto courseResponseDto = new CourseResponseDto();
        courseResponseDto.setCourseId(courseResponseDto.getCourseId());
        courseResponseDto.setCourseName(courseResponseData.getCourseName());
        courseResponseDto.setPrice(courseResponseData.getPrice());
        courseResponseDto.setTeacher(
                personDtoMapper.toPersonResponseDto(courseResponseData.getTeacher())
        );

        return courseResponseDto;
    }
}
