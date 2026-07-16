package com.sp2603.lab_b02.service;

import com.sp2603.lab_b02.data.course.domainObject.request.CreateCourseRequestData;
import com.sp2603.lab_b02.data.course.domainObject.response.CourseResponseData;

public interface CourseService {


    CourseResponseData createCourse(CreateCourseRequestData createCourseRequestData);
}
