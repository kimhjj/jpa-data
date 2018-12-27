package lab.jpa.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import lab.jpa.domain.Member;

public interface MemberRepository extends CrudRepository<Member, Long>{

}
