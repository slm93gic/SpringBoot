import com.slm.spring.entity.Hello;
import com.slm.spring.entity.User;
import com.slm.spring.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JunitTest {
    private String beanFile = "beans.xml";


    @Test
    public void test() {
        //解析beans.xml文件 , 生成管理相应的Bean对象
        ApplicationContext context = new ClassPathXmlApplicationContext(beanFile);
        //getBean : 参数即为spring配置文件中bean的id .
        Hello hello = (Hello) context.getBean("hello");
        hello.show();
    }

    @Test
    public void test1() {
        //解析beans.xml文件 , 生成管理相应的Bean对象
        ApplicationContext context = new ClassPathXmlApplicationContext(beanFile);
        UserServiceImpl serviceImpl = (UserServiceImpl) context.getBean("ServiceImpl");
        serviceImpl.getUser();
    }

    @Test
    public void test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext(beanFile);
        User user = (User) context.getBean("user");
        user.show();
    }

}
