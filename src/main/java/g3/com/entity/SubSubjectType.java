package g3.com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tblSubSubjectType")
@Data
public class SubSubjectType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_sub_subject")
	private int idSubSubject;
	
	@Column(name = "skill")
	private String skill;
	
	@Column(name = "isDisable")
	private boolean isDisable;

	public SubSubjectType() {
		super();
	}


	
}
