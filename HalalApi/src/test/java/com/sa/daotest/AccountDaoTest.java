package com.sa.daotest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.halal.sa.controller.vo.LogonVO;
import com.halal.sa.controller.vo.UserVO;
import com.halal.sa.data.dao.impl.MyAccountDaoImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/WEB-INF/dispatcher-servlet.xml")
public class AccountDaoTest {
	
	@Autowired
	MyAccountDaoImpl accountDao;
	
//	@Test
	public void registerTest(){
		System.out.println("start");
		UserVO userVO = new UserVO();
		userVO.setFullName("lubna");
//		userVO.setCity("pune");
		userVO.setEmail("lubna@gmail.com");
		userVO.setPassword("sameen");
//		userVO.setPhone(9890);
		accountDao.insertUserData(userVO);
	}
	
	@Test
	public void loginTest(){
		System.out.println(accountDao.getUserByPassword("sameen@gmail.com", "a2f3dd8948898f19fc69fe780edf14eb9ae83f71cd312a3f17817300aca0af38"));
//		System.out.println(password);
	}
	
//	@Test
	public void addToken(){
//		accountDao.updateToken(3, "3", "3");
	}
	
//	@Test
	public void checkEmail(){
		System.out.println(accountDao.getUserByEmail("sameen2u@gmail.com"));;
	}
	

}
