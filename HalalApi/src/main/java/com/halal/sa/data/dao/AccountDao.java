package com.halal.sa.data.dao;

import com.halal.sa.common.error.ApiException;
import com.halal.sa.controller.vo.UserVO;
import com.halal.sa.data.entities.User;

public interface AccountDao {
	
	public String insertUserData(UserVO userBean) throws ApiException;
	
	public Object getUserByPassword(String username, String password) throws ApiException;
	
	public User getUserByEmail(String email)  throws ApiException;

}
