package g3.com.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CandidateEventDTO {
	private int acutalNumberOfTrainees;
	private int acutalNumberOfEnrolled;
	private int acutalNumberOfPassed;
	private int id;
	private int idCandidate;
	private int idEvent;
	private String courseCode;
	private Date plannedStartDate;
	private Date plannedEndDate;
	private String canStatus;
	private float finalGrade;
	private String updateBy;
	private Date updateDate;
	//Hieu
	private String status;
	private long statusCount;
	private int month;
	
	public CandidateEventDTO() {
		super();
	}

	public CandidateEventDTO(int id, int idCandidate, int idEvent, String courseCode, Date plannedStartDate,
			Date plannedEndDate, String canStatus, float finalGrade, String updateBy, Date updateDate) {
		super();
		this.id = id;
		this.idCandidate = idCandidate;
		this.idEvent = idEvent;
		this.courseCode = courseCode;
		this.plannedStartDate = plannedStartDate;
		this.plannedEndDate = plannedEndDate;
		this.canStatus = canStatus;
		this.finalGrade = finalGrade;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
	}

	public CandidateEventDTO(int id, String canStatus, float finalGrade, String updateBy, Date updateDate) {
		super();
		this.id = id;
		this.canStatus = canStatus;
		this.finalGrade = finalGrade;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
	}
	

	public CandidateEventDTO( int month, long statusCount) {
		super();
		this.month = month;
		this.statusCount = statusCount;

	}
	
	public CandidateEventDTO(String status, long statusCount) {
		super();
		this.status = status;
		this.statusCount = statusCount;
	}
}
