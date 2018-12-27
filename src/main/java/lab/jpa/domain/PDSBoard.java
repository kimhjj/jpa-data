package lab.jpa.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="pid")
@Entity
@Table(name="tbl_pds") 
public class PDSBoard {
	
	@Id
	@SequenceGenerator (name="psdboard_seq_gen", sequenceName="psdboard_seq", allocationSize=1)
	@GeneratedValue(generator="psdboard_seq_gen")  
	private Long pid;
	private String pname;
	private String pwriter;

	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="pdsno")
	private List<PDSFile> files;
}
