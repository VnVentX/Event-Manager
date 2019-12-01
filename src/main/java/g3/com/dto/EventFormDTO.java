package g3.com.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EventFormDTO {
	private int idEvent;
	
	private String courseCode;
	
	private String courseName;
	
	private String subjectType;
	
	private String subSubjectType;
	
	private String formatType;
	
	private String supplier;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date plannedStartDate;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date plannedEndDate;
	
	private String plannedExpense;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date actualStartDate;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date actualEndDate;
	
	private String budgetCode;
	
	private String note;
	
	private String updateBy;
	
	private String trainingFeedback;
	
	private String trainingFeedbackContent;
	
	private String trainingFeedbackTeacher;
	
	private String trainingFeedbackOrganization;

	public EventFormDTO() {
		super();
	}

}
