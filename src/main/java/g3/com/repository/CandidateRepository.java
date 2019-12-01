package g3.com.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import g3.com.dto.CandidateDTO;
import g3.com.entity.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
	@Query("Select new g3.com.dto.CandidateDTO(e.idEvent, c.idCandidate, c.name, c.email, c.phone, c.gender, c.dateOfBirth, e.status) "
			+ "From Candidate c, CandidateEvent e " + "Where c.idCandidate =  e.idCandidate and e.idEvent = :idEvent ")
	List<CandidateDTO> findByIdEvent(@Param("idEvent") int idEvent);

	@Query("Select new g3.com.dto.CandidateDTO(a.idCandidate, a.name, a.email, a.phone, a.gender, a.dateOfBirth, c.universityName, count(d.idCandidate)) "
			+ "from Candidate a left outer join CandidateEvent d on a.idCandidate = d.idCandidate, SchoolCode c "
			+ "where a.idSchoolCode = c.idSchoolCode "
			+ "group by a.idCandidate, a.name, a.email, a.phone, a.gender, a.dateOfBirth, c.universityName")
	List<CandidateDTO> getAllCandidate();
	
	@Query("Select new g3.com.dto.CandidateDTO(a.idCandidate, a.nationalId, a.name, a.email, a.phone, a.facebook, a.gender, a.dateOfBirth, b.universityName, "
			+ "c.skill, a.gpa, a.universityGarduationDate, a.fulltimeWorkingAvailableDate, a.reCer, a.recStatus, a.reDetailNote, a.cvNumber, a.contractType, a.note, a.update_by, a.updateDate) "
			+ "from Candidate a, SchoolCode b, SubSubjectType c "
			+ "where a.idSchoolCode = b.idSchoolCode and a.idSubSubject = c.idSubSubject and a.idCandidate =:idCandidate")
	CandidateDTO getCandidateDetail(@Param("idCandidate")int idCandidate);
	
	@Query("Select new g3.com.dto.CandidateDTO(a.idCandidate ,a.name, a.phone, a.account, a.dateOfBirth, b.universityName) "
			+ "from Candidate a left outer join SchoolCode b on a.idSchoolCode = b.idSchoolCode "
			+ "where a.name =:name and a.account =:account and a.dateOfBirth =:birth and b.universityName =:uniName")
	CandidateDTO checkDupCandidate(@Param("name") String name, @Param("account") String account, @Param("birth") Date dateOfBirth, @Param("uniName") String uniName);
}
