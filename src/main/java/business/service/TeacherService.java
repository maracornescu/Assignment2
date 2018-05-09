package business.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import business.model.TeacherModel;
import repository.ITeacherRepository;
import repository.dbmodel.TeacherDto;

@Service
public class TeacherService implements ITeacherService{
	
	public final ITeacherRepository teacherRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public TeacherService(ITeacherRepository teacherRepository) {
		this.teacherRepository = teacherRepository;
		modelMapper = new ModelMapper();
	}
	
	public TeacherModel login(String email, String password) {
		TeacherDto loginTeacher = teacherRepository.findByEmail(email);
		
		if(loginTeacher != null) {
			if(loginTeacher.getPassword().equals(password)) {
				TeacherModel teacherModel = modelMapper.map(loginTeacher, TeacherModel.class);
				return teacherModel;
			}
		}
		return null;
	}

}
