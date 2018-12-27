package lab.jpa.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import lab.jpa.domain.PDSBoard;

public interface PDSBoardRepository extends CrudRepository<PDSBoard, Long> {
	
	@Modifying
	@Query("UPDATE FROM PDSFile f SET f.pdsfile = ?2 WHERE f.fno = ?1 ")
	public int updatePDSFile(Long fno, String newFileName);
	
	@Modifying
	@Query("DELETE FROM PDSFile f WHERE f.fno = ?1 ")
	public int deletePDSFile(Long fno);
	
	@Query("SELECT p, COUNT(f) FROM PDSBoard p LEFT OUTER JOIN p.files f " +
			"ON p.pid = f WHERE p.pid > 0 GROUP BY p ORDER BY p.pid DESC ")
	public List<Object[]> getSummary();
}
