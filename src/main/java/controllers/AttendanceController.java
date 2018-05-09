package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import business.model.AttendanceModel;
import business.model.SubmissionModel;
import business.service.IAttendanceService;

@RestController
@RequestMapping("attendance")
public class AttendanceController {
	
	private final IAttendanceService attendanceService;
	
	@Autowired
	public AttendanceController(IAttendanceService attendanceService) {
		 this.attendanceService = attendanceService;
	}
	
	 @GetMapping("")
	 public List<AttendanceModel> getAllAttendances(@RequestParam(required = false) Long studentId, @RequestParam(required = false) Long laboratoryId) {
		 try {
			 if(studentId == null && laboratoryId == null) {
				 return attendanceService.getAllAttendances();
			 }
			 else if(studentId == null) {
				 return attendanceService.getallByLaboratory(laboratoryId);
			 }
			 else if(laboratoryId == null) {
				 return attendanceService.getAllByStudent(studentId);
			 }
			 else {		
				 AttendanceModel m = attendanceService.getByStudentAndLaboratory(studentId, laboratoryId);
				 List<AttendanceModel> a = new ArrayList<AttendanceModel>();
				 a.add(m);
				 return a;
			 }
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	 }
	 	
	@PostMapping("")
	public AttendanceModel saveAttendance(@RequestBody AttendanceModel attendance) {
		try {
          return attendanceService.saveAttendance(attendance);
      } catch (Exception e) {
          e.printStackTrace();
          return null;
      }
	}
	
	
	@PutMapping("")
	public AttendanceModel updateAttendance(@RequestBody AttendanceModel attendance) {
		try {
          return attendanceService.updateAttendance(attendance);
      } catch (Exception e) {
          e.printStackTrace();
          return null;
      }
	}
	
	@DeleteMapping("")
	public String deleteByStudentAndLaboratory(Long studentId, Long laboratoryId) {
		try {
			attendanceService.deleteByStudentAndLaboratory(studentId, laboratoryId);
	        return "Attendance with student id = " + studentId + " and laboratory id = " + laboratoryId +" was successfully deleted!";
	    } catch (Exception e) {
	    	return e.getMessage();
	    }
	}
	
	/* 
	@GetMapping("student/{id}")
	public List<AttendanceModel> getByStudentId(Long studentId) {
		try {
			return attendanceService.getAllByStudent(studentId);
		} catch (Exception e) {
			e.printStackTrace();
		    return null;
		}
	}
	 
	@GetMapping("laboratory/{id}")
	public List<AttendanceModel> getByLaboratoryId(Long laboratoryId) {
		try {
			return attendanceService.getallByLaboratory(laboratoryId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("student/{id}/laboratory/{id}")
	public AttendanceModel getByStudentAndLaboratory(Long studentId, Long laboratoryId) {
		try {
			return attendanceService.getByStudentAndLaboratory(studentId, laboratoryId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/
}
