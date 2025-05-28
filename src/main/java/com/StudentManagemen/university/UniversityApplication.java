package com.StudentManagemen.university;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UniversityApplication {

	public static void main(String[] args) {

		SpringApplication.run(UniversityApplication.class, args);

		System.out.println("==============================================");
		System.out.println("    نظام إدارة الطلاب - خدمة SOAP    ");
		System.out.println("==============================================");
		System.out.println("الخدمة تعمل على: http://localhost:8080");
		System.out.println("WSDL متاح على: http://localhost:8080/ws/students.wsdl");
		System.out.println("==============================================");
		System.out.println("العمليات المتاحة:");
		System.out.println("1. إضافة طالب - addStudentRequest");
		System.out.println("2. البحث عن طالب - getStudentRequest");
		System.out.println("3. عرض جميع الطلاب - getAllStudentsRequest");
		System.out.println("4. حذف طالب - deleteStudentRequest");
		System.out.println("==============================================");
	}

}
