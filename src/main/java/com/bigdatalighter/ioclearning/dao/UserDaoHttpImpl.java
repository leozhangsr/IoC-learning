package com.bigdatalighter.ioclearning.dao;

import com.bigdatalighter.ioclearning.anotation.MyComponent;

/**
 * Description:
 * 通过公开的http接口查找用户信息
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
@MyComponent(values = "userDao")
public class UserDaoHttpImpl implements IUserDao {

    public void findUser(String userName) {
        System.out.println("通过http api查找用户：" + userName);
    }

}
