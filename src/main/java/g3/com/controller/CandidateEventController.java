package g3.com.controller;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import g3.com.dto.CandidateDTO;
import g3.com.entity.Candidate;
import g3.com.entity.CandidateEvent;
import g3.com.security.CustomUserDetails;
import g3.com.service.ICandidateEventService;
import g3.com.service.ICandidateService;
import g3.com.service.ISchoolCodeService;
import g3.com.service.ISubSubjectTypeService;

@Controller
public class CandidateEventController {

	@Autowired
	ICandidateService candidateService;
	@Autowired
	ISchoolCodeService schoolCodeService;
	@Autowired
	ISubSubjectTypeService subSubjectTypeService;
	@Autowired
	ICandidateEventService candidateEventService;

	@RequestMapping(value = "/candidateEvent/{id}")
	public String showCandidateEventPage(@PathVariable int id, Model model) {
		model.addAttribute("listSubSubjectType", subSubjectTypeService.loadAllSubSubjectType());
		model.addAttribute("listSchool", schoolCodeService.findAllSchool());
		model.addAttribute("idEvent", id);
		return "create_candidate_event";
	}

	@RequestMapping(value = "/saveCandidateEvent/{idEvent}")
	public String saveCandidateEvent(@ModelAttribute("candidate") Candidate candidate, @PathVariable int idEvent) {
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String updateBy = userDetails.getUsername();
		Date updateDate = java.sql.Date.valueOf(LocalDate.now());

		String schoolName = schoolCodeService.searchById(candidate.getIdSchoolCode()).getUniversityName();
		CandidateDTO oldCandidate = candidateService.isDupCandidate(candidate.getName(), candidate.getEmail(),
				candidate.getDateOfBirth(), schoolName);

		if (oldCandidate == null) { // if this candidate is not exist (no id)
			candidate.setAccount(candidate.getEmail());
			candidate.setUpdate_by(updateBy);
			candidate.setUpdateDate(updateDate);
			candidateService.saveCandidate(candidate);
			insertToEvent(idEvent, candidate.getIdCandidate());
		} else {
			if (candidateEventService.findByEventAndCandidate(idEvent, oldCandidate.getIdCandidate()) == null) {
				// if not enrolled in this event
				insertToEvent(idEvent, oldCandidate.getIdCandidate());
			}
		}
		return "redirect:/eventDetail/" + idEvent;
	}

	private boolean insertToEvent(int idEvent, int idCandidate) {
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String updateBy = userDetails.getUsername();
		Date updateDate = java.sql.Date.valueOf(LocalDate.now());
		CandidateEvent candidateEvent = new CandidateEvent();
		candidateEvent.setUpdateBy(updateBy);
		candidateEvent.setUpdateDate(updateDate);
		candidateEvent.setStatus("Active");
		// Add to event
		candidateEvent.setIdEvent(idEvent);
		candidateEvent.setIdCandidate(idCandidate);
		return candidateEventService.insertNew(candidateEvent);
	}
}
