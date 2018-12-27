package lab.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="fno")
@Entity
@Table(name="tbl_pdsfiles")
public class PDSFile {
	
	@Id
	@SequenceGenerator (name="psdfile_seq_gen", sequenceName="psdfile_seq", allocationSize=1)
	@GeneratedValue(generator="psdfile_seq_gen")  
	private Long fno;
	
	private String pdsfile;
}
