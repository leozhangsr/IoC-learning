package com.bigdatalighter.ioclearning.service;

import com.bigdatalighter.ioclearning.anotation.MyAutowired;
import com.bigdatalighter.ioclearning.anotation.MyComponent;
import com.bigdatalighter.ioclearning.dao.IUserDao;

/**
 * Description:
 * 使用IoC和依赖注入
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
@MyComponent(values = "userService")
public class UserServiceImplWithIoc implements IUserService{

    @MyAutowired(value = "userDao")
    private IUserDao userDao;

    public void findUser(String userName) {
        userDao.findUser(userName);
    }

}
