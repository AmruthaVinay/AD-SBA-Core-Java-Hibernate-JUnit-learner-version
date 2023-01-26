package sba.sms.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sba.sms.models.Student;

class FirstJunit {
	 static StudentService studentService;
	/*
	 * @Test void test() { fail("Not yet implemented"); }
	 */
	
	 @Test void test() 
	 { 
		 Student expectedObj = new Student("annette@gmail.com", "annette allen", "password");
		 assertThat(studentService.getStudentByEmail("annette@gmail.com").getEmail()).isEqualTo(expectedObj.getEmail());
	 }

}
