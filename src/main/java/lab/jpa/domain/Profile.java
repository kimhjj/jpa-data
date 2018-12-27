package lab.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude="member")
@EqualsAndHashCode(of="fname")
@Entity
@Table(name="tbl_profile")
public class Profile {

	@Id
	@SequenceGenerator (name="profile_seq_gen", sequenceName="profile_seq", allocationSize=1)
	@GeneratedValue(generator="profile_seq_gen")  
	private Long fno;

	private String fname;

	private boolean newest;

	@ManyToOne
	private Member member;
}
