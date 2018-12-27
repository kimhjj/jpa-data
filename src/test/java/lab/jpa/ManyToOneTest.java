package lab.jpa;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.junit4.SpringRunner;

import lab.jpa.domain.Member;
import lab.jpa.domain.Profile;
import lab.jpa.persistence.MemberRepository;
import lab.jpa.persistence.ProfileRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManyToOneTest {
	@Autowired
	MemberRepository memberRepo;
	
	@Autowired
	ProfileRepository profileRepo;
	
	
	@Test
	public void testInsertMembers() {
		IntStream.range(1, 101).forEach(i -> {
			Member member = new Member();
			member.setUserid("user" + i);
			member.setUserpw("pw" + i);
			member.setUsername("사용자" + i);

			memberRepo.save(member);
		});
	}
    
	@Test
	public void testInsertProfile() {
		Member member = new Member();
		member.setUserid("user1");

		for (int i = 1; i < 5; i++) {
			Profile profile1 = new Profile();
			profile1.setFname("face" + i + ".jpg");
			if (i == 1) {
				profile1.setNewest(true);
			}
			profile1.setMember(member);
			profileRepo.save(profile1);
		}
	}
	
	@Test
	public void testFindProfile() { 
	    Optional<Profile> profile = profileRepo.findById(1L);
	    if (profile != null) {
	    	System.out.println(profile);     	 
    	}
	}
	
	@Test
	@Description("Fetch. Select Join Count")
	public void testFetchJoin1() {
		List<Object[]> result = memberRepo.getMemberWithProfileCount("user1");
		// results.forEach(member -> System.out.println(member));
		result.forEach(arr -> System.out.println(Arrays.toString(arr)));
	}

	@Test
	@Description("Fetch. Select Join Data")
	public void testFetchJoin2() {
		List<Object[]> result = memberRepo.getMemberWithProfile("user1");
		// results.forEach(member -> System.out.println(member));
		result.forEach(arr -> System.out.println(Arrays.toString(arr)));
	}
}

