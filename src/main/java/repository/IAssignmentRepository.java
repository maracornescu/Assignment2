package repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import repository.dbmodel.AssignmentDto;
import repository.dbmodel.LaboratoryClassDto;

@Repository
@Transactional
public interface IAssignmentRepository extends JpaRepository<AssignmentDto, Long> {
	public AssignmentDto findByAssignmentName(String assignmentName);
	public AssignmentDto findByAssignmentId(Long assignmentId);
	public List<AssignmentDto> findByLaboratory(LaboratoryClassDto laboratory);
};
