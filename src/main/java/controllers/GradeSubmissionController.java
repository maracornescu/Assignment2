package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import business.model.SubmissionModel;
import business.service.ISubmissionService;

@RestController
@RequestMapping("grade")
public class GradeSubmissionController {
	
private final ISubmissionService submissionService;
	
	@Autowired
	public GradeSubmissionController(ISubmissionService submissionService) {
		 this.submissionService = submissionService;
	}
	
	@PutMapping("")
	 public SubmissionModel updateGrade(Long submissionId, Long assignmentId, float grade) {
		 try {
			 return submissionService.updateGrade(submissionId, assignmentId, grade);
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	 }

}
