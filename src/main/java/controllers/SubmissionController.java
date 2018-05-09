package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import business.model.SubmissionModel;
import business.service.ISubmissionService;

@RestController
@RequestMapping("submission")
public class SubmissionController {
	
	private final ISubmissionService submissionService;
	
	@Autowired
	public SubmissionController(ISubmissionService submissionService) {
		 this.submissionService = submissionService;
	}
	
	@GetMapping("")
	 public List<SubmissionModel> getAllSubmissions(@RequestParam(required = false) Long studentId, @RequestParam(required = false) Long assignmentId) {
		 try {
			 if(studentId == null && assignmentId == null) {
				 return submissionService.getAllSubmissions();
			 }
			 else if(studentId == null) {
				 return submissionService.getallByAssignment(assignmentId);
			 }
			 else if(assignmentId == null) {
				 return submissionService.getAllByStudent(studentId);
			 }
			 else {		
				 SubmissionModel m = submissionService.getByStudentAndAssignment(studentId, assignmentId);
				 List<SubmissionModel> s = new ArrayList<SubmissionModel>();
				 s.add(m);
				 return s;
			 }
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	 }
	
	@GetMapping("assignment/{id}")
	 public Map<String, Float> getAllGradesForAssignment(@PathVariable(value = "id") Long assignmentId) {
		 try {
			 return submissionService.getAllGradesForAssignment(assignmentId);
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	 }
	
	@PostMapping("")
	public SubmissionModel saveSubmission(@RequestBody SubmissionModel submission) {
		try {
          return submissionService.saveSubmission(submission);
      } catch (Exception e) {
          e.printStackTrace();
          return null;
      }
	}
	
	@PutMapping("")
	public SubmissionModel updateSubmission(SubmissionModel submission) {
		try {
          return submissionService.updateSubmission(submission);
      } catch (Exception e) {
          e.printStackTrace();
          return null;
      }
	}
	
	@PutMapping("grade")
	 public SubmissionModel updateGrade(Long submissionId, Long assignmentId, float grade) {
		 try {
			 return submissionService.updateGrade(submissionId, assignmentId, grade);
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	 }
	
	@DeleteMapping("")
	public String deleteByStudentAndAssignment(Long studentId, Long assignmentId) {
		try {
			submissionService.deleteByStudentAndAssignment(studentId, assignmentId);
	        return "Submission with student id = " + studentId + " and laboratory id = " + assignmentId +" was successfully deleted!";
	    } catch (Exception e) {
	    	return e.getMessage();
	    }
	}
	
}
