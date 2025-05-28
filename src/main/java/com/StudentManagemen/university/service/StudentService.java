package com.StudentManagemen.university.service;




import com.StudentManagemen.university.model.Student;
import com.StudentManagemen.university.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // إضافة طالب جديد
    public Student addStudent(String name, String email, String major, int year) {
        // التحقق من صحة البيانات
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("اسم الطالب مطلوب");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("البريد الإلكتروني مطلوب");
        }
        if (major == null || major.trim().isEmpty()) {
            throw new IllegalArgumentException("التخصص مطلوب");
        }
        if (year < 1 || year > 6) {
            throw new IllegalArgumentException("السنة الدراسية يجب أن تكون بين 1 و 6");
        }

        Student student = new Student();
        student.setName(name.trim());
        student.setEmail(email.trim());
        student.setMajor(major.trim());
        student.setYear(year);

        return studentRepository.addStudent(student);
    }

    // البحث عن طالب بالمعرف
    public Student getStudentById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("معرف الطالب غير صحيح");
        }

        Optional<Student> student = studentRepository.getStudentById(id);
        if (student.isEmpty()) {
            throw new RuntimeException("الطالب غير موجود بالمعرف: " + id);
        }

        return student.get();
    }

    // الحصول على جميع الطلاب
    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.getAllStudents();
        if (students.isEmpty()) {
            throw new RuntimeException("لا يوجد طلاب في النظام");
        }
        return students;
    }

    // تحديث طالب
    public Student updateStudent(Long id, String name, String email, String major, int year) {
        Student existingStudent = getStudentById(id);

        if (name != null && !name.trim().isEmpty()) {
            existingStudent.setName(name.trim());
        }
        if (email != null && !email.trim().isEmpty()) {
            existingStudent.setEmail(email.trim());
        }
        if (major != null && !major.trim().isEmpty()) {
            existingStudent.setMajor(major.trim());
        }
        if (year >= 1 && year <= 6) {
            existingStudent.setYear(year);
        }

        return studentRepository.updateStudent(existingStudent);
    }

    // حذف طالب
    public boolean deleteStudent(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("معرف الطالب غير صحيح");
        }

        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("الطالب غير موجود بالمعرف: " + id);
        }

        return studentRepository.deleteStudent(id);
    }

    // البحث بالاسم
    public List<Student> searchByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("اسم البحث مطلوب");
        }
        return studentRepository.findByName(name.trim());
    }

    // البحث بالتخصص
    public List<Student> searchByMajor(String major) {
        if (major == null || major.trim().isEmpty()) {
            throw new IllegalArgumentException("اسم التخصص مطلوب");
        }
        return studentRepository.findByMajor(major.trim());
    }

    // التحقق من وجود طالب
    public boolean studentExists(Long id) {
        return id != null && id > 0 && studentRepository.existsById(id);
    }
}