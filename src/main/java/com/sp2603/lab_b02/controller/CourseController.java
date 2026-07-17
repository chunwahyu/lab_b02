package com.sp2603.lab_b02.controller;

import com.sp2603.lab_b02.data.course.domainObject.request.CreateCourseRequestData;
import com.sp2603.lab_b02.data.course.domainObject.request.UpdateCourseRequestData;
import com.sp2603.lab_b02.data.course.domainObject.response.CourseResponseData;
import com.sp2603.lab_b02.data.course.dto.request.CreateCourseRequestDto;
import com.sp2603.lab_b02.data.course.dto.request.UpdateCourseRequestDto;
import com.sp2603.lab_b02.data.course.dto.response.CourseResponseDto;
import com.sp2603.lab_b02.mapper.course.CourseDataMapper;
import com.sp2603.lab_b02.mapper.course.CourseDtoMapper;
import com.sp2603.lab_b02.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseDataMapper courseDataMapper;
    private final CourseService courseService;
    private final CourseDtoMapper courseDtoMapper;

    public CourseController(CourseDataMapper courseDataMapper, CourseService courseService, CourseDtoMapper courseDtoMapper) {
        this.courseDataMapper = courseDataMapper;
        this.courseService = courseService;
        this.courseDtoMapper = courseDtoMapper;
    }

    @PostMapping
    public CourseResponseDto createCourse(@Valid @RequestBody CreateCourseRequestDto createCourseRequestDto) {
        /** Lv2
        CreateCourseRequestData createCourseRequestData = courseDataMapper.toCreateCourseRequestData(createCourseRequestDto);
        CourseResponseData courseResponseData = courseService.createCourse(createCourseRequestData);
        CourseResponseDto courseResponseDto = courseDtoMapper.toCourseResponseDto(courseResponseData);
        return courseResponseDto;
         **/

        return courseDtoMapper.toCourseResponseDto(
                courseService.createCourse(
                        courseDataMapper.toCreateCourseRequestData(createCourseRequestDto)
                )
        );
    }

    @GetMapping
    public List<CourseResponseDto> getAllCourses() {
        /** Lv2
        List<CourseResponseData> courseResponseDataList = courseService.getAllCourses();
        List<CourseResponseDto> courseResponseDtoList = courseDtoMapper.toCourseResponseDtoList(courseResponseDataList);
        return courseResponseDtoList;
         **/

        return courseDtoMapper.toCourseResponseDtoList(
                courseService.getAllCourses()
        );
    }

    @PutMapping
    public CourseResponseDto updateCourse(@Valid @RequestBody UpdateCourseRequestDto updateCourseRequestDto) {

//        Lv2
//        UpdateCourseRequestData updateCourseRequestData = courseDataMapper.toUpdateCourseRequestData(updateCourseRequestDto);
//        CourseResponseData courseResponseData = courseService.updateCourse(updateCourseRequestData);
//        CourseResponseDto courseResponseDto = courseDtoMapper.toCourseResponseDto(courseResponseData);
//
//        return courseResponseDto;

        return courseDtoMapper.toCourseResponseDto(
                courseService.updateCourse(
                        courseDataMapper.toUpdateCourseRequestData(updateCourseRequestDto)
                )
        );
    }

}
