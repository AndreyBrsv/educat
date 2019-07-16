import ink.educat.user.dao.api.UserNotFoundException;
import ink.educat.user.dao.api.entities.ShortDetailedUser;
import ink.educat.user.dao.api.entities.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ink.educat.user.dao.api.UserDao;
import ink.educat.user.dao.impl.UserDaoImpl;

import java.util.ArrayList;

public class UserDaoTest {

    private UserDaoImpl userDao;

    @Autowired
    @Before
    public void UserDaoTest() {
        AnnotationConfigApplicationContext configApplicationContext =
                new AnnotationConfigApplicationContext(TestConfig.class);

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

    /**
     * В данном методе мы проверяем поиск короткой информации по id. Еще может быть такое,
     * что id не существует.
     */
    @Test
    public void getShortDetailedUserByIdTest() {
        ArrayList<Integer> idList = new ArrayList<>();
        ArrayList<String> expectedUserNameList = new ArrayList<>();
        idList.add(17);
        expectedUserNameList.add("Andrey Borisov");
        idList.add(100);
        expectedUserNameList.add("null");

        for(int i = 0; i< idList.size(); i++) {
            try {
                ShortDetailedUser user = userDao.getShortDetailedUserById(idList.get(i));
                Assert.assertEquals(expectedUserNameList.get(i), user.getUserName());
            } catch (Exception ex) {
                if (expectedUserNameList.get(i).equals("null")) {
                    Assert.assertEquals(expectedUserNameList.get(i), "null");
                }
            }
        }
    }
}
