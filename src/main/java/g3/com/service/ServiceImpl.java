package g3.com.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import g3.com.dto.CandidateDTO;
import g3.com.dto.CandidateEventDTO;
import g3.com.dto.EventDTO;
import g3.com.dto.EventFormDTO;
import g3.com.entity.CProgram;
import g3.com.entity.Candidate;
import g3.com.entity.CandidateEvent;
import g3.com.entity.Event;
import g3.com.entity.Member;
import g3.com.entity.SchoolCode;
import g3.com.entity.SubSubjectType;
import g3.com.myenum.Status;
import g3.com.repository.CProgramRepository;
import g3.com.repository.CandiateEventRepository;
import g3.com.repository.CandidateRepository;
import g3.com.repository.EventRepository;
import g3.com.repository.MemberRepository;
import g3.com.repository.SchoolCodeRepository;
import g3.com.repository.SubSubjectTypeRepository;

@Service
public class ServiceImpl implements ICandidateEventService, ICandidateService, IEventService, ISchoolCodeService,
		ICProgramService, ISubSubjectTypeService, IMemberService {

	@Autowired
	EventRepository eventRepo;
	@Autowired
	SchoolCodeRepository schoolRepo;
	@Autowired
	CProgramRepository cProgramRepo;
	@Autowired
	SubSubjectTypeRepository subSubjectTypeRepo;
	@Autowired
	CandidateRepository candidateRepo;
	@Autowired
	CandiateEventRepository candiateEventRepo;
	@Autowired
	MemberRepository memberRepository;

	// FOR EVENT SERVICE IMPLEMENTS

	@Override
	public int countEvent(java.util.Date plannedStartDate) {
		return eventRepo.countEvent(plannedStartDate);
	}

	public List<EventDTO> getAllEvent() {
		return eventRepo.getAllEvents();
	}

	public boolean insertEvent(EventFormDTO eventForm) {
		String[] schoolCodeForm = eventForm.getSupplier().split("_");
		String[] courseNameForm = eventForm.getCourseName().split("_");
		String[] subSubjectTypeForm = eventForm.getSubSubjectType().split("_");
		LocalDate now = LocalDate.now();

		Event event = new Event(eventForm);
		event.setIdSchoolCode(Integer.valueOf(schoolCodeForm[0]));
		event.setIdSubSubject(Integer.valueOf(subSubjectTypeForm[0]));
		event.setIdCProgram(Integer.valueOf(courseNameForm[0]));
		// Create course Code
		SimpleDateFormat sdf = new SimpleDateFormat("yy");
		String courseCode = schoolCodeForm[1] + "_" + courseNameForm[1] + "_" + subSubjectTypeForm[1] + "_" + "HCM"
				+ sdf.format(eventForm.getPlannedStartDate()) + "_"
				+ String.format("%03d", countEvent(eventForm.getPlannedStartDate()) + 1);
		event.setCourseCode(courseCode);
		// end
		event.setActualStartDate(eventForm.getPlannedStartDate());
		event.setActualEndDate(eventForm.getPlannedEndDate());
		event.setUpdateDate(java.sql.Date.valueOf(now));
		event.setUpdateBy(eventForm.getUpdateBy());
		return eventRepo.save(event) == null ? false : true;
	}

	@Override
	public Event getEventDetail(int idEvent) {
		return eventRepo.getEvent(idEvent);
	}

	@Override
	public boolean editEvent(Event evt) {
		return eventRepo.save(evt) == null ? false : true;
	}

	@Override
	public List<Event> getBySchoolCode(int id) {
		return eventRepo.findByIdSchoolCode(id);
	}

	@Override
	public List<Event> getByCprogramCode(int id) {
		return eventRepo.findByIdCProgram(id);
	}

	@Override
	public boolean updateCourseCodeBySCode(List<Event> list, String schoolCode) {
		for (Event event : list) {
			StringBuffer bf = new StringBuffer(event.getCourseCode());
			bf.replace(0, bf.indexOf("_"), schoolCode);
			String newCourseCode = bf.toString();
			event.setCourseCode(newCourseCode);
			eventRepo.save(event);
		}
		return true;
	}

	@Override
	public boolean updateCourseCodeByCPCode(List<Event> list, String cProgramCode) {
		for (Event event : list) {
			StringBuffer bf = new StringBuffer(event.getCourseCode());
			bf.replace(bf.indexOf("_") + 1, bf.indexOf("_", bf.indexOf("_") + 1), cProgramCode);
			String newCourseCode = bf.toString();
			event.setCourseCode(newCourseCode);
			eventRepo.save(event);
		}
		return false;
	}

	// REPORT API
	@Override
	public List<EventDTO> getEventBySchool() {
		List<EventDTO> list = new ArrayList<EventDTO>();
		String str1 = "", str2 = "";
		Date d1, d2;
		LocalDate now = LocalDate.now();
		int curMonth = now.getMonthValue();
		int curYear = now.getYear();
		try {
			if (curMonth >= 1 && curMonth <= 3) {
				str1 = curYear + "-" + "01" + "-" + "01";
				str2 = curYear + "-" + "03" + "-" + "31";
			}
			if (curMonth >= 4 && curMonth <= 6) {
				str1 = curYear + "-" + "04" + "-" + "01";
				str2 = curYear + "-" + "06" + "-" + "30";
			}
			if (curMonth >= 7 && curMonth <= 9) {
				str1 = curYear + "-" + "07" + "-" + "01";
				str2 = curYear + "-" + "09" + "-" + "30";
			}
			if (curMonth >= 10 && curMonth <= 12) {
				str1 = curYear + "-" + "10" + "-" + "01";
				str2 = curYear + "-" + "12" + "-" + "31";
			}

			d1 = java.sql.Date.valueOf(str1);
			d2 = java.sql.Date.valueOf(str2);
			list = eventRepo.getEventBySchool(d1, d2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<EventDTO> getNumOfCandidate() {
		List<EventDTO> list = new ArrayList<EventDTO>();
		String str1 = "", str2 = "";
		Date d1, d2;
		LocalDate now = LocalDate.now();
		int curMonth = now.getMonthValue();
		int curYear = now.getYear();
		try {
			if (curMonth >= 1 && curMonth <= 3) {
				str1 = curYear + "-" + "01" + "-" + "01";
				str2 = curYear + "-" + "03" + "-" + "31";
			}
			if (curMonth >= 4 && curMonth <= 6) {
				str1 = curYear + "-" + "04" + "-" + "01";
				str2 = curYear + "-" + "06" + "-" + "30";
			}
			if (curMonth >= 7 && curMonth <= 9) {
				str1 = curYear + "-" + "07" + "-" + "01";
				str2 = curYear + "-" + "09" + "-" + "30";
			}
			if (curMonth >= 10 && curMonth <= 12) {
				str1 = curYear + "-" + "10" + "-" + "01";
				str2 = curYear + "-" + "12" + "-" + "31";
			}

			d1 = java.sql.Date.valueOf(str1);
			d2 = java.sql.Date.valueOf(str2);
			list = eventRepo.getNumOfCandidate(d1, d2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public int getTotalCandidateAtAMonth(int month, int year) {
		int total = 0;
		Date d1 = null, d2 = null;
		String str1, str2;
		int date1 = 1, date2 = 0;
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			date2 = 31;
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			date2 = 30;
		} else if (month == 2) {
			if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
				date2 = 29;
			} else {
				date2 = 28;
				;
			}
		}
		str1 = year + "-" + month + "-" + date1;
		str2 = year + "-" + month + "-" + date2;
		d1 = java.sql.Date.valueOf(str1);
		d2 = java.sql.Date.valueOf(str2);

		try {
			List<EventDTO> list = eventRepo.getNumOfCandidate(d1, d2);
			for (int i = 0; i < list.size(); i++) {
				total += list.get(i).getNum();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

	public List<String> getCandidateNumberInYear() {
		List<String> list = new ArrayList<>();
		LocalDate now = LocalDate.now();
		int curMonth = now.getMonthValue();
		int curYear = now.getYear();
		for (int i = 0; i < curMonth; i++) {
			list.add(getTotalCandidateAtAMonth(i + 1, curYear) + "");
		}
		return list;
	}

	public List<CandidateEventDTO> getStatusCandidate() {
		List<CandidateEventDTO> list = candiateEventRepo.getStatus();
		return list;
	}

	public List<EventDTO> getSkillOnGoing() {
		List<EventDTO> list = eventRepo.getSkillOnGoing();
		return list;
	}

	public List<EventDTO> getStatusEvent() {
		List<EventDTO> list = eventRepo.getStatusEvent();
		return list;
	}

	// END REPORT API

	@Override
	public boolean isDupEvent(String courseCode) {
		return eventRepo.checkDupEvent(courseCode) == null ? false : true;
	}
	// END EVENT SERVICE

	// SCHOOLCODE IMPLEMENTS
	public SchoolCode searchById(int id) {
		return schoolRepo.findById(id).get();
	}

	@Override
	public List<SchoolCode> findAllSchool() {
		return schoolRepo.findAll();
	}

	@Override
	public boolean saveSchoolCode(SchoolCode school) {
		return schoolRepo.save(school) == null ? false : true;
	}

	@Override
	public int getSchoolCodeId(String name) {
		return schoolRepo.findByUniversityName(name);
	}

	@Override
	public List<SubSubjectType> loadAllSubSubjectType() {
		return subSubjectTypeRepo.findAll();
	}

	// END SCHOOL CODE
	// CANDIDATE IMPLEMENTS
	@Override
	public List<CandidateDTO> loadCandidateByIdEvent(int id) {
		return candidateRepo.findByIdEvent(id);
	}

	@Override
	public List<CandidateDTO> showAllCandidate() {
		return candidateRepo.getAllCandidate();
	}

	@Override
	public boolean saveCandidate(Candidate candidate) {
		return candidateRepo.save(candidate) == null ? false : true;
	}

	@Override
	public CandidateDTO showCandidateDetail(int id) {
		return candidateRepo.getCandidateDetail(id);
	}

	@Override
	public CandidateDTO isDupCandidate(String name, String account, Date dateOfBirth, String uniName) {
		return candidateRepo.checkDupCandidate(name, account, dateOfBirth, uniName);
	}
	//END CANDIDATE
	
	//CPROGRAM IMPLEMENTS
	@Override
	public int getCProgramId(String courseName) {
		return cProgramRepo.findByCourseName(courseName);
	}

	@Override
	public List<CProgram> loadAllCProgram() {
		return cProgramRepo.findAll();
	}

	@Override
	public CProgram loadCProgram(int id) {
		return cProgramRepo.getOne(id);
	}

	@Override
	public List<CProgram> loadAvailableCP() {
		return cProgramRepo.availableCP();
	}
	//END CAMPUS PROGRAM
	
	// SUB-SUBJECT TYPE
	@Override
	public SubSubjectType searchByID(int id) {
		return subSubjectTypeRepo.getOne(id);
	}

	@Override
	public int getSubSubjectId(String skillName) {
		return subSubjectTypeRepo.findBySkillName(skillName);
	}
	//END SUB-SUBJECT
	
	// CANDIDATE-EVENT
	@Override
	public boolean insertNew(CandidateEvent ce) {
		return candiateEventRepo.save(ce) != null ? true : false;
	}

	@Override
	public CandidateEvent findByEventAndCandidate(int idEvent, int idCandidate) {
		return candiateEventRepo.getByIdEventAndIdCandidate(idEvent, idCandidate);
	}

	@Override
	public int countStudentByStatus(int eventID, Status status) {
		int result = 0;
		switch (status) {
		case DROPOUT:
			result = candiateEventRepo.findByIdEventAndStatus(eventID, "Drop-Out");
			break;
		case CANCEL:
			result = candiateEventRepo.findByIdEventAndStatus(eventID, "Cancel");
			break;
		case PASSED:
			result = candiateEventRepo.findByIdEventAndStatus(eventID, "Passed");
			break;
		default:
			break;
		}
		return result;
	}

	@Override
	public void updateCE(CandidateEvent ce) {
		candiateEventRepo.updateCERepo(ce.getStatus(), ce.getFinalGrade(), ce.getUpdateBy(), ce.getUpdateDate(),
				ce.getId());
	}

	@Override
	public List<CandidateEventDTO> loadCandidateEvent(int id) {
		return candiateEventRepo.getCandidateEvent(id);
	}

	@Override
	public void changeCandidate(Integer[] idCandidate, String status, int idEvent) {
		for (int id : idCandidate) {
			candiateEventRepo.changeRoleCandidate(id, status, idEvent);
		}
	}

	@Override
	public CandidateEvent findCEByID(int id) {
		return candiateEventRepo.findById(id).get();
	}
	// END CANDIDATE EVENT

	// MEMBER SERVICE
	@Override
	public boolean editProfile(Member member) {
		try {
			memberRepository.updateMemberInfo(member.getUsername(), member.getPhone(), member.getUpdateTime(),
					member.getIdUser());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public Member getInfo(int id) {
		return memberRepository.getOne(id);
	}
}
