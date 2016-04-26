package com.sa.common;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.halal.sa.common.CommonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/WEB-INF/dispatcher-servlet.xml")
public class LoginAuthenticationTest {

	@Test
	public void passwordTest() throws NoSuchAlgorithmException{
		String password1 = "sameen";
		String password2 = "sameen";
		
		CommonUtil commonUtil = new CommonUtil();
		if(commonUtil.hashPassword(password1).compareTo(commonUtil.hashPassword(password2))==0){
			System.out.println("Password match");
		}
		else System.out.println("Password not match");
	}
}
