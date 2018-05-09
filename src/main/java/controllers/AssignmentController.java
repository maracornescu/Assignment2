package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import business.model.AssignmentModel;
import business.service.IAssignmentService;

@RestController
@RequestMapping("assignment")
public class AssignmentController {
	
	private final IAssignmentService assignmentService;
	
	 @Autowired
	 public AssignmentController(IAssignmentService assignmentService) {
		 this.assignmentService = assignmentService;
	 }

	 @GetMapping("")
	 public List<AssignmentModel> getAllAssignments() {
		 try {
			 return assignmentService.getAllAssignments();
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	 }
	 
	@GetMapping("{id}")
	public AssignmentModel getAssignmentById(@PathVariable(value = "id") Long assignmentId) {
		try {
			 return assignmentService.getAssignmentById(assignmentId);
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	}
	
	@GetMapping("laboratory/{id}")
	public List<AssignmentModel> getAssignmentByLaboratoryId(@PathVariable(value = "id") Long laboratoryId) {
		try {
			 return assignmentService.getAssignmentByLabId(laboratoryId);
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	}
	
	@PostMapping("")
	public AssignmentModel saveAssignment(@RequestBody AssignmentModel assignment) {
		try {
          return assignmentService.saveAssignment(assignment);
      } catch (Exception e) {
          e.printStackTrace();
          return null;
      }
	}
	
	@PutMapping("{id}")
	public AssignmentModel updateAssignment(@PathVariable(value = "id") Long assignmentId, AssignmentModel assignment) {
		try {
          return assignmentService.updateAssignment(assignmentId, assignment);
      } catch (Exception e) {
          e.printStackTrace();
          return null;
      }
	}
	
	@DeleteMapping("{id}")
	public String deleteLaboratoryById(@PathVariable(value = "id") Long assignmentId) {
		try {
			assignmentService.deleteLaboratoryById(assignmentId);
	        return "Assignment with id = " + assignmentId + " successful deleted!";
	    } catch (Exception e) {
	    	return e.getMessage();
	    }
	}

}
