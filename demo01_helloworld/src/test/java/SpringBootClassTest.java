import com.slm.Demo01BootStarter;
import com.slm.pojo.Dog;
import com.slm.pojo.Person;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Demo01BootStarter.class)
@SuppressWarnings("all")
public class SpringBootClassTest {

    @Autowired
    Dog dog;

    @Autowired
    Person person;

    @Test
    public void test1() {
        System.out.println(dog);
    }

    @Test
    public void test2() {
        System.out.println(person);
    }


}


