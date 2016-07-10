package com.halal.sa.common;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(JUnit4.class)
//@ContextConfiguration("/WEB-INF/dispatcher-servlet.xml")
public class CommonUtilTest {
	
	CommonUtil commonUtil;
	
	@Before
	public void setup(){
		commonUtil = new CommonUtil();
	}
	
	@Test
	public void test_encrypt(){
		String result = "Nzg2QTE1MTctNjRDRi00RjRELTkzNTAtRjhDNzRGMjkxNjMzLzU3MDY4ZmQ5MmVjZTY4M2UyNGU5ZjZiMC8yMDE2LTA2LTA3LDE2OjI0OjA5";
		assertTrue(commonUtil.encriptString("786A1517-64CF-4F4D-9350-F8C74F291633/57068fd92ece683e24e9f6b0/2016-06-07,16:24:09").equals(result));
	}

}
