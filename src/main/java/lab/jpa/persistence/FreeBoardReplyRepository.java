package lab.jpa.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import lab.jpa.domain.FreeBoard;
import lab.jpa.domain.FreeBoardReply;

public interface FreeBoardReplyRepository extends CrudRepository<FreeBoardReply, Long> {
	
	@Query("SELECT r FROM FreeBoardReply r WHERE r.board = ?1 ORDER BY r.rno ASC")
	public List<FreeBoardReply> getReply(FreeBoard board);
}
