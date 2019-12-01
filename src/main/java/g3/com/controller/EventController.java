package g3.com.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import g3.com.dto.CandidateDTO;
import g3.com.dto.CandidateEventDTO;
import g3.com.dto.EventFormDTO;
import g3.com.entity.CProgram;
import g3.com.entity.Event;
import g3.com.entity.SchoolCode;
import g3.com.entity.SubSubjectType;
import g3.com.myenum.Status;
import g3.com.security.CustomUserDetails;
import g3.com.service.ICProgramService;
import g3.com.service.ICandidateEventService;
import g3.com.service.ICandidateService;
import g3.com.service.IEventService;
import g3.com.service.ISchoolCodeService;
import g3.com.service.ISubSubjectTypeService;

@Controller
public class EventController {

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

	@RequestMapping(value = { "/homepage", "/" })
	public String showOverviewEventPage(Model model) {
		return "homepage";
	}

	@RequestMapping(value = { "/createEvent" })
	public String showCreateEventPage(Model model) {
		List<SchoolCode> schoolCodeList = schoolCodeService.findAllSchool();
		List<CProgram> cProgramList = cProgramService.loadAvailableCP();
		List<SubSubjectType> subSubjectTypeList = subSubjectTypeService.loadAllSubSubjectType();
		model.addAttribute("listSchool", schoolCodeList);
		model.addAttribute("listCProgram", cProgramList);
		model.addAttribute("listSubSubjectType", subSubjectTypeList);
		return "create_event";
	}

	@RequestMapping(value = "/saveEvent")
	public String insertEvent(Model model, @ModelAttribute("event") EventFormDTO eventForm) {
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		eventForm.setUpdateBy(userDetails.getUsername());
		boolean flag = eventService.insertEvent(eventForm);
		if (flag == false) {
			return "redirect:/createEvent";
		} else {
			return "redirect:/homepage";
		}
	}

	@RequestMapping(value = "/eventDetail/{idEvent}")
	public String showEventDetailPage(@PathVariable int idEvent, Model model) {
		Event event = eventService.getEventDetail(idEvent);
		CProgram cProgram = cProgramService.loadCProgram(event.getIdCProgram());
		SchoolCode schoolCode = schoolCodeService.searchById(event.getIdSchoolCode());
		SubSubjectType subjectType = subSubjectTypeService.searchByID(event.getIdSubSubject());
		List<CandidateDTO> candidateList = candidateService.loadCandidateByIdEvent(event.getIdEvent());
		int plannedStudentNumber = candidateList.size();

		// load number of students
		CandidateEventDTO canDto = new CandidateEventDTO();
		canDto.setAcutalNumberOfEnrolled(
				plannedStudentNumber - candidateEventService.countStudentByStatus(event.getIdEvent(), Status.DROPOUT));
		canDto.setAcutalNumberOfPassed(candidateEventService.countStudentByStatus(event.getIdEvent(), Status.PASSED));
		canDto.setAcutalNumberOfTrainees(
				plannedStudentNumber - candidateEventService.countStudentByStatus(event.getIdEvent(), Status.CANCEL));

		// Add model
		model.addAttribute("eventDTO", event);
		model.addAttribute("cProgram", cProgram);
		model.addAttribute("schoolCode", schoolCode);
		model.addAttribute("subjectType", subjectType);
		model.addAttribute("candidateList", candidateList);
		model.addAttribute("canDto", canDto);
		model.addAttribute("eventStatus", getStatus(event.getActualStartDate(), event.getActualEndDate()));
		// Add select list
		model.addAttribute("listSchool", schoolCodeService.findAllSchool());
		model.addAttribute("listCProgram", cProgramService.loadAvailableCP());
		model.addAttribute("listSubSubjectType", subSubjectTypeService.loadAllSubSubjectType());
		return "event_detail";
	}

	@RequestMapping(value = "/editEvent/{idEvent}")
	public String editEvent(@PathVariable int idEvent, @ModelAttribute("eventDTO") EventFormDTO eventForm) {
		// Get current user info
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		// Create event obj
		String[] schoolCodeForm = eventForm.getSupplier().split("_");
		String[] courseNameForm = eventForm.getCourseName().split("_");
		String[] subSubjectTypeForm = eventForm.getSubSubjectType().split("_");
		Event evt = new Event(eventForm);
		evt.setIdSchoolCode(Integer.valueOf(schoolCodeForm[0]));
		evt.setIdCProgram(Integer.valueOf(courseNameForm[0]));
		evt.setIdSubSubject(Integer.valueOf(subSubjectTypeForm[0]));
		// Set current date and user
		evt.setUpdateBy(userDetails.getUsername());
		evt.setUpdateDate(java.sql.Date.valueOf(LocalDate.now()));
		// Save edited event
		boolean flag = eventService.editEvent(evt);
		if (flag == false) {
			return "redirect:/eventDetail/" + idEvent;
		} else {
			return "redirect:/eventDetail/" + evt.getIdEvent();
		}
	}

	private String getStatus(Date startDate, Date endDate) {
		LocalDate tmp = LocalDate.now();
		Date now = java.sql.Date.valueOf(tmp);
		String result = "Done";
		if (startDate.compareTo(now) == 1) {
			result = "Planned";
		} else if (endDate.compareTo(now) == 1) {
			result = "In progress";
		}
		return result;
	}
}
