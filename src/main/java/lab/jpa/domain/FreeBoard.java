package lab.jpa.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="bno")
@Entity
@Table(name="tbl_freeboards") 
public class FreeBoard {

	@Id
	@SequenceGenerator(name="freeboard_seq_gen", sequenceName="freeboard_seq", allocationSize=1)
	@GeneratedValue(generator="freeboard_seq_gen")  
	private Long bno;
	private String title;
	private String writer;
	private String content;
	
	@CreationTimestamp
	private Timestamp regdate;
	@UpdateTimestamp 
	private Timestamp updatedate;
	
	@OneToMany(mappedBy="board",
			  cascade=CascadeType.ALL,
			  fetch=FetchType.LAZY)
	private List<FreeBoardReply> replies;
}
