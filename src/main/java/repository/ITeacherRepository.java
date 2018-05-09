package repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import repository.dbmodel.TeacherDto;

@Repository
@Transactional
public interface ITeacherRepository extends JpaRepository<TeacherDto, Long> {
	public TeacherDto findByEmail(String email);
}
