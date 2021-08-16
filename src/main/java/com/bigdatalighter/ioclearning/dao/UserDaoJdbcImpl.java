package com.bigdatalighter.ioclearning.dao;

import com.bigdatalighter.ioclearning.anotation.MyComponent;

/**
 * Description:
 * 通过jdbc直接查询内部数据库，寻找用户信息
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
@MyComponent(values = "userDaoJdbcImpl")
public class UserDaoJdbcImpl implements IUserDao {

    public void findUser(String userName) {
        System.out.println("通过数据库查找用户：" + userName);
    }

}
