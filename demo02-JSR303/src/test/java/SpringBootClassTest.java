import com.slm.Demo02BootStarter;
import com.slm.pojo.Dog;
import com.slm.pojo.Person;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest(classes = Demo02BootStarter.class)
@SuppressWarnings("all")
public class SpringBootClassTest {

    @Autowired
    Dog dog;

    @Autowired
    Person person;

    @Value("${server.port}")
    private String port;

    @Autowired
    Environment environment;

    @Test
    public void getPort() {
        System.out.println(environment.getProperty("server.port"));
    }

    @Test
    public void test3() {
        System.out.println(port);
    }

    @Test
    public void test1() {
        System.out.println(dog);
    }

    @Test
    public void test2() {
        System.out.println(person);
    }


}


