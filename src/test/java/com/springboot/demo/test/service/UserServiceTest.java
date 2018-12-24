package com.springboot.demo.test.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.springboot.demo.entity.Attribute;
import com.springboot.demo.service.UserService;

//not good, has to start up the application

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes={Application.class})
public class UserServiceTest {

	
//	@MockBean
//	private UserDao userDao;
//	
//	@Autowired
//	private UserService userService;
//	
//	@Test
//	public void testCreate() throws DatabaseException, EntityNotFoundException {
//		User user = new User();
//		user.setName("xxx");
//		Mockito.when(userDao.save(user)).thenReturn(user);
//		userService.create(user);
//		
//		//your Assert
//	}
//	
//	@Test
//	public void testGet() throws DatabaseException, EntityNotFoundException {
//		User user = new User();
//		user.setId(666L);
//		Mockito.when(userDao.get(666L)).thenReturn(user);
//		userService.get(666L);
//		
//		//your Assert
//	}
	
	@Test
	public void test() {
		UserService s = new UserService();
		List<Attribute> out = s.otherBusiness();
		Assert.assertEquals(2, out.size());
	}
	
	
}
