import ink.educat.user.dao.api.UserNotFoundException;
import ink.educat.user.dao.api.entities.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ink.educat.web.configuration.ApplicationConfiguration;
import ink.educat.user.dao.api.UserDao;
import ink.educat.user.dao.impl.UserDaoImpl;

import java.util.ArrayList;


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

    //todo подредактировать входные данные
    /**
     * В данном тесте мы проверяем поиск по email'у. @ya.ru и @yandex.ru должны являться одним
     * адресом. Так же тест должен проверять, что мы ничего не нашли по email'у.
     */
    @Test
    public void findUserByEmailTest() {
        ArrayList<String> emailList = new ArrayList<>();
        ArrayList<String> expectedFirstNameList = new ArrayList<>();

        emailList.add("andreybrsv@yandex.ru");
        expectedFirstNameList.add("Andrey");
        emailList.add("andreybrsv@ya.ru");
        expectedFirstNameList.add("Andrey");
        emailList.add("noname@yandex.ru");
        expectedFirstNameList.add("null");

        for(int i = 0; i < emailList.size(); i++) {
            try {
                User user = userDao.findUserByEmail(emailList.get(i));
                Assert.assertEquals(expectedFirstNameList.get(i), user.getFirstName());
            } catch (Exception ex) {
                if (expectedFirstNameList.get(i).equals("null")) {
                    Assert.assertTrue(ex instanceof UserNotFoundException);
                } else {
                    Assert.assertEquals(expectedFirstNameList.get(i), "null");
                }
            }
        }

    }

   // @Test
    //public void
}
