import ink.educat.user.dao.api.UserNotFoundException;
import ink.educat.user.dao.api.entities.User;
import ink.educat.user.service.api.UserService;
import ink.educat.user.service.impl.UserServiceImpl;
import ink.educat.user.service.api.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;

public class UserServiceTest {

    private UserServiceImpl userService;

    @Autowired
    @Before
    public void UserServiceTest() {
        AnnotationConfigApplicationContext configApplicationContext =
                new AnnotationConfigApplicationContext(TestConfig.class);

        final UserServiceImpl UserServiceImpl = (UserServiceImpl) configApplicationContext.getBean(UserService.class);

        this.userService = UserServiceImpl;
    }

    //todo подредактировать входные данные, ничего не трогать до тех пор, пока не будут готовы методы

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
        for (int i = 0; i < emailList.size(); i++) {
            try {
                User user = userService.findUserByEmail(emailList.get(i));
                Assert.assertEquals(expectedFirstNameList.get(i), user.getFirstName());
            } catch(Exception ex) {
                if (expectedFirstNameList.get(i).equals("null")) {
                    Assert.assertTrue(ex instanceof UserNotFoundException);
                } else {
                    Assert.assertEquals(expectedFirstNameList.get(i), "null");
                }
            }

        }
    }


}
