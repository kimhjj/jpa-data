package lab.jpa;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import lab.jpa.domain.FreeBoard;
import lab.jpa.domain.FreeBoardReply;
import lab.jpa.persistence.FreeBoardReplyRepository;
import lab.jpa.persistence.FreeBoardRepository;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class FreeBoardTest {
	@Autowired
	FreeBoardRepository boardRepo;
	
	@Autowired
	FreeBoardReplyRepository replyRepo;
	 
	@Test
	public void insertDummy(){
		IntStream.range(1,200).forEach(i ->{
			FreeBoard board = new FreeBoard();
			board.setTitle("Free Board ... " + i);
			board.setContent("Free Content.... "+ i);
			board.setWriter("user"+ i%10 );
			
			boardRepo.save(board);
		});
	}
	
	@Transactional
	@Test
	public void insertReply2Way() {
		Optional<FreeBoard> result = boardRepo.findById(199L);
		result.ifPresent(board ->{
			List<FreeBoardReply> replies = board.getReplies();
			
			FreeBoardReply reply = new FreeBoardReply();
			reply.setReply("REPLY..............");
			reply.setReplyer("replyer00");
			reply.setBoard(board);
			replies.add(reply);
			board.setReplies(replies);
			
			boardRepo.save(board);
		});
	}
	
	@Test
	public void insertReply1Way(){
		FreeBoard board = new FreeBoard();
		board.setBno(198L);
		FreeBoardReply reply = new FreeBoardReply();
		reply.setReply("REPLY..............");
		reply.setReplyer("replyer00");
		reply.setBoard(board);
		
		replyRepo.save(reply);
	}
	
	@Test
	public void testListGreaterThan(){
		Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
		boardRepo.findByBnoGreaterThan(0L, page).forEach(board -> {
			System.out.println(board.getBno() +": " +board.getTitle() );
		});
	}

	@Test
	public void testList1(){
		Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
		boardRepo.getPage(page).forEach(arr -> System.out.println(Arrays.toString(arr)));
	}
	 
	@Test
	public void testList52(){
		Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
		boardRepo.getPage52(page).forEach(arr -> System.out.println(Arrays.toString(arr)));
	}
	
	@Test
	@Description("Error?")
	public void testRead(){
		Optional<FreeBoard> result = boardRepo.findById(199L);
		System.out.println(result);
		FreeBoard board = result.get();
		System.out.println(board);
		List<FreeBoardReply> replies = replyRepo.getReply(board);
		replies.forEach(reply -> System.out.println("REPLY: "+ reply));
	}
}

