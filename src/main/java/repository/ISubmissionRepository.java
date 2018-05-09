package repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import repository.dbmodel.AssignmentDto;
import repository.dbmodel.StudentDto;
import repository.dbmodel.SubmissionDto;

@Repository
@Transactional
public interface ISubmissionRepository extends JpaRepository<SubmissionDto, Long> {
	
	public List<SubmissionDto> findByAssignment(AssignmentDto assignment);
	public List<SubmissionDto> findByStudent(StudentDto student);
	public SubmissionDto findByStudentAndAssignment(StudentDto student, AssignmentDto assignment);
	void deleteByStudentAndAssignment(StudentDto student, AssignmentDto assignment);
	
}
