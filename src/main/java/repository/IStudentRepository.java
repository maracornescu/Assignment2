package repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import repository.dbmodel.StudentDto;

@Repository
@Transactional
public interface IStudentRepository extends JpaRepository<StudentDto, Long>{
	
	public StudentDto findByEmail(String email);
	
}
