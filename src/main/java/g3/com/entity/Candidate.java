package g3.com.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import g3.com.dto.CandidateDTO;
import lombok.Data;

@Entity
@Table(name = "tblCandidate")
@Data
public class Candidate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCandidate")
	private int idCandidate;

	@Column(name = "national_id")
	private int nationalId;

	@Column(name = "account")
	private String account;

	@Column(name = "name")
	private String name;

	@Column(name = "id_school_code")
	private int idSchoolCode;

	@Column(name = "date_of_birth")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateOfBirth;

	@Column(name = "gender")
	private String gender;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "facebook")
	private String facebook;

	@Column(name = "university_garduation_date")
	private Date universityGarduationDate;

	@Column(name = "fulltime_working_available_date")
	private Date fulltimeWorkingAvailableDate;

	@Column(name = "re_cer")
	private String reCer;

	@Column(name = "rec_status")
	private String recStatus;

	@Column(name = "re_detail_note")
	private String reDetailNote;

	@Column(name = "cv_number")
	private int cvNumber;

	@Column(name = "contract_type")
	private String contractType;

	@Column(name = "id_sub_subject")
	private int idSubSubject;

	@Column(name = "gpa")
	private float gpa;

	@Column(name = "note")
	private String note;

	@Column(name = "update_by")
	private String update_by;

	@Column(name = "update_date")
	private Date updateDate;

	@Column(name = "isDisable")
	private boolean isDisable;

	public Candidate() {
		super();
		this.isDisable = false;
	}

	public Candidate(CandidateDTO canDTO) {
		super();
		this.idCandidate = canDTO.getIdCandidate();
		this.nationalId = canDTO.getNationalID();
		this.name = canDTO.getName();
		this.idSchoolCode = Integer.parseInt(canDTO.getUniversityName());
		this.dateOfBirth = canDTO.getDayOfBirth();
		this.gender = canDTO.getGender();
		this.email = canDTO.getEmail();
		this.phone = canDTO.getPhone();
		this.facebook = canDTO.getFacebook();
		this.universityGarduationDate = canDTO.getGarduationDate();
		this.fulltimeWorkingAvailableDate = canDTO.getWorkingDate();
		this.reCer = canDTO.getReCer();
		this.recStatus = canDTO.getRecStatus();
		this.reDetailNote = canDTO.getReDetailNote();
		this.cvNumber = canDTO.getCvNumber();
		this.contractType = canDTO.getContractType();
		this.idSubSubject = Integer.parseInt(canDTO.getSkill());
		this.gpa = canDTO.getGpa();
		this.note = canDTO.getNote();
	}

	public Candidate(List<String> row) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
		this.nationalId = Integer.parseInt(row.get(0));
		this.account = row.get(1);
		this.name = row.get(2);
		if (row.get(5) != null) {
			this.dateOfBirth = format.parse(row.get(5));
		}
		this.gender = row.get(6);
		this.email = row.get(7);
		this.phone = row.get(8);
		this.facebook = (row.get(9));
		if (row.get(10) != null) {
			this.universityGarduationDate = format.parse(row.get(10));
		}
		if (row.get(11) != null) {
			this.fulltimeWorkingAvailableDate = format.parse(row.get(11));
		}
		this.reCer = row.get(12);
		this.recStatus = row.get(13);
		this.contractType = row.get(14);
		this.reDetailNote = row.get(15);
		this.cvNumber = Integer.parseInt(row.get(16));
		this.gpa = Float.parseFloat(row.get(17));
		if (!row.get(18).isEmpty()) {
			this.updateDate = format.parse(row.get(18));
		}
		this.note = row.get(19);
		this.update_by = row.get(20);
	}
}
