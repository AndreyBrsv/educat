import ink.educat.user.dao.api.entities.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ink.educat.web.configuration.ApplicationConfiguration;
import ink.educat.user.dao.api.UserDao;
import ink.educat.user.dao.impl.UserDaoImpl;


public class UserDaoTest {

    private UserDaoImpl userDao;

    @Autowired
    @Before
    public void UserDaoTest() {
        AnnotationConfigApplicationContext configApplicationContext =
                new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        final UserDaoImpl userDaoImpl = (UserDaoImpl)configApplicationContext.getBean(UserDao.class);

        this.userDao = userDaoImpl;
    }

    @Test
    public void findUserByEmailTest()
    {
        String email = "andreybrsv@yandex.ru";
        User user = userDao.findUserByEmail(email);

        Assert.assertEquals("Andrey", user.getFirstName());
    }
}
