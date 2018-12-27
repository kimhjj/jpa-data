package lab.jpa.persistence;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import lab.jpa.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
	public List<Board> findBoardByTitle(String title);
	
	// 작성자에 대한 like % 키워드 %
	public Collection<Board> findByWriterContaining(String writer);
	
	// bno > ? ORDER BY bno DESC
	public Collection<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno);
	
	// bno > ? ORDER BY bno DESC limit ?, ?
	public List<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno, Pageable paging);
}
