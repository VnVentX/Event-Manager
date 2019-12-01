package g3.com.service;

import java.util.Date;
import java.util.List;

import g3.com.dto.EventDTO;
import g3.com.dto.EventFormDTO;
import g3.com.entity.Event;

public interface IEventService {

	//Query
	List<EventDTO> getAllEvent();

	List<Event> getBySchoolCode(int id);
	
	List<Event> getByCprogramCode(int id);
	
	List<EventDTO> getNumOfCandidate();
	 
	List<EventDTO> getSkillOnGoing();
	
	List<EventDTO> getStatusEvent();
	List<EventDTO> getEventBySchool();

	Event getEventDetail(int idEvent);
	
	int countEvent(Date plannedStartDate);
	//Update 
	boolean insertEvent(EventFormDTO eventForm);
	
	boolean editEvent(Event evt);
		
	boolean updateCourseCodeBySCode(List<Event> list, String schoolCode);
	
	boolean updateCourseCodeByCPCode(List<Event> list, String cProgramCode);
	
	//Check dup
	boolean isDupEvent(String courseCode);
	
}
