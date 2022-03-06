package com.project.employee.spring.employeeReg;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	@Autowired
	private UserRepository repo ;
	
	@Autowired
	private TestEntityManager em ;
	
	@Test
	public void testCreateUser() {
		
		User user = new User();
		user.setEmail("sumitshama123@gmail.com");
		user.setPassword("sumit123");
		user.setFirstName("Sumit");
		user.setLastName("Sharma");
		
		User savedUser = repo.save(user);
		User existUser = em.find(User.class, savedUser.getId());
		
		assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
	}
	
	@Test
	public void testFindUserByEmail() {
		String email = "demo1@123" ;
		
		User user = repo.findByEmail(email);
		assertThat(user).isNotNull(); 
	}
	
}
