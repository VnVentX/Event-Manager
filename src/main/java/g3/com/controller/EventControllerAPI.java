package g3.com.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import g3.com.dto.CandidateDTO;
import g3.com.dto.CandidateEventDTO;
import g3.com.dto.EventDTO;
import g3.com.entity.CProgram;
import g3.com.entity.Event;
import g3.com.entity.SchoolCode;
import g3.com.mypojo.CandidatePojo;
import g3.com.service.ICProgramService;
import g3.com.service.ICandidateEventService;
import g3.com.service.ICandidateService;
import g3.com.service.IEventService;
import g3.com.service.ISchoolCodeService;
import g3.com.service.ISubSubjectTypeService;

@RestController
@RequestMapping(value = "/api")
public class EventControllerAPI {

	@Autowired
	IEventService eventService;
	@Autowired
	ISchoolCodeService schoolCodeService;
	@Autowired
	ICProgramService cProgramService;
	@Autowired
	ISubSubjectTypeService subSubjectTypeService;
	@Autowired
	ICandidateService candidateService;
	@Autowired
	ICandidateEventService candidateEventService;

	@RequestMapping(value = "/campusLink")
	public List<CProgram> showCampusLink(Model model) {
		return cProgramService.loadAllCProgram();
	}

	// REPORT API
	@GetMapping(value = "/report6")
	public List<EventDTO> getEventBySchool() {
		return eventService.getEventBySchool();
	}

	@GetMapping(value = "/report5")
	public List<String> getCandidateNumberInYear() {
		return candidateEventService.getCandidateNumberInYear();
	}

	@GetMapping(value = "/report4")
	public List<EventDTO> getStatusEvent() {
		return eventService.getStatusEvent();
	}

	@GetMapping(value = "/report3")
	public List<EventDTO> getSkillOnGoing() {
		return eventService.getSkillOnGoing();
	}

	@GetMapping(value = "/report2")
	public List<CandidateEventDTO> getStatus(Model model) {
		return candidateEventService.getStatusCandidate();
	}

	@GetMapping(value = "/report")
	public List<EventDTO> report(Model model) {
		return eventService.getNumOfCandidate();
	}
	// END REPORT API

	// EVENT API
	@GetMapping(value = "/eventOverview")
	public List<EventDTO> showOverviewEventPageAPI() {
		List<EventDTO> eventList = eventService.getAllEvent();
		for (EventDTO eventDTO : eventList) {
			String status = getStatus(eventDTO.getActualStartDate(), eventDTO.getActualEndDate());
			eventDTO.setStatus(status);
		}
		return eventList;
	}

	@GetMapping(value = "/schoolCode")
	public List<SchoolCode> showSchoolCodeAPI() {
		return schoolCodeService.findAllSchool();
	}

	@GetMapping(value = { "/manageCandidate" })
	public List<CandidateDTO> showManageCandidatePage() {
		return candidateService.showAllCandidate();
	}

	@GetMapping(value = "/showDetail/{idEvent}")
	public List<CandidateDTO> showEventDetailPage(@PathVariable int idEvent) {
		Event event = eventService.getEventDetail(idEvent);
		return candidateService.loadCandidateByIdEvent(event.getIdEvent());
	}

	@RequestMapping(value = "/eventDetail/{idEvent}")
	public Event getEventDetail(@PathVariable int idEvent) {
		return eventService.getEventDetail(idEvent);
	}

	@PostMapping(value = "/changeStatus", produces = "application/json")
	public @ResponseBody void changeRoleCandidate(@RequestBody CandidatePojo obj) {
		List<Integer> tmpArr = obj.getId();
		Integer[] finalIdArr = tmpArr.toArray(new Integer[tmpArr.size()]);
		candidateEventService.changeCandidate(finalIdArr, obj.getStatus(), obj.getIdEvent());
	}
	// END EVENT API

	private String getStatus(Date startDate, Date endDate) {
		LocalDate tmp = LocalDate.now();
		Date now = java.sql.Date.valueOf(tmp);
		String result = "Done";
		if (startDate.compareTo(now) == 1) {
			result = "Planning";
		} else if (endDate.compareTo(now) == 1) {
			result = "In progress";
		}
		return result;
	}
}
