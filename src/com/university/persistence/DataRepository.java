package com.university.persistence;

import com.university.users.User;
import com.university.courses.Course;
import com.university.enrollment.Enrollment;
import com.university.grades.Grade;
import java.util.List;
import java.util.Map;

public interface DataRepository {
  
 void saveUser(User user);
    User getUserById(String userId);
    List<User> getAllUsers();
    
    void saveCourse(Course course);
    Course getCourseByCode(String courseCode);
    List<Course> getAllCourses();
    

    void saveEnrollment(Enrollment enrollment);
    List<Enrollment> getEnrollmentsByStudent(String studentId);
    
    void saveGrade(Grade grade);
    List<Grade> getGradesByStudent(String studentId);
    

    void saveAllData(Map<String, Object> allData);
    Map<String, Object> loadAllData();
}