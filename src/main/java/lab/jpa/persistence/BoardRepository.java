package lab.jpa.persistence;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import lab.jpa.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
	public List<Board> findBoardByTitle(String title);
	
	// 작성자에 대한 like % 키워드 %
	public Collection<Board> findByWriterContaining(String writer);
	
	// bno > ? ORDER BY bno DESC
	public Collection<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno);
	
	// bno > ? ORDER BY bno DESC
	public Page<Board> findByBnoGreaterThan(Long bno, Pageable paging);
	
	// bno > ? ORDER BY bno DESC limit ?, ?
	public List<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno, Pageable paging);
	
	/*
	 * @Query("SELECT board FROM Board board WHERE board.bno > 0 ORDER BY board.bno desc"
	 * ) public List<Board> findByPage(Pageable pageable);
	 * 
	 * @Query("SELECT b FROM Board b WHERE b.content LIKE %:content% AND b.bno > 0 ORDER BY b.bno desc"
	 * ) public List<Board> findByContent(@Param("content") String content);
	 */
}
