package lab.jpa;

import java.util.Collection;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import lab.jpa.domain.Board;
import lab.jpa.persistence.BoardRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataJpaQueryTest {
	@Autowired
	BoardRepository repo;
	
	@Test
	public void insertBoard200() {
		for(int i=1; i<=200; i++) {
			Board board = new Board();
			board.setTitle("제목..." + i);
			board.setContent("내용..." + i + "채우기");
			board.setWriter("user0" + (i%10));
			repo.save(board);
		}
	}
	
	@Test
	@Ignore
	public void deleteAllBoard() {
		repo.deleteAll();
	}

	@Test
	public void findByTitleTest() {
		repo.findBoardByTitle("제목...177").forEach(board -> System.out.println(board));
	}
	
	@Test
	public void findByWriterTest() {
		Collection<Board> results = repo.findByWriterContaining("07");
		results.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testBnoOrderBy() {
		Collection<Board> results = repo.findByBnoGreaterThanOrderByBnoDesc(2900L);
		results.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testBnoOrderByPaging() {
		Pageable pageable = PageRequest.of(0, 10);	// start, view
		Collection<Board> results = repo.findByBnoGreaterThanOrderByBnoDesc(0L, pageable);
		results.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testBnoPagingSort() {
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "bno");	// start, view, sort, column
		Page<Board> results = repo.findByBnoGreaterThan(0L, pageable);
		results.forEach(board -> System.out.println(board));
		
		System.out.println(results.hasNext());
		if (results.hasNext()) {
			pageable = results.nextPageable();
			Page<Board> next_results = repo.findByBnoGreaterThan(0L, pageable);
			next_results.forEach(board -> System.out.println(board));
		}
		
		System.out.println(results.hasPrevious());
		if (results.hasPrevious()) {
			pageable = results.previousPageable();
			Page<Board> previous_results = repo.findByBnoGreaterThan(0L, pageable);
			previous_results.forEach(board -> System.out.println(board));
		}
	}
	
	@Test
	public void testByContent() {
		
	}
}

