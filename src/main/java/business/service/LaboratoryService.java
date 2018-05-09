package business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import business.model.LaboratoryModel;
import repository.ILaboratoryClassRepository;
import repository.dbmodel.LaboratoryClassDto;

@Service
public class LaboratoryService implements ILaboratoryService{

	public final ILaboratoryClassRepository laboratoryRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public LaboratoryService(ILaboratoryClassRepository laboratoryRepository) {
		this.laboratoryRepository = laboratoryRepository;
		modelMapper = new ModelMapper();
	}
	
	public List<LaboratoryModel> getAllLaboratory() {
		List<LaboratoryModel> laboratoryModel = new ArrayList<LaboratoryModel>();
		List<LaboratoryClassDto> laboratoryDto = laboratoryRepository.findAll();
	
		for(LaboratoryClassDto l: laboratoryDto) {
			
			LaboratoryModel laboratory = modelMapper.map(l, LaboratoryModel.class);
			laboratoryModel.add(laboratory);
		}
		
		return laboratoryModel;
	}
	
	public LaboratoryModel getLaboratoryById(Long laboratoryId) {
		Optional<LaboratoryClassDto> laboratoryDto = laboratoryRepository.findById(laboratoryId);
		LaboratoryModel l = modelMapper.map(laboratoryDto.get(), LaboratoryModel.class);
		return l;
	}
	
		
	public LaboratoryModel saveLaboratory(LaboratoryModel laboratory) {

		LaboratoryClassDto laboratoryDto = modelMapper.map(laboratory, LaboratoryClassDto.class);
		
        if (laboratoryRepository.findByTitle(laboratory.getTitle()) == null) {
        	laboratoryRepository.save(laboratoryDto);
            return laboratory;
        }
        
        return null;
    }

    public LaboratoryModel updateLaboratory(Long laboratoryId, LaboratoryModel laboratory) {
       
    	Optional<LaboratoryClassDto> updateLaboratory = laboratoryRepository.findById(laboratoryId);

        if (updateLaboratory != null) {
        	if(laboratory.getTitle() != null)	
        		updateLaboratory.get().setTitle(laboratory.getTitle());
        	if(laboratory.getLaboratoryText() != null)
        		updateLaboratory.get().setLaboratoryText(laboratory.getLaboratoryText());
        	if(laboratory.getCurricula() != null)
        		updateLaboratory.get().setCurricula(laboratory.getCurricula());
     	
        	laboratoryRepository.save(updateLaboratory.get());
            
        	LaboratoryModel laboratoryModel = modelMapper.map(updateLaboratory.get(), LaboratoryModel.class);
            
            return laboratoryModel;
            
        } else {
            return null;
        }
    }
    
    public void deleteLaboratoryById(Long laboratoryId) {
    	laboratoryRepository.deleteById(laboratoryId);   
    }

	public List<LaboratoryModel> viewFilteredList(String keyword) {
		List<LaboratoryModel> laboratoryModel = new ArrayList<LaboratoryModel>();
		List<LaboratoryClassDto> laboratoryDto = laboratoryRepository.findAll();
		
		for(LaboratoryClassDto l: laboratoryDto) {
			if(l.getCurricula().contains(keyword) || l.getLaboratoryText().contains(keyword)) {
				LaboratoryModel laboratory = modelMapper.map(l, LaboratoryModel.class);
				laboratoryModel.add(laboratory);
			}
		}
		
		return laboratoryModel;
	}
}
