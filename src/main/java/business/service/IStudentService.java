package business.service;

import business.model.StudentModel;
import java.util.List;

public interface IStudentService {
	
	List<StudentModel> getAllStudents();
	StudentModel getStudentById(Long studentId);
	StudentModel saveStudent(StudentModel student);
	StudentModel updateStudent(Long studentId, StudentModel student);
	void deleteStudentById(Long studentId);
	StudentModel register(String token, String email, String password);
	StudentModel login(String email, String password);
}
