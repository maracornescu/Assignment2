package business.service;

import business.model.TeacherModel;

public interface ITeacherService {
	
	TeacherModel login(String email, String password);
	
}
