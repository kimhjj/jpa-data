package lab.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="userid")
@Entity
@Table(name="tbl_members")
public class Member {
	
	@Id
	private String userid;
	private String userpw;
	private String username;
}
