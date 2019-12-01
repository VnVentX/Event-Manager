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

import g3.com.dto.EventFormDTO;
import lombok.Data;

@Entity
@Table(name = "tblEvent")
@Data
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_event")
	private int idEvent;

	@Column(name = "id_school_code")
	private int idSchoolCode;

	@Column(name = "id_cprogram")
	private int idCProgram;

	@Column(name = "id_sub_subject")
	private int idSubSubject;

	@Column(name = "course_code")
	private String courseCode;

	@Column(name = "subject_type")
	private String subjectType;

	@Column(name = "format_type")
	private String formatType;

	@Column(name = "planned_start_date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date plannedStartDate;

	@Column(name = "planned_end_date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date plannedEndDate;

	@Column(name = "planned_expense")
	private String plannedExpense;

	@Column(name = "budget_code")
	private String budgetCode;

	@Column(name = "actual_start_date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date actualStartDate;

	@Column(name = "actual_end_date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date actualEndDate;

	@Column(name = "actual_expense")
	private String actualExpense;

	@Column(name = "training_feedback")
	private String trainingFeedback;

	@Column(name = "training_feedback_content")
	private String trainingFeedbackContent;

	@Column(name = "training_feedback_teacher")
	private String trainingFeedbackTeacher;

	@Column(name = "training_feedback_organization")
	private String trainingFeedbackOrganization;

	@Column(name = "update_by")
	private String updateBy;

	@Column(name = "update_date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date updateDate;

	@Column(name = "note")
	private String note;

	@Column(name = "isDisable")
	private boolean isDisable;

	public Event() {
		super();
		this.isDisable = false;
	}

	public Event(EventFormDTO dto) {
		this.idEvent = dto.getIdEvent();
		this.courseCode = dto.getCourseCode();
		this.subjectType = dto.getSubjectType();
		this.formatType = dto.getFormatType();
		this.budgetCode = dto.getBudgetCode();
		this.plannedExpense = dto.getPlannedExpense();
		this.plannedStartDate = dto.getPlannedStartDate();
		this.plannedEndDate = dto.getPlannedEndDate();
		this.actualStartDate = dto.getActualStartDate();
		this.actualEndDate = dto.getActualEndDate();
		this.actualExpense = dto.getPlannedExpense();
		this.trainingFeedbackTeacher = dto.getTrainingFeedbackTeacher();
		this.trainingFeedbackOrganization = dto.getTrainingFeedbackOrganization();
		this.trainingFeedbackContent = dto.getTrainingFeedbackContent();
		this.trainingFeedback = dto.getTrainingFeedback();
		this.note = dto.getNote();
		this.isDisable = false;
	}

	public Event(List<String> row) throws ParseException {
		super();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
		this.courseCode = row.get(1);
		this.subjectType = row.get(3);
		this.formatType = row.get(5);
		if (row.get(7) != null) {
			this.plannedStartDate = format.parse(row.get(7));
		}
		if (row.get(8) != null) {
			this.plannedEndDate = format.parse(row.get(8));
		}

		this.plannedExpense = row.get(11);
		this.budgetCode = row.get(12);
		if (row.get(7) != null) {
			this.actualStartDate = format.parse(row.get(13));
		}
		if (row.get(7) != null) {
			this.actualEndDate = format.parse(row.get(14));
		}
		this.actualExpense = row.get(19);
		this.trainingFeedback = row.get(20);
		this.trainingFeedbackContent = row.get(21);
		this.trainingFeedbackTeacher = row.get(22);
		this.trainingFeedbackOrganization = row.get(23);
		this.updateBy = row.get(25);
		if (row.get(7) != null) {
			this.updateDate = format.parse(row.get(26));
		}
		this.note = row.get(24);
	}

}
