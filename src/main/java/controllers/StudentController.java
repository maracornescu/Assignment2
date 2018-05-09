package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import business.service.IStudentService;
import business.model.StudentModel;

@RestController
@RequestMapping("student")
public class StudentController {
	
	private final IStudentService studentService;
	
	 @Autowired
	 public StudentController(IStudentService studentService) {
		 this.studentService = studentService;
	 }

	 @GetMapping("")
	 public List<StudentModel> getAllStudents() {
		 try {
			 return studentService.getAllStudents();
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	 }
	 
	@GetMapping("{id}")
	public StudentModel getStudentById(@PathVariable(value = "id") Long studentId) {
		try {
			 return studentService.getStudentById(studentId);
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	}
	
	
	@PostMapping("")
	public StudentModel saveStudent(@RequestBody StudentModel student) {
		try {
            return studentService.saveStudent(student);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
	@PutMapping("{id}")
	public StudentModel updateStudent(@PathVariable(value = "id") Long studentId, StudentModel student) {
		try {
            return studentService.updateStudent(studentId, student);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
	@DeleteMapping("{id}")
	public String deleteStudentById(@PathVariable(value = "id") Long studentId) {
		try {
			studentService.deleteStudentById(studentId);
	        return "Student with id = " + studentId + " successful deleted!";
	    } catch (Exception e) {
	    	return e.getMessage();
	    }
	}
}
