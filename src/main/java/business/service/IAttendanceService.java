package business.service;

import java.util.List;

import business.model.AttendanceModel;

public interface IAttendanceService {
	
	List<AttendanceModel> getAllAttendances();
	List<AttendanceModel> getAllByStudent(Long studentId);
	List<AttendanceModel> getallByLaboratory(Long laboratoryId);
	AttendanceModel getByStudentAndLaboratory(Long studentId, Long laboratoryId);
	AttendanceModel saveAttendance(AttendanceModel attendance);
	AttendanceModel updateAttendance(AttendanceModel attendance);
	void deleteByStudentAndLaboratory(Long studentId, Long laboratoryId);

	
}
