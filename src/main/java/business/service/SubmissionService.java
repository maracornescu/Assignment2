package business.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import business.model.SubmissionModel;
import repository.ISubmissionRepository;
import repository.dbmodel.AssignmentDto;
import repository.dbmodel.StudentDto;
import repository.dbmodel.SubmissionDto;

@Service
public class SubmissionService implements ISubmissionService{

	public final ISubmissionRepository submissionRepository;
	
	@Autowired
	public SubmissionService(ISubmissionRepository submissionRepository) {
		this.submissionRepository = submissionRepository;
	}
	
	private SubmissionDto mapModelToDto(SubmissionModel submission) {
		SubmissionDto submissionDto = new SubmissionDto();
		
		StudentDto studentDto = new StudentDto(submission.getStudentId());
		AssignmentDto assignmentDto = new AssignmentDto(submission.getAssignmentId());
		
		submissionDto.setStudent(studentDto);
		submissionDto.setAssignment(assignmentDto);
		submissionDto.setGitLink(submission.getGitLink());
		submissionDto.setRemark(submission.getRemark());
		submissionDto.setGrade(submission.getGrade());
		submissionDto.setNumberOfSubmissions(submission.getNumberOfSubmissions());
	
		return submissionDto;
	}
	
	private SubmissionModel mapDtoToModel(SubmissionDto submissionDto) {
		SubmissionModel submissionModel = new SubmissionModel();
		
		submissionModel.setStudentId(submissionDto.getStudent().getStudentId());
		submissionModel.setAssignmentId(submissionDto.getAssignment().getAssignmentId());
		submissionModel.setGitLink(submissionDto.getGitLink());
		submissionModel.setRemark(submissionDto.getRemark());
		submissionModel.setGrade(submissionDto.getGrade());
		submissionModel.setNumberOfSubmissions(submissionDto.getNumberOfSubmissions());
	
		return submissionModel;
	}	

	@Override
	public List<SubmissionModel> getAllSubmissions() {
		List<SubmissionModel> submissionModel = new ArrayList<SubmissionModel>();
		List<SubmissionDto> submissionDto = submissionRepository.findAll();
	
		for(SubmissionDto s: submissionDto) {
			SubmissionModel submission = mapDtoToModel(s);
			submissionModel.add(submission);
		}
		
		return submissionModel;
	}

	@Override
	public List<SubmissionModel> getAllByStudent(Long studentId) {
		StudentDto studentDto = new StudentDto(studentId);
		
		List<SubmissionModel> submissionModel = new ArrayList<SubmissionModel>();
		List<SubmissionDto> submissionDto = submissionRepository.findByStudent(studentDto);
		
		for(SubmissionDto s: submissionDto) {
			SubmissionModel submission = mapDtoToModel(s);
			submissionModel.add(submission);
		}
		
		return submissionModel;
	}

	@Override
	public List<SubmissionModel> getallByAssignment(Long assignmentId) {
		AssignmentDto assignmentDto = new AssignmentDto(assignmentId);
		
		List<SubmissionModel> submissionModel = new ArrayList<SubmissionModel>();
		List<SubmissionDto> submissionDto = submissionRepository.findByAssignment(assignmentDto);
		
		for(SubmissionDto s: submissionDto) {
			SubmissionModel submission = mapDtoToModel(s);
			submissionModel.add(submission);
		}
		
		return submissionModel;
	}

	@Override
	public SubmissionModel getByStudentAndAssignment(Long studentId, Long assignmentId) {
		StudentDto studentDto = new StudentDto(studentId);
		AssignmentDto assignmentDto = new AssignmentDto(assignmentId);
		
		SubmissionDto submissionDto = submissionRepository.findByStudentAndAssignment(studentDto, assignmentDto);
		SubmissionModel submissionModel = mapDtoToModel(submissionDto);
		
		return submissionModel;
	}

	@Override
	public SubmissionModel saveSubmission(SubmissionModel submission) {
		StudentDto studentDto = new StudentDto(submission.getStudentId());
		AssignmentDto assignmentDto = new AssignmentDto(submission.getAssignmentId());
		SubmissionDto submissionDto = mapModelToDto(submission);
		
		//Date date = new Date();
		
		//&& submissionDto.getAssignment().getDeadline().after(date)
		
		if(submissionRepository.findByStudentAndAssignment(studentDto, assignmentDto) == null ) {
			submissionDto.setNumberOfSubmissions(1);
			submissionRepository.save(submissionDto);
		}
		return submission;
	}

	@Override
	public SubmissionModel updateSubmission(SubmissionModel submission) {
		StudentDto studentDto = new StudentDto(submission.getStudentId());
		AssignmentDto assignmentDto = new AssignmentDto(submission.getAssignmentId());
		SubmissionDto submissionDto = submissionRepository.findByStudentAndAssignment(studentDto, assignmentDto);
		SubmissionModel s;
		

		if(submissionDto.getNumberOfSubmissions() > 2) {
			s = mapDtoToModel(submissionDto);
		}
		else {
			if(submission.getGitLink() != null) {
				submissionDto.setGitLink(submission.getGitLink());
			}
			if(submission.getRemark() != null) {
				submissionDto.setRemark(submission.getRemark());
			}
			if(submission.getGrade() != 0) {
				submissionDto.setGrade(submissionDto.getGrade());
			}
			submissionDto.setNumberOfSubmissions(submissionDto.getNumberOfSubmissions() + 1);
		
			s = mapDtoToModel(submissionDto);
			submissionRepository.save(submissionDto);
		}
		
		return s;
	}
	
	@Override
	public SubmissionModel updateGrade(Long submissionId, Long assignmentId, float grade) {
		StudentDto studentDto = new StudentDto(submissionId);
		AssignmentDto assignmentDto = new AssignmentDto(assignmentId);
		SubmissionDto submissionDto = submissionRepository.findByStudentAndAssignment(studentDto, assignmentDto);
	
		if(grade != 0) {
			submissionDto.setGrade(grade);
		}
		
		submissionRepository.save(submissionDto);
		SubmissionModel submission = mapDtoToModel(submissionDto);
		
		return submission;
	}

	@Override
	public void deleteByStudentAndAssignment(Long studentId, Long assignmentId) {
		StudentDto studentDto = new StudentDto(studentId);
		AssignmentDto assignmentDto = new AssignmentDto(assignmentId);
		submissionRepository.deleteByStudentAndAssignment(studentDto, assignmentDto);
	}

	@Override
	public Map<String, Float> getAllGradesForAssignment(Long assignmentId) {
		
		Map<String, Float> gradesForAssignment = new HashMap<String, Float>();
		AssignmentDto assignmentDto = new AssignmentDto(assignmentId);
		
		List<SubmissionDto> submissionDto = submissionRepository.findByAssignment(assignmentDto);
		
		for(SubmissionDto s: submissionDto) {
			gradesForAssignment.put(s.getStudent().getFirstName(), s.getGrade());
		}
		
		return gradesForAssignment;
	}

}
