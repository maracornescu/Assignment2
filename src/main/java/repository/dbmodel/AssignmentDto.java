package repository.dbmodel;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "assignment")
public class AssignmentDto {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "assignment_id")
	 private Long assignmentId;
	 
	 @Column(name = "assignment_name")
	 private String assignmentName;
	 
	 @Column(name = "deadline")
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	 private Date deadline;
	 
	 @Column(name = "description")
	 private String description; 

	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "lab_id", nullable = false)
	 @JsonIgnore
	 private LaboratoryClassDto laboratory;
	 
	 @OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL)
	 private Set<SubmissionDto> submission;
	 
	 public AssignmentDto() {
		 
	 }
	 
	 public AssignmentDto(Long assignmentId) {
		 this.assignmentId = assignmentId;
	 }
	 
	 public AssignmentDto(String assignmentName, Timestamp deadline, String description, LaboratoryClassDto laboratory) {
		 this.assignmentName = assignmentName;
		 this.deadline = deadline;
		 this.description = description;
		 this.laboratory = laboratory;
	 }

	public Long getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(Long assignmentId) {
		this.assignmentId = assignmentId;
	}

	public String getAssignmentName() {
		return assignmentName;
	}

	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public LaboratoryClassDto getLaboratory() {
		return laboratory;
	}

	public void setLaboratory(LaboratoryClassDto laboratoryId) {
		this.laboratory = laboratoryId;
	}

	public Set<SubmissionDto> getSubmission() {
		return submission;
	}

	public void setSubmission(Set<SubmissionDto> submission) {
		this.submission = submission;
	}

}
