package com.bigdatalighter.ioclearning.service;

import com.bigdatalighter.ioclearning.dao.IUserDao;
import com.bigdatalighter.ioclearning.dao.UserDaoHttpImpl;

/**
 * Description:
 * 不使用Ioc和依赖注入的写法
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public class UserServiceImplWithOutIoC implements IUserService{

    private IUserDao userDao;

    /**
     * 在构造器中人工创建对象，并完成赋值
     * 由于直接创建具体的IUserDao实现类， 服务与UserDaoHttpImpl强耦合
     */
    public UserServiceImplWithOutIoC() {
        userDao = new UserDaoHttpImpl();
    }

    @Override
    public void findUser(String userName) {
        userDao.findUser(userName);
    }

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

}
