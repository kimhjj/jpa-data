package lab.jpa.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import lab.jpa.domain.Member;

public interface MemberRepository extends CrudRepository<Member, Long>{
	@Query("SELECT m.userid, count(p) FROM Member m LEFT OUTER JOIN Profile p "+
			" ON m.userid = p.member WHERE m.userid = ?1 GROUP BY m")
	public List<Object[]> getMemberWithProfileCount(String userid);
	
	
	@Query("SELECT m, p FROM Member m LEFT OUTER JOIN Profile p " + 
			" ON m.userid = p.member WHERE m.userid = ?1 AND p.newest = true")
	public List<Object[]> getMemberWithProfile(String userid);
}
