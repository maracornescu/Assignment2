package business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import business.RandomToken;
import business.model.StudentModel;
import repository.IStudentRepository;
import repository.dbmodel.StudentDto;

@Service
public class StudentService implements IStudentService{
	
	public final IStudentRepository studentRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public StudentService(IStudentRepository studentRepository) {
		this.studentRepository = studentRepository;
		modelMapper = new ModelMapper();
	}
	
	public List<StudentModel> getAllStudents() {
		List<StudentModel> studentModel = new ArrayList<StudentModel>();
		List<StudentDto> studentDto = studentRepository.findAll();
	
		for(StudentDto s: studentDto) {
			
			StudentModel student = modelMapper.map(s, StudentModel.class);
			studentModel.add(student);
		}
		
		return studentModel;
	}
	
	public StudentModel getStudentById(Long studentId) {
		Optional<StudentDto> studentDto = studentRepository.findById(studentId);
		StudentModel s = modelMapper.map(studentDto.get(), StudentModel.class);
		return s;
	}
	
	public StudentModel saveStudent(StudentModel student) {

    	StudentDto studentDto = modelMapper.map(student, StudentDto.class);
    	studentDto.setToken(RandomToken.generateToken());
    	studentDto.setPassword(null);
    	
        if (studentRepository.findByEmail(student.getEmail()) == null) {
        	studentRepository.save(studentDto);
        	student.setToken(studentDto.getToken());
            return student;
        }
        
        return null;
    }

    public StudentModel updateStudent(Long studentId, StudentModel student) {
       
    	Optional<StudentDto> updateStudent = studentRepository.findById(studentId);

        if (updateStudent != null) {
        	if(student.getEmail() != null)
        		updateStudent.get().setEmail(student.getEmail());
        	if(student.getFirstName() != null)	
        		updateStudent.get().setFirstName(student.getFirstName());
        	if(student.getLastName() != null)
        		updateStudent.get().setLastName(student.getLastName());
        	if(student.getGroup() != null)
        		updateStudent.get().setGroup(student.getGroup());
        	if(student.getHobby() != null)
        		updateStudent.get().setHobby(student.getHobby());
        	
        	studentRepository.save(updateStudent.get());
            
        	StudentModel studentModel = modelMapper.map(updateStudent.get(), StudentModel.class);
            
            return studentModel;
            
        } else {
            return null;
        }
    }
    
    public void deleteStudentById(Long bookId) {
    	studentRepository.deleteById(bookId);   
    }

	public StudentModel register(String token, String email, String password) {
		
		StudentDto updateStudent = studentRepository.findByEmail(email);
		
		if(updateStudent != null) {
			if(updateStudent.getToken().equals(token)) {
				updateStudent.setPassword(password);
				studentRepository.save(updateStudent);
				
				StudentModel studentModel = modelMapper.map(updateStudent, StudentModel.class);
	            
	            return studentModel;
			}
		}
		
		return null;
		
	}

	public StudentModel login(String email, String password) {
		
		StudentDto loginStudent = studentRepository.findByEmail(email);
		
		if(loginStudent != null) {
			if(loginStudent.getPassword().equals(password)) {
				StudentModel studentModel = modelMapper.map(loginStudent, StudentModel.class);
				return studentModel;
			}
		}
		
		return null;
	}
	
}
