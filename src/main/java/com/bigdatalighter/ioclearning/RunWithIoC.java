package com.bigdatalighter.ioclearning;

import com.bigdatalighter.ioclearning.service.IUserService;
import com.bigdatalighter.ioclearning.service.UserServiceImplWithIoc;

/**
 * Description:
 *
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public class RunWithIoC {

    public static void main(String[] args) throws Exception{
        String author = "Leo Zhang";
        //通过容器完成依赖注册和注入，实现动态，配置式对象管理
        MyApplicationContext context = new MyApplicationContext();
        IUserService userService = (UserServiceImplWithIoc)context.getBean("userService");

        userService.findUser(author);
    }
}
