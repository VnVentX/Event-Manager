package g3.com.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import g3.com.dto.EventDTO;
import g3.com.entity.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {
		@Query("Select new g3.com.dto.EventDTO(a.idEvent, a.courseCode, c.programName, b.skill, a.subjectType"
			+ ", d.universityName, a.plannedStartDate, a.plannedEndDate, a.actualStartDate, a.actualEndDate"
			+ ", count(e.idEvent))"
			+ " from Event a left outer join CandidateEvent e on e.idEvent = a.idEvent, SubSubjectType b, CProgram c, SchoolCode d"
			+ " where a.idSubSubject = b.idSubSubject and a.idCProgram = c.idCProgram and a.idSchoolCode = d.idSchoolCode"
			+ " group by a.idEvent, a.courseCode, c.programName, b.skill, a.subjectType, d.universityName"
			+ ", a.plannedStartDate, a.plannedEndDate, a.actualStartDate, a.actualEndDate"
			+ " order by a.idEvent ASC")
	List<EventDTO> getAllEvents();

		@Query("Select new g3.com.dto.EventDTO(a.universityName, count(b.idSchoolCode)) " + 
				"from SchoolCode a left outer join Event b on a.idSchoolCode = b.idSchoolCode " + 
				"where b.plannedStartDate <= :d2 and (b.plannedStartDate >= :d1 or b.plannedEndDate >= :d1) " + 
				"group by a.universityName ")
		List<EventDTO> getEventBySchool(@Param("d1") Date d1, @Param("d2") Date d2);	
		
	@Query("Select a from Event a " + "Where a.idEvent = :idEvent")
	Event getEvent(@Param("idEvent") int idEvent);

	@Query("SELECT COUNT(c.courseCode) FROM Event c where YEAR(c.plannedStartDate) = YEAR(:plannedStartDate)")
	int countEvent(@Param("plannedStartDate") Date plannedStartDate);

	List<Event> findByIdSchoolCode(int idSchoolCode);

	List<Event> findByIdCProgram(int idCProgram);

	// Hieu

	// chart number of candidate
	@Query("Select new g3.com.dto.EventDTO(a.courseCode, a.plannedStartDate, a.plannedEndDate, count(b.idCandidate)) "
			+ "from Event a left outer join CandidateEvent b on a.idEvent = b.idEvent "
			+ "where a.plannedStartDate <= :d2 and (a.plannedStartDate >= :d1 or a.plannedEndDate >= :d1) "
			+ "group by a.courseCode, a.plannedStartDate, a.plannedEndDate " + "order by a.plannedStartDate")
	List<EventDTO> getNumOfCandidate(@Param("d1") Date d1, @Param("d2") Date d2);

	// widgetChartSkill
	@Query("Select new g3.com.dto.EventDTO(a.skill, count(b.idSubSubject)) "
			+ "from SubSubjectType a left outer join Event b on a.idSubSubject = b.idSubSubject "
			+ "where b.actualEndDate >= CURRENT_TIMESTAMP and b.actualStartDate <= CURRENT_TIMESTAMP "
			+ "group by a.skill")
	List<EventDTO> getSkillOnGoing();

	// event overview
	@Query("Select new g3.com.dto.EventDTO(a.courseCode, " + "Case "
			+ "when a.actualStartDate > CURRENT_TIMESTAMP then 'Planning' "
			+ "when a.actualEndDate >= CURRENT_TIMESTAMP and a.actualStartDate <= CURRENT_TIMESTAMP then 'On Going' "
			+ "when a.actualEndDate < CURRENT_TIMESTAMP then 'Done' " + "End) " + "from Event a")
	List<EventDTO> getStatusEvent();
	
	//Check duplicate Event
	@Query("Select a from Event a where a.courseCode = :courseCode")
	Event checkDupEvent(@Param("courseCode") String courseCode);
}
