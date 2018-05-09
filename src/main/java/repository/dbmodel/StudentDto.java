package repository.dbmodel;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class StudentDto {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "student_id")
	 private Long studentId;
	 
	 @Column(name = "first_name")
	 private String firstName;
	 
	 @Column(name = "last_name")
	 private String lastName;
	 
	 @Column(name = "email")
	 private String email;
	 
	 @Column(name = "password")
	 private String password;
	 
	 @Column(name = "`group`")
	 private String group;
	 
	 @Column(name = "hobby")
	 private String hobby;
	 
	 @Column(name = "token")
	 private String token;
	 
	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
	 private Set<AttendanceDto> attendance;
	 
	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
	 private Set<SubmissionDto> submission;
	 
	 public StudentDto() {
		 
	 }
	 
	 public StudentDto(Long studentId) {
		 this.studentId = studentId;
	 }
	 
	 public StudentDto(String firstName, String lastName, String email, String password, String group, String hobby, String token) {
		 this.firstName = firstName;
		 this.lastName = lastName;
		 this.email = email;
		 this.password = password;
		 this.group = group;
		 this.hobby = hobby;
		 this.token = token;
	 }
	 
	 public StudentDto(String firstName, String lastName, String email, String group, String hobby, String token) {
		 this.firstName = firstName;
		 this.lastName = lastName;
		 this.email = email;
		 this.group = group;
		 this.hobby = hobby;
		 this.token = token;
	 }

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public Set<AttendanceDto> getAttendance() {
		return attendance;
	}

	public void setAttendance(Set<AttendanceDto> attendance) {
		this.attendance = attendance;
	}

	public Set<SubmissionDto> getSubmission() {
		return submission;
	}

	public void setSubmission(Set<SubmissionDto> submission) {
		this.submission = submission;
	}
	
	public String toString(StudentDto s) {
		return "Student: " + s.getFirstName() + " " + s.getLastName() + " is in group " + s.getGroup() + " and likes to " + s.getHobby();
	}
	
}
