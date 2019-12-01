package g3.com.controller;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import g3.com.dto.CandidateDTO;
import g3.com.entity.Candidate;
import g3.com.entity.CandidateEvent;
import g3.com.entity.Event;
import g3.com.mypojo.ExcelPojo;
import g3.com.security.CustomUserDetails;
import g3.com.service.ICProgramService;
import g3.com.service.ICandidateEventService;
import g3.com.service.ICandidateService;
import g3.com.service.IEventService;
import g3.com.service.ISchoolCodeService;
import g3.com.service.ISubSubjectTypeService;

@RestController
@RequestMapping(value = "/api")
public class ImportAPI {

	@Autowired
	IEventService eventService;
	@Autowired
	ICandidateService canService;
	@Autowired
	ISchoolCodeService schoolCodeService;
	@Autowired
	ISubSubjectTypeService subjectTypeService;
	@Autowired
	ICProgramService cprogramService;
	@Autowired
	ICandidateEventService candidateEventService;

	@PostMapping(value = "/importData", produces = "application/json; charset=UTF-8", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ExcelPojo> importData(@RequestBody ExcelPojo obj) throws IOException {
		List<List<String>> data = obj.getData();
		for (List<String> s : data) {
			for (String str : s) {
				System.out.println(str);
			}
		}
		return new ResponseEntity<ExcelPojo>(obj, HttpStatus.OK);
	}

	@PostMapping(value = "/importCandidateEvent/{idEvent}", produces = "application/json; charset=UTF-8", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody void insertCandidateEvent(@RequestBody ExcelPojo can, @PathVariable int idEvent)
			throws ParseException, IOException {
		List<List<String>> data = can.getData();
		List<Candidate> canList = parseToCandidateList(data);
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String updateBy = userDetails.getUsername();
		Date updateDate = java.sql.Date.valueOf(LocalDate.now());

		if (canList != null) {
			for (Candidate candidate : canList) {
				String schoolName = schoolCodeService.searchById(candidate.getIdSchoolCode()).getUniversityName();
				CandidateDTO oldCandidate = canService.isDupCandidate(candidate.getName(), candidate.getEmail(),
						candidate.getDateOfBirth(), schoolName);
				if (oldCandidate == null) { // if this candidate is not exist (no id)
					candidate.setUpdate_by(updateBy);
					candidate.setUpdateDate(updateDate);
					canService.saveCandidate(candidate);
					insertToEvent(idEvent, candidate.getIdCandidate());
				} else {
					if (candidateEventService.findByEventAndCandidate(idEvent, oldCandidate.getIdCandidate()) == null) {
						// if not enrolled in this event
						insertToEvent(idEvent, oldCandidate.getIdCandidate());
					}
				}
			}
		}
	}

	@PostMapping(value = "/importCan", produces = "application/json; charset=UTF-8", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody void insertCandidate(@RequestBody ExcelPojo can) throws ParseException, IOException {
		List<List<String>> data = can.getData();
		List<Candidate> canList = parseToCandidateList(data);
		if (canList != null) {
			for (Candidate candidate : canList) {
				// Checking existed Candidate
				String schoolName = schoolCodeService.searchById(candidate.getIdSchoolCode()).getUniversityName();
				CandidateDTO tmp = canService.isDupCandidate(candidate.getName(), candidate.getAccount(),
						candidate.getDateOfBirth(), schoolName);
				if (tmp == null) {
					canService.saveCandidate(candidate);
				} else {
					canList = null;
					break;
				}
			}
		}
	}

	private List<Candidate> parseToCandidateList(List<List<String>> data) throws ParseException {
		ArrayList<Candidate> canList = new ArrayList<>();
		// Remove header row
		data.remove(0);
		for (List<String> row : data) {
			Candidate candidate = new Candidate(row);
			candidate.setIdSchoolCode(schoolCodeService.getSchoolCodeId(row.get(3)));
			candidate.setIdSubSubject(subjectTypeService.getSubSubjectId(row.get(4)));
			canList.add(candidate);
		}
		return canList;
	}

	@PostMapping(value = "/importEvent", produces = "application/json; charset=UTF-8", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody void insertEvent(@RequestBody ExcelPojo obj) throws ParseException {
		List<List<String>> data = obj.getData();
		List<Event> eventList = parseToEventList(data);
		if (eventList != null) {
			for (Event event : eventList) {
				eventService.editEvent(event);
			}
		}
	}

	private List<Event> parseToEventList(List<List<String>> data) throws ParseException {
		ArrayList<Event> eventList = new ArrayList<>();
		data.remove(0);
		for (List<String> row : data) { // Checking existed Event
			boolean isExisting = eventService.isDupEvent(row.get(1));
			if (!isExisting) { // if not exist Event event =
				Event event = new Event(row);
				event.setIdCProgram(cprogramService.getCProgramId(row.get(2)));
				event.setIdSubSubject(subjectTypeService.getSubSubjectId(row.get(4)));
				event.setIdSchoolCode(schoolCodeService.getSchoolCodeId(row.get(6)));
				eventList.add(event);
			} else {
				eventList = null;
				break;
			}
		}
		return eventList;
	}

	// @PostMapping(value = "/importParam", produces = "application/json;
	// charset=UTF-8", consumes = MediaType.APPLICATION_JSON_VALUE)

//	@PostMapping(value = "/importSchoolCode", produces = "application/json; charset=UTF-8", consumes = MediaType.APPLICATION_JSON_VALUE)
//	// Test Data
//	private void writeToFile(List<List<String>> data) throws IOException {
//		FileWriter fileWriter = new FileWriter("samplefile2.txt");
//		PrintWriter printWriter = new PrintWriter(fileWriter);
//		for (List<String> rows : data) {
//			String fileContent = rows.toString() + " Columns:" + rows.size();
//			printWriter.println(fileContent);
//		}
//		printWriter.close();
//	}

//	private void writeCandidate(List<Candidate> data) throws IOException {
//		FileWriter fileWriter = new FileWriter("candidate.txt");
//		PrintWriter printWriter = new PrintWriter(fileWriter);
//		for (Candidate rows : data) {
//			String fileContent = rows.toString();
//			printWriter.println(fileContent);
//		}
//		printWriter.close();
//	}
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