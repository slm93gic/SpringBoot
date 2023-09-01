import com.slm.spring.conf.MyConfig;
import com.slm.spring.entity.Dog;
import com.slm.spring.entity.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JunitTest {

    private String beanFile = "beans.xml";

    @Test
    public void test() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(beanFile);
        User user = (User) applicationContext.getBean("user");
        System.out.println(user.name);
    }

    @Test
    public void test2() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig.class);
        Dog dog = (Dog) applicationContext.getBean("dog");
        Dog wocao = (Dog) applicationContext.getBean("wocao");
        System.out.println(dog.name);
        System.out.println(wocao.name);
    }

}
