import com.slm.Demo04BootStarter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest(classes = Demo04BootStarter.class)
public class SpringbootDataJdbcApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    public void contextLoads() throws SQLException {
        //看一下默认数据源
        System.out.println(dataSource.getClass());//获得连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);//关闭连接connection.close();}}
    }
}
