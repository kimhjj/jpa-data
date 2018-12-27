package lab.jpa.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
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
@EqualsAndHashCode(of="rno")
@Entity
@Table(name = "tbl_free_replies", indexes = {@Index(unique=false, columnList="board_bno") }) 
public class FreeBoardReply {
	
	@Id
	@SequenceGenerator (name="reply_seq_gen", sequenceName="reply_seq", allocationSize=1)
	@GeneratedValue(generator="reply_seq_gen")  
	private Long rno;
	private String reply;
	private String replyer;
	
	@CreationTimestamp
	private Timestamp replydate;
	@UpdateTimestamp
	private Timestamp updatedate;
	
	@ManyToOne
	//@JoinColumn(name="board_bno")
	private FreeBoard board;
}
