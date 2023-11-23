import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zt.dao.BookMapper;
import com.zt.dao.UserDetailMapper;
import com.zt.dao.UserLoginMapper;
import com.zt.pojo.Book;
import com.zt.pojo.UserDetail;
import com.zt.pojo.UserLogin;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2023/11/17 21:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml", "classpath:mybatis-config.xml","classpath:SpringMVC-servlet.xml"})
public class Try {
    @Autowired
    private UserLoginMapper userLoginMapper;
    @Test
    public void test() throws Exception {
        userLoginMapper.insertUserLogin(new UserLogin(null, "zhoutao", "yk5201314", true));
    }
    @Autowired
    private UserDetailMapper userDetailMapper;
    @Autowired
    private BookMapper bookMapper;
    @Test
    public void testUserDetail(){
    }
}
