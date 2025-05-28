package com.StudentManagemen.university.repository;


import com.StudentManagemen.university.model.Student;
import org.springframework.stereotype.Repository;

import java.util.*;
        import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class StudentRepository {

    // خريطة لتخزين الطلاب مؤقتاً (يمكن استبدالها بقاعدة بيانات حقيقية)
    private final Map<Long, Student> students = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public StudentRepository() {
        // إضافة بعض البيانات التجريبية
        initializeData();
    }

    private void initializeData() {
        Student student1 = new Student(1L, "أحمد محمد", "ahmed@university.edu", "هندسة الحاسوب", 3);
        Student student2 = new Student(2L, "فاطمة علي", "fatima@university.edu", "الطب", 2);
        Student student3 = new Student(3L, "محمد سالم", "mohammed@university.edu", "الهندسة المدنية", 4);

        students.put(student1.getId(), student1);
        students.put(student2.getId(), student2);
        students.put(student3.getId(), student3);

        idGenerator.set(4L);
    }

    // إضافة طالب جديد
    public Student addStudent(Student student) {
        Long id = idGenerator.getAndIncrement();
        student.setId(id);
        students.put(id, student);
        return student;
    }

    // البحث عن طالب بالمعرف
    public Optional<Student> getStudentById(Long id) {
        return Optional.ofNullable(students.get(id));
    }

    // الحصول على جميع الطلاب
    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    // تحديث طالب
    public Student updateStudent(Student student) {
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return student;
        }
        return null;
    }

    // حذف طالب
    public boolean deleteStudent(Long id) {
        return students.remove(id) != null;
    }

    // التحقق من وجود طالب
    public boolean existsById(Long id) {
        return students.containsKey(id);
    }

    // البحث بالاسم
    public List<Student> findByName(String name) {
        return students.values().stream()
                .filter(student -> student.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    // البحث بالتخصص
    public List<Student> findByMajor(String major) {
        return students.values().stream()
                .filter(student -> student.getMajor().equalsIgnoreCase(major))
                .toList();
    }
}