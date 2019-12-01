	package g3.com.dto;

import java.util.Date;

import lombok.Data;

@Data
public class EventDTO {

	private int idEvent;
	private String courseCode;
	private String programName;
	private String skill;
	private String subjectType;
	private String schoolName;
	private Date plannedStartDate;
	private Date plannedEndDate;
	private Date actualStartDate;
	private Date actualEndDate;
	private long num;
	private String status;

	public EventDTO() {
		super();
	}

	public EventDTO(int idEvent, String courseCode, String programName, String skill, String subjectType,
			String schoolName, Date plannedStartDate, Date plannedEndDate, long num) {
		super();
		this.idEvent = idEvent;
		this.courseCode = courseCode;
		this.programName = programName;
		this.skill = skill;
		this.subjectType = subjectType;
		this.schoolName = schoolName;
		this.plannedStartDate = plannedStartDate;
		this.plannedEndDate = plannedEndDate;
		this.num = num;
	}

	public EventDTO(int idEvent, String courseCode, String programName, String skill, String subjectType,
			String schoolName, String status) {
		super();
		this.idEvent = idEvent;
		this.courseCode = courseCode;
		this.programName = programName;
		this.skill = skill;
		this.subjectType = subjectType;
		this.schoolName = schoolName;
		this.status = status;
	}


	public EventDTO(int idEvent, String courseCode, String programName, String skill, String subjectType,
			String schoolName, Date plannedStartDate, Date plannedEndDate, Date actualStartDate, Date actualEndDate,
			long num) {
		super();
		this.idEvent = idEvent;
		this.courseCode = courseCode;
		this.programName = programName;
		this.skill = skill;
		this.subjectType = subjectType;
		this.schoolName = schoolName;
		this.plannedStartDate = plannedStartDate;
		this.plannedEndDate = plannedEndDate;
		this.actualStartDate = actualStartDate;
		this.actualEndDate = actualEndDate;
		this.num = num;
	}
	
	// Hieu
	public EventDTO(String courseCode, Date plannedStartDate, Date plannedEndDate, long num) {
		super();
		this.courseCode = courseCode;
		this.plannedStartDate = plannedStartDate;
		this.plannedEndDate = plannedEndDate;
		this.num = num;
	}

	public EventDTO(String skill, long num) {
		super();
		this.skill = skill;
		this.num = num;
	}

	public EventDTO(String courseCode, String status) {
		super();
		this.courseCode = courseCode;
		this.status = status;
	}

}
