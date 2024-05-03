package com.example.springassignmenttwo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController

public class CourseController {
    @Autowired
    private CourseRepository courseRepository;



    @GetMapping("/read")

    public Iterable<Course> getCourse() {
        return courseRepository.findAll();
}
    @GetMapping("/read/{id}")
    public Course findCourseById(@PathVariable Integer id) {
        return courseRepository.findCourseById(id);
    }
    @PostMapping("/create")
    public String createCourse(@RequestParam String name, @RequestParam String description) {
        Course course = new Course();
        course.setName(name);
        course.setDescription(description);
        courseRepository.save(course);
        return "Added new course to system!";
    }





    @PutMapping("/update/{id}")
    public String updateCourse(@PathVariable Integer id, @RequestBody Course updatedCourse) {
        Course existingCourse = courseRepository.findCourseById((id));
        if (existingCourse != null) {
            existingCourse.setName(updatedCourse.getName());
            existingCourse.setDescription(updatedCourse.getDescription());
            courseRepository.save(existingCourse);
            return " Course updated ";
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Integer id) {
        Course course = courseRepository.findCourseById((id));
        if (course != null) {
            courseRepository.delete(course);
            return "Course deleted successfully";
        }
        return "Course not found";
    }


}
