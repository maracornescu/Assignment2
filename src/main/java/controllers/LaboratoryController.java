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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import business.model.LaboratoryModel;
import business.service.ILaboratoryService;

@RestController
@RequestMapping("laboratory")
public class LaboratoryController {
	
	private final ILaboratoryService laboratoryService;
	
	 @Autowired
	 public LaboratoryController(ILaboratoryService laboratoryService) {
		 this.laboratoryService = laboratoryService;
	 }

	 @GetMapping("")
	 public List<LaboratoryModel> getAllLaboratory(@RequestParam(required = false) String keyword) {
		 try {
			 if(keyword == null) {
				 return laboratoryService.getAllLaboratory();
			 }
			 else {
				 return laboratoryService.viewFilteredList(keyword);
			 }
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	 }
	 
	@GetMapping("{id}")
	public LaboratoryModel getLaboratoryById(@PathVariable(value = "id") Long laboratoryId) {
		try {
			 return laboratoryService.getLaboratoryById(laboratoryId);
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	}
	
	
	@PostMapping("")
	public LaboratoryModel saveLaboratory(@RequestBody LaboratoryModel laboratory) {
		try {
           return laboratoryService.saveLaboratory(laboratory);
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
	}
	
	@PutMapping("{id}")
	public LaboratoryModel updateLaboratory(@PathVariable(value = "id") Long laboratoryId, LaboratoryModel laboratory) {
		try {
           return laboratoryService.updateLaboratory(laboratoryId, laboratory);
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
	}
	
	@DeleteMapping("{id}")
	public String deleteLaboratoryById(@PathVariable(value = "id") Long laboratoryId) {
		try {
			laboratoryService.deleteLaboratoryById(laboratoryId);
	        return "Laboratory with id = " + laboratoryId + " successful deleted!";
	    } catch (Exception e) {
	    	return e.getMessage();
	    }
	}
	
	/*
	 @GetMapping("test")
	 public List<LaboratoryModel> getAllLaboratories() {
		 try {
			return laboratoryService.getAllLaboratory();
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	 }*/
	
	/*
	@GetMapping("getFilteredList")
	public List<LaboratoryModel> viewFilteredList(String keyword) {
		 try {
			 return laboratoryService.viewFilteredList(keyword);
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	} */

}
