package com.halal.sa.data.dao;

import com.halal.sa.common.error.ApiException;
import com.halal.sa.controller.vo.UserVO;

public interface AccountDao {
	
	public String insertUserData(UserVO userBean);
	
	public Object getUserByPassword(String username, String password) throws ApiException;
	
	public String getUserByEmail(String email);

}
