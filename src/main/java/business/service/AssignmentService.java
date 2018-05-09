package business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import business.model.AssignmentModel;
import repository.IAssignmentRepository;
import repository.dbmodel.AssignmentDto;
import repository.dbmodel.LaboratoryClassDto;

@Service
public class AssignmentService implements IAssignmentService{
	public final IAssignmentRepository assignmentRepository;
	private ModelMapper modelMapper;
	
	 @Autowired
	public AssignmentService(IAssignmentRepository assignmentRepository) {
		this.assignmentRepository = assignmentRepository;
		modelMapper = new ModelMapper();
	}
	 
	private AssignmentDto mapModelToDto(AssignmentModel assignment) {
		AssignmentDto assignmentDto = new AssignmentDto();
	
		LaboratoryClassDto laboratoryDto = new LaboratoryClassDto(assignment.getLaboratoryId());
		
		assignmentDto.setAssignmentId(assignment.getAssignmentId());
		assignmentDto.setAssignmentName(assignment.getAssignmentName());
		assignmentDto.setDeadline(assignment.getDeadline());
		assignmentDto.setDescription(assignment.getDescription());
		assignmentDto.setLaboratory(laboratoryDto);
		
		return assignmentDto;
	}
	
	private AssignmentModel mapDtoToModel(AssignmentDto assignmentDto) {
		AssignmentModel assignmentModel = new AssignmentModel();
		
		assignmentModel.setAssignmentId(assignmentDto.getAssignmentId());
		assignmentModel.setAssignmentName(assignmentDto.getAssignmentName());
		assignmentModel.setDeadline(assignmentDto.getDeadline());
		assignmentModel.setDescription(assignmentDto.getDescription());
		assignmentModel.setLaboratoryId(assignmentDto.getLaboratory().getLaboratoryId());
		
		return assignmentModel;
	}

	@Override
	public List<AssignmentModel> getAllAssignments() {
		List<AssignmentModel> assignmentModel = new ArrayList<AssignmentModel>();
		List<AssignmentDto> assignmentDto = assignmentRepository.findAll();
	
		for(AssignmentDto a: assignmentDto) {
			
			AssignmentModel assignment = modelMapper.map(a, AssignmentModel.class);
			assignmentModel.add(assignment);
		}
		
		return assignmentModel;
	}

	@Override
	public AssignmentModel getAssignmentById(Long assignmentId) {
		Optional<AssignmentDto> assignmentDto = assignmentRepository.findById(assignmentId);
		AssignmentModel a = modelMapper.map(assignmentDto.get(), AssignmentModel.class);
		return a;
	}

	@Override
	public AssignmentModel saveAssignment(AssignmentModel assignment) {
		AssignmentDto assignmentDto = mapModelToDto(assignment);
		
        if (assignmentRepository.findByAssignmentName(assignment.getAssignmentName()) == null) {
        	assignmentRepository.save(assignmentDto);
            return assignment;
        }
        
        return null;
	}

	@Override
	public AssignmentModel updateAssignment(Long assignmentId, AssignmentModel assignment) {
		Optional<AssignmentDto> updateAssignment = assignmentRepository.findById(assignmentId);

        if (updateAssignment != null) {
        	if(assignment.getAssignmentName() != null)
        		updateAssignment.get().setAssignmentName(assignment.getAssignmentName());
        	if(assignment.getDeadline() != null)
        		updateAssignment.get().setDeadline(assignment.getDeadline());
        	if(assignment.getDescription() != null)
        		updateAssignment.get().setDescription(assignment.getDescription());
       
        	assignmentRepository.save(updateAssignment.get());
            
        	AssignmentModel assignmentModel = modelMapper.map(updateAssignment.get(), AssignmentModel.class);
            
            return assignmentModel;
            
        } else {
            return null;
        }
	}

	@Override
	public void deleteLaboratoryById(Long assignmentId) {
		assignmentRepository.deleteById(assignmentId);
	}
	
	@Override
	public List<AssignmentModel> getAssignmentByLabId(Long labId) {
		LaboratoryClassDto laboratoryDto = new LaboratoryClassDto(labId);
		
		List<AssignmentModel> assignmentModel = new ArrayList<AssignmentModel>();
		List<AssignmentDto> assignmentDto = assignmentRepository.findByLaboratory(laboratoryDto);
	
		for(AssignmentDto a: assignmentDto) {
			AssignmentModel assignment = mapDtoToModel(a);
			assignmentModel.add(assignment);
		}
		
		return assignmentModel;
	}
}
