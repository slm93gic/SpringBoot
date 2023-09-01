import com.slm.spring.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    private String beanFile = "application.xml";

    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext(beanFile);
        UserService userService = (UserService) context.getBean("userService");
        userService.search();
    }


}
