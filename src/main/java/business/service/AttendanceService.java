package business.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import business.model.AttendanceModel;
import business.model.StudentModel;
import repository.IAttendanceRepository;
import repository.dbmodel.AttendanceDto;
import repository.dbmodel.LaboratoryClassDto;
import repository.dbmodel.StudentDto;

@Service
public class AttendanceService implements IAttendanceService{
	
	public final IAttendanceRepository attendanceRepository;
	
	@Autowired
	public AttendanceService(IAttendanceRepository attendanceRepository) {
		this.attendanceRepository = attendanceRepository;
	}
	
	private AttendanceDto mapModelToDto(AttendanceModel attendance) {
		AttendanceDto attendanceDto = new AttendanceDto();
		
		StudentDto studentDto = new StudentDto(attendance.getStudentId());
		LaboratoryClassDto laboratoryDto = new LaboratoryClassDto(attendance.getLaboratoryId());
		
		attendanceDto.setStudent(studentDto);
		attendanceDto.setLaboratory(laboratoryDto);
		attendanceDto.setPresent(attendance.getPresent());
	
		return attendanceDto;
	}
	
	private AttendanceModel mapDtoToModel(AttendanceDto attendanceDto) {
		AttendanceModel attendanceModel = new AttendanceModel();
		
		attendanceModel.setStudentId(attendanceDto.getStudent().getStudentId());
		attendanceModel.setLaboratoryId(attendanceDto.getLaboratory().getLaboratoryId());
		attendanceModel.setPresent(attendanceDto.getPresent());
	
		return attendanceModel;
	}
	
	@Override
	public List<AttendanceModel> getAllByStudent(Long studentId) {
		
		StudentDto studentDto = new StudentDto(studentId);
		
		List<AttendanceModel> attendanceModel = new ArrayList<AttendanceModel>();
		List<AttendanceDto> attendanceDto = attendanceRepository.findByStudent(studentDto);
		
		for(AttendanceDto a: attendanceDto) {
			AttendanceModel attendance = mapDtoToModel(a);
			attendanceModel.add(attendance);
		}
		
		return attendanceModel;
	}

	@Override
	public List<AttendanceModel> getallByLaboratory(Long laboratoryId) {
		LaboratoryClassDto laboratoryDto = new LaboratoryClassDto(laboratoryId);
		
		List<AttendanceModel> attendanceModel = new ArrayList<AttendanceModel>();
		List<AttendanceDto> attendanceDto = attendanceRepository.findByLaboratory(laboratoryDto);
		
		for(AttendanceDto a: attendanceDto) {
			AttendanceModel attendance = mapDtoToModel(a);
			attendanceModel.add(attendance);
		}
		
		return attendanceModel;
	}

	@Override
	public AttendanceModel getByStudentAndLaboratory(Long studentId, Long laboratoryId) {
		StudentDto studentDto = new StudentDto(studentId);
		LaboratoryClassDto laboratoryDto = new LaboratoryClassDto(laboratoryId);
		
		AttendanceDto attendanceDto = attendanceRepository.findByStudentAndLaboratory(studentDto, laboratoryDto);
		AttendanceModel attendanceModel = mapDtoToModel(attendanceDto);
		
		return attendanceModel;
	}

	@Override
	public AttendanceModel saveAttendance(AttendanceModel attendance) {
		StudentDto studentDto = new StudentDto(attendance.getStudentId());
		LaboratoryClassDto laboratoryDto = new LaboratoryClassDto(attendance.getLaboratoryId());
		AttendanceDto attendanceDto = mapModelToDto(attendance);
		
		if(attendanceRepository.findByStudentAndLaboratory(studentDto, laboratoryDto) == null)
			attendanceRepository.save(attendanceDto);
		
		return attendance;
	}

	@Override
	public void deleteByStudentAndLaboratory(Long studentId, Long laboratoryId) {
		StudentDto studentDto = new StudentDto(studentId);
		LaboratoryClassDto laboratoryDto = new LaboratoryClassDto(laboratoryId);
		attendanceRepository.deleteByStudentAndLaboratory(studentDto, laboratoryDto);
	}

	@Override
	public List<AttendanceModel> getAllAttendances() {
		List<AttendanceModel> attendanceModel = new ArrayList<AttendanceModel>();
		List<AttendanceDto> attendanceDto = attendanceRepository.findAll();
	
		for(AttendanceDto a: attendanceDto) {
			
			AttendanceModel attendance = mapDtoToModel(a);
			attendanceModel.add(attendance);
		}
		
		return attendanceModel;
	}

	@Override
	public AttendanceModel updateAttendance(AttendanceModel attendance) {
		StudentDto studentDto = new StudentDto(attendance.getStudentId());
		LaboratoryClassDto laboratoryDto = new LaboratoryClassDto(attendance.getLaboratoryId());
		AttendanceDto attendanceDto = attendanceRepository.findByStudentAndLaboratory(studentDto, laboratoryDto);
		
		attendanceDto.setPresent(attendance.getPresent());
		attendanceRepository.save(attendanceDto);
		
		AttendanceModel attendanceModel = mapDtoToModel(attendanceDto);
		
		return attendanceModel;
	}
	
}
