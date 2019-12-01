package g3.com.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name = "tblSchoolCode")
@Data
public class SchoolCode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_school_code")
	private int idSchoolCode;

	@Column(name = "site")
	private String site;

	@Column(name = "university_name")
	private String universityName;

	@Column(name = "faculty_name")
	private String facultyName;

	@Column(name = "university_code")
	private String universityCode;

	@Column(name = "hot_key")
	private int hotKey;

	@Column(name = "faculty_code")
	private String facultyCode;

	@Column(name = "rank")
	private int rank;

	@Column(name = "cooperation_start")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date cooperationStart;

	@Column(name = "note")
	private String note;

	@Column(name = "isDisable")
	private boolean isDisable;

	public SchoolCode() {
		super();
	}

}
