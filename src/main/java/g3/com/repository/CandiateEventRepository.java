package g3.com.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import g3.com.dto.CandidateEventDTO;
import g3.com.entity.CandidateEvent;

public interface CandiateEventRepository extends JpaRepository<CandidateEvent, Integer> {

	@Query("Select COUNT(c.id) " + "From CandidateEvent c " + "Where c.idEvent = :idEvent and c.status = :status")
	int findByIdEventAndStatus(@Param("idEvent") int idEvent, @Param("status") String status);

	@Transactional
	@Modifying
	@Query("UPDATE CandidateEvent c SET c.status = :status WHERE c.idEvent = :idEvent AND c.idCandidate = :idCandidate")
	void changeRoleCandidate(@Param("idCandidate") int idCandidate, @Param("status") String status,
			@Param("idEvent") int idEvent);

	@Query("Select new g3.com.dto.CandidateEventDTO(a.id, a.idCandidate, a.idEvent, b.courseCode, b.plannedStartDate, b.plannedEndDate, a.status, a.finalGrade, a.updateBy, a.updateDate) "
			+ "from CandidateEvent a left outer join Event b on b.idEvent = a.idEvent "
			+ "where a.idCandidate =:idCandidate")
	List<CandidateEventDTO> getCandidateEvent(@Param("idCandidate") int idCandidate);

	@Query("Select a"
			+ " From CandidateEvent a"
			+ " Where a.idEvent = ?1 and a.idCandidate = ?2")
	CandidateEvent getByIdEventAndIdCandidate(int idEvent, int idCandidate);
	
	// chart status
	@Query("Select new g3.com.dto.CandidateEventDTO(a.status, count(a.status)) " + "from CandidateEvent a "
			+ "group by a.status")
	List<CandidateEventDTO> getStatus();
	
	@Transactional
	@Modifying
	@Query("UPDATE CandidateEvent c SET c.status = :status, c.finalGrade = :grade, c.updateBy = :upBy, c.updateDate =:upDate WHERE c.id = :id")
	void updateCERepo(@Param("status") String st, @Param("grade") float grade, @Param("upBy") String upBy, @Param("upDate") Date date, @Param("id") int id);
}
