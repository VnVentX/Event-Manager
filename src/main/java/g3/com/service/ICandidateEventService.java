package g3.com.service;

import java.util.List;

import g3.com.dto.CandidateEventDTO;
import g3.com.entity.CandidateEvent;
import g3.com.myenum.Status;

public interface ICandidateEventService {
	//Select
	List<CandidateEventDTO> loadCandidateEvent(int id);
	List<CandidateEventDTO> getStatusCandidate();
	List<String> getCandidateNumberInYear();
	CandidateEvent findCEByID(int id);
	CandidateEvent findByEventAndCandidate(int idEvent, int idCandidate);
	int countStudentByStatus(int eventID, Status status);
	
	//Update
	void changeCandidate(Integer[] id, String status, int idEvent);
	void updateCE(CandidateEvent ce);
	boolean insertNew(CandidateEvent ce);
}
