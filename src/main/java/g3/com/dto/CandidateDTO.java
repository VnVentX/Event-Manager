package g3.com.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CandidateDTO {

	private int idEvent;
	private int idCandidate;
	private int nationalID;
	private int cvNumber;
	private long numOfEvent;
	private float gpa;
	private String name;
	private String email;
	private String phone;
	private String facebook;
	private String gender;
	private String account;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dayOfBirth;
	
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date garduationDate;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date workingDate;
	
	private String universityName;
	private String skill;
	private String reCer;
	private String recStatus;
	private String reDetailNote;
	private String contractType;
	private String note;
	private String updateBy;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date updateDate;
	
	private String canStatus;
	
	public CandidateDTO() {
		super();
	}
	
	public CandidateDTO(int idCandidate, String name, String email, String phone, String gender, Date dayOfBirth,
			String universityName, long numOfEvent) {
		super();
		this.idCandidate = idCandidate;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.dayOfBirth = dayOfBirth;
		this.universityName = universityName;
		this.numOfEvent = numOfEvent;
	}

	public CandidateDTO(int idEvent, int idCandidate, String name, String email, String phone, String gender,
			Date dayOfBirth, String canStatus) {
		super();
		this.idEvent = idEvent;
		this.idCandidate = idCandidate;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.dayOfBirth = dayOfBirth;
		this.canStatus = canStatus;
	}

	public CandidateDTO(int idCandidate, int nationalID, String name, String email, String phone, String facebook,
			String gender, Date dayOfBirth, String universityName, String skill, float gpa,
			Date garduationDate, Date workingDate, String reCer, String recStatus, String reDetailNote, int cvNumber,
			String contractType, String note, String updateBy, Date updateDate) {
		super();
		this.idCandidate = idCandidate;
		this.nationalID = nationalID;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.facebook = facebook;
		this.gender = gender;
		this.dayOfBirth = dayOfBirth;
		this.universityName = universityName;
		this.skill = skill;
		this.gpa = gpa;
		this.garduationDate = garduationDate;
		this.workingDate = workingDate;
		this.reCer = reCer;
		this.recStatus = recStatus;
		this.reDetailNote = reDetailNote;
		this.cvNumber = cvNumber;
		this.contractType = contractType;
		this.note = note;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
	}

	public CandidateDTO(int idCandidate,  String name, String phone, String account, Date dayOfBirth, String universityName) {
		super();
		this.idCandidate = idCandidate;
		this.name = name;
		this.phone = phone;
		this.account = account;
		this.dayOfBirth = dayOfBirth;
		this.universityName = universityName;
	}
	
	
}
