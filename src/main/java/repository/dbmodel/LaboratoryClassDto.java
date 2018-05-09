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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "laboratory_class")
public class LaboratoryClassDto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "lab_id")
	private Long laboratoryId;
	
	@Column(name = "lab_number")
	private int laboratoryNumber;
	
	@Column(name = "date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date date;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "curricula")
	private String curricula;
	
	@Column(name = "laboratory_text")
	private String laboratoryText;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "laboratory")
	private Set<AssignmentDto> assignments;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "laboratory")
	private Set<AttendanceDto> attendance;
	
	public LaboratoryClassDto() {
		
	}
	
	public LaboratoryClassDto(Long laboratoryId) {
		this.laboratoryId = laboratoryId;
	}
	
	public LaboratoryClassDto(int laboratoryNumber, Timestamp date, String title, String curricula, String laboratoryText) {
		this.laboratoryNumber = laboratoryNumber;
		this.date = date;
		this.title = title;
		this.curricula = curricula;
		this.laboratoryText = laboratoryText;
	}
	
	
    public Set<AssignmentDto> getAssignments() {
        return assignments;
    }

    public void setAssignments(Set<AssignmentDto> assignments) {
        this.assignments = assignments;
    }
	
	public Long getLaboratoryId() {
		return laboratoryId;
	}
	public void setLaboratoryId(Long laboratoryId) {
		this.laboratoryId = laboratoryId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCurricula() {
		return curricula;
	}
	public void setCurricula(String curricula) {
		this.curricula = curricula;
	}
	public String getLaboratoryText() {
		return laboratoryText;
	}
	public void setLaboratoryText(String laboratoryText) {
		this.laboratoryText = laboratoryText;
	}

	public int getLaboratoryNumber() {
		return laboratoryNumber;
	}

	public void setLaboratoryNumber(int laboratoryNumber) {
		this.laboratoryNumber = laboratoryNumber;
	}
	
	public Set<AttendanceDto> getAttendance() {
		return attendance;
	}

	public void setAttendance(Set<AttendanceDto> attendance) {
		this.attendance = attendance;
	}
	
}
