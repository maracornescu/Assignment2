package business.service;

import java.util.List;

import business.model.LaboratoryModel;

public interface ILaboratoryService {
	
	List<LaboratoryModel> getAllLaboratory();
	LaboratoryModel getLaboratoryById(Long laboratoryId);
	LaboratoryModel saveLaboratory(LaboratoryModel laboratory);
	LaboratoryModel updateLaboratory(Long laboratoryId, LaboratoryModel laboratory);
	void deleteLaboratoryById(Long laboratoryId);
	List<LaboratoryModel> viewFilteredList(String keyword);
	
}
