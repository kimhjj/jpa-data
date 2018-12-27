package lab.jpa.persistence;

import org.springframework.data.repository.CrudRepository;

import lab.jpa.domain.Profile;

public interface ProfileRepository extends CrudRepository<Profile, Long>{

}
