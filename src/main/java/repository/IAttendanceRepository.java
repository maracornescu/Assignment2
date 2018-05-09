package repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import repository.dbmodel.AttendanceDto;
import repository.dbmodel.LaboratoryClassDto;
import repository.dbmodel.StudentDto;

@Repository
@Transactional
public interface IAttendanceRepository extends JpaRepository<AttendanceDto, Long> {
	public List<AttendanceDto> findByLaboratory(LaboratoryClassDto laboratory);
	public List<AttendanceDto> findByStudent(StudentDto student);
	public AttendanceDto findByStudentAndLaboratory(StudentDto student, LaboratoryClassDto laboratory);
	void deleteByStudentAndLaboratory(StudentDto student, LaboratoryClassDto laboratory);
}
