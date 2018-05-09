package business.service;

import java.util.List;
import java.util.Map;

import business.model.SubmissionModel;

public interface ISubmissionService {
	
	List<SubmissionModel> getAllSubmissions();
	List<SubmissionModel> getAllByStudent(Long studentId);
	List<SubmissionModel> getallByAssignment(Long assignmentId);
	SubmissionModel getByStudentAndAssignment(Long studentId, Long assignmentId);
	SubmissionModel saveSubmission(SubmissionModel submission);
	SubmissionModel updateSubmission(SubmissionModel submission);
	void deleteByStudentAndAssignment(Long studentId, Long assignmentId);
	Map<String, Float> getAllGradesForAssignment(Long assignmentId);
	SubmissionModel updateGrade(Long submissionId, Long assignmentId, float grade);

}
