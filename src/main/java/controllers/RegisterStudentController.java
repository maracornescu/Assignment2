package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import business.model.StudentModel;
import business.service.IStudentService;

@RestController
@RequestMapping("register")
public class RegisterStudentController {
	
	private final IStudentService studentService;
	
	@Autowired
	public RegisterStudentController(IStudentService studentService) {
		this.studentService = studentService;
	}
	
	@PutMapping("")
	public StudentModel register(String token, String email, String password) {
		try {
            return studentService.register(token, email, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }	
	}
}
