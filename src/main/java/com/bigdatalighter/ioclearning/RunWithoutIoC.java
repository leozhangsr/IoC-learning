package com.bigdatalighter.ioclearning;

import com.bigdatalighter.ioclearning.dao.IUserDao;
import com.bigdatalighter.ioclearning.dao.UserDaoJdbcImpl;
import com.bigdatalighter.ioclearning.service.UserServiceImplWithOutIoC;

/**
 * Description:
 *
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public class RunWithoutIoC {

    public static void main(String[] args) {
        String author = "Leo Zhang";
        //创建UserServiceImplWithOutIoC时，类内部在构造器上初始化了具体的IUserDao
        //类内部控制了IUserDao具休实现类的创建、赋值关联流程
        UserServiceImplWithOutIoC userServiceImplWithOutIoC = new UserServiceImplWithOutIoC();
        userServiceImplWithOutIoC.findUser(author);
        //为了解耦，我们可以在外部创建IUserDao的实现类，再通过set方法将IUserDao注入IUserService
        //通过将创建对象与赋值分离，实现服务类和dao类的解耦
        IUserDao userDaoJdbc = new UserDaoJdbcImpl();
        userServiceImplWithOutIoC.setUserDao(userDaoJdbc);
        userServiceImplWithOutIoC.findUser(author);

    }

}
