package g3.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import g3.com.entity.CProgram;

public interface CProgramRepository extends JpaRepository<CProgram, Integer> {

	@Query("Select a from CProgram a where a.status='false'")
	List<CProgram> availableCP();

	@Query("Select a.idCProgram from CProgram a Where a.programName = ?1")
	int findByCourseName(String courseName);
}
