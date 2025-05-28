package com.StudentManagemen.university.controller;






import com.StudentManagemen.university.*;
import com.StudentManagemen.university.model.Student;
import com.StudentManagemen.university.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class StudentEndpoint {

    private static final String NAMESPACE_URI = "http://www.example.com/students";

    @Autowired
    private StudentService studentService;

    // إضافة طالب جديد
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addStudentRequest")
    @ResponsePayload
    public AddStudentResponse addStudent(@RequestPayload AddStudentRequest request) {
        try {
            Student student = studentService.addStudent(
                    request.getName(),
                    request.getEmail(),
                    request.getMajor(),
                    request.getYear()
            );

            AddStudentResponse response = new AddStudentResponse();
            StudentType studentType = mapToStudentType(student);
            response.setStudent(studentType);

            return response;
        } catch (Exception e) {
            throw new RuntimeException("خطأ في إضافة الطالب: " + e.getMessage());
        }
    }

    // البحث عن طالب
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getStudentRequest")
    @ResponsePayload
    public GetStudentResponse getStudent(@RequestPayload GetStudentRequest request) {
        try {
            Student student = studentService.getStudentById(request.getId());

            GetStudentResponse response = new GetStudentResponse();
            StudentType studentType = mapToStudentType(student);
            response.setStudent(studentType);

            return response;
        } catch (Exception e) {
            throw new RuntimeException("خطأ في البحث عن الطالب: " + e.getMessage());
        }
    }

    // الحصول على جميع الطلاب
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllStudentsRequest")
    @ResponsePayload
    public GetAllStudentsResponse getAllStudents(@RequestPayload GetAllStudentsRequest request) {
        try {
            List<Student> students = studentService.getAllStudents();

            GetAllStudentsResponse response = new GetAllStudentsResponse();

            for (Student student : students) {
                StudentType studentType = mapToStudentType(student);
                response.getStudents().add(studentType);
            }

            return response;
        } catch (Exception e) {
            throw new RuntimeException("خطأ في الحصول على قائمة الطلاب: " + e.getMessage());
        }
    }

    // حذف طالب
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteStudentRequest")
    @ResponsePayload
    public DeleteStudentResponse deleteStudent(@RequestPayload DeleteStudentRequest request) {
        try {
            boolean deleted = studentService.deleteStudent(request.getId());

            DeleteStudentResponse response = new DeleteStudentResponse();
            response.setSuccess(deleted);
            response.setMessage(deleted ? "تم حذف الطالب بنجاح" : "فشل في حذف الطالب");

            return response;
        } catch (Exception e) {
            DeleteStudentResponse response = new DeleteStudentResponse();
            response.setSuccess(false);
            response.setMessage("خطأ في حذف الطالب: " + e.getMessage());
            return response;
        }
    }

    // تحديث بيانات طالب
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateStudentRequest")
    @ResponsePayload
    public UpdateStudentResponse updateStudent(@RequestPayload UpdateStudentRequest request) {
        try {
            Student student = studentService.updateStudent(
                    request.getId(),
                    request.getName(),
                    request.getEmail(),
                    request.getMajor(),
                    request.getYear()
            );

            UpdateStudentResponse response = new UpdateStudentResponse();
            StudentType studentType = mapToStudentType(student);
            response.setStudent(studentType);

            return response;
        } catch (Exception e) {
            throw new RuntimeException("خطأ في تحديث بيانات الطالب: " + e.getMessage());
        }
    }

    

    // تحويل كائن Student إلى StudentType
    private StudentType mapToStudentType(Student student) {
        StudentType studentType = new StudentType();
        studentType.setId(student.getId());
        studentType.setName(student.getName());
        studentType.setEmail(student.getEmail());
        studentType.setMajor(student.getMajor());
        studentType.setYear(student.getYear());
        return studentType;
    }
}