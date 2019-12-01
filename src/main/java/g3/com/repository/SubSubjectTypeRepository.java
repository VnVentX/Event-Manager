package g3.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import g3.com.entity.SubSubjectType;

public interface SubSubjectTypeRepository extends JpaRepository<SubSubjectType, Integer> {

	@Query("Select s.idSubSubject " + "From SubSubjectType s " + "Where s.skill = ?1")
	int findBySkillName(String skillName);
}
