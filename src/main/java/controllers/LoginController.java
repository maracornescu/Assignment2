package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import business.model.StudentModel;
import business.model.TeacherModel;
import business.service.IStudentService;
import business.service.ITeacherService;

@RestController
@RequestMapping("login")
public class LoginController {
	
	private final IStudentService studentService;
	private final ITeacherService teacherService;
	
	@Autowired
	public LoginController(IStudentService studentService, ITeacherService teacherService) {
		this.studentService = studentService;
		this.teacherService = teacherService;
	}
	 
	@GetMapping("")
	public <T> T login(String email, String password) {
		try {
			StudentModel s = studentService.login(email, password);
			TeacherModel t = teacherService.login(email, password);
			if(s != null) {
				return (T) s;
			}
			else {
				return (T) t;
			}
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	 }
}
