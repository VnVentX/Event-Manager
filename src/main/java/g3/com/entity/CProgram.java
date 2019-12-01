package g3.com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "tblCprogram")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class CProgram {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cprogram")
	private int idCProgram;
	
	@Column(name = "program_name")
	private String programName;
	
	@Column(name = "program_code")
	private String programCode;
	
	@Column(name = "time")
	private int time;
	
	@Column(name = "isDisable")
	private boolean status;

	public CProgram() {
		super();
	}

}
