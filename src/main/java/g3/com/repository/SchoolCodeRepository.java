package g3.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import g3.com.entity.SchoolCode;

public interface SchoolCodeRepository extends JpaRepository<SchoolCode, Integer>{
	@Query("Select s.idSchoolCode "
			+ "From SchoolCode s "
			+ "Where s.universityName = ?1")
	int findByUniversityName(String name);
}
