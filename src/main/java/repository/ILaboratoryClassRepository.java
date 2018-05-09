package repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import repository.dbmodel.LaboratoryClassDto;

@Repository
@Transactional
public interface ILaboratoryClassRepository extends JpaRepository<LaboratoryClassDto, Long> {
	public LaboratoryClassDto findByTitle(String title);
	public LaboratoryClassDto findByLaboratoryId(Long laboratoryId);
}
