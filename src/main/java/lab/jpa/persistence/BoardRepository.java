package lab.jpa.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lab.jpa.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

}
