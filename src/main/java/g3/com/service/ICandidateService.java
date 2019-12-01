package g3.com.service;

import java.util.Date;
import java.util.List;

import g3.com.dto.CandidateDTO;
import g3.com.entity.Candidate;

public interface ICandidateService {
	
	//Select
	List<CandidateDTO> loadCandidateByIdEvent(int id);
	List<CandidateDTO> showAllCandidate();
	CandidateDTO showCandidateDetail(int id);
	CandidateDTO isDupCandidate(String name, String account, Date dateOfBirth, String uniName);

	//Update
	boolean saveCandidate(Candidate candidate);
}
