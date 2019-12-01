package g3.com.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tblCandidateEvent")
@Data
public class CandidateEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "id_candidate")
	private int idCandidate;

	@Column(name = "id_event")
	private int idEvent;

	@Column(name = "status")
	private String status;

	@Column(name = "final_grade")
	private float finalGrade;

	@Column(name = "completion_on_level")
	private String completionOnLevel;

	@Column(name = "update_by")
	private String updateBy;

	@Column(name = "update_date")
	private Date updateDate;

	@Column(name = "isDisable")
	private boolean isDisable;

	public CandidateEvent() {
		super();
	}

}
