package business.service;

import java.util.List;

import business.model.AssignmentModel;

public interface IAssignmentService {
	
	List<AssignmentModel> getAllAssignments();
	AssignmentModel getAssignmentById(Long assignmentId);
	AssignmentModel saveAssignment(AssignmentModel assignment);
	AssignmentModel updateAssignment(Long assignmentId, AssignmentModel assignment);
	void deleteLaboratoryById(Long assignmentId);
	List<AssignmentModel> getAssignmentByLabId(Long labId);

}
