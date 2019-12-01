package g3.com.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tbl_account")
@Data
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUser")
	private int idUser;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String phone;
	private String email;
	private Timestamp createDate;
	private Timestamp updateTime;

	@Column(name = "enabled")
	private int enabled;

	public Member() {
		super();
	}

	public Member(Member user) {
		this.idUser = user.idUser;
		this.username = user.username;
		this.password = user.password;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.email = user.email;
		this.enabled = user.enabled;
	}

	

}
