import ink.educat.user.dao.api.UserNotFoundException;
import ink.educat.user.dao.api.entities.ShortDetailedUser;
import ink.educat.user.dao.api.entities.User;
import ink.educat.user.dao.api.entities.UserRole;
import ink.educat.user.dao.api.entities.UserStatus;
import ink.educat.user.dao.impl.UserDaoImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ink.educat.user.dao.api.UserDao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDaoTest {

    private UserDaoImpl userDao;

    @Before
    public void UserDaoTest() {
        AnnotationConfigApplicationContext configApplicationContext =
                new AnnotationConfigApplicationContext(TestConfig.class);

        final UserDaoImpl userDaoImpl = (UserDaoImpl) configApplicationContext.getBean(UserDao.class);

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

        for (int i = 0; i < emailList.size(); i++) {
            try {
                User user = userDao.findUserByEmail(emailList.get(i));
                Assert.assertEquals(expectedFirstNameList.get(i), user.getFirstName());
            } catch (UserNotFoundException ex) {
                if(expectedFirstNameList.get(i).equals("null")) {
                    Assert.assertTrue(true);
                }
                else {
                    Assert.assertTrue(false);
                }
            } catch (RuntimeException ex) {
                System.out.println("Неизвестная ошибка внутри метода!");
                Assert.assertTrue(false);
                ex.printStackTrace();
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

        for (int i = 0; i < idList.size(); i++) {
            try {
                ShortDetailedUser user = userDao.getShortDetailedUserById(idList.get(i));
                Assert.assertEquals(expectedUserNameList.get(i), user.getUserName());
            } catch (UserNotFoundException ex) {
                if (expectedUserNameList.get(i).equals("null")) {
                    Assert.assertTrue(true);
                } else {
                    Assert.assertTrue(false);
                }
            } catch (RuntimeException ex) {
                System.out.println("Неизвестная ошибка внутри метода!");
                Assert.assertTrue(false);
                ex.printStackTrace();
            }
        }
    }

    /**
     * В данном методе осуществляется проверка информации по id. id может не существовать.
     */
    @Test
    public void findByIdTest() {
        ArrayList<Integer> idList = new ArrayList<>();
        ArrayList<String> expectedemailList = new ArrayList<>();
        idList.add(17);
        expectedemailList.add("andreybrsv@yandex.ru");
        idList.add(100);
        expectedemailList.add("null");
        idList.add(17);
        expectedemailList.add("andreybrsv@ya.ru");


        for (int i = 0; i < idList.size(); i++) {
            try {
                User user = userDao.findById(idList.get(i));
                Assert.assertEquals(expectedemailList.get(i), user.getEmail());
            } catch (UserNotFoundException ex) {
                if (expectedemailList.get(i).equals("null")) {
                    Assert.assertTrue(true);
                } else {
                    Assert.assertTrue(false);
                }
            } catch (RuntimeException ex) {
                System.out.println("Неизвестная ошибка внутри метода!");
                Assert.assertTrue(false);
                ex.printStackTrace();
            }
        }
    }

    /**
     * В данном методе идет поиск информации по нескольким id. Какого-то может не существовать. Так что
     * проверяем количество найденных пользователей. Пользователи в возвращаемом списке должны быть уникальны.
     */
    @Test
    public void findByIDsTest() {
        ArrayList<Long> goodids = new ArrayList<>();
        goodids.add(17L);
        goodids.add(18L);
        ArrayList<Long> badids = new ArrayList<>();
        badids.add(1000L);
        badids.add(17L); // Повторяется
        ArrayList<Long> ids = new ArrayList<>();
        ids.addAll(goodids);
        ids.addAll(badids);
        try {
            List<User> userList = userDao.findByIDs(ids);
            Assert.assertEquals(goodids.size(), userList.size());
        } catch (RuntimeException ex){
            System.out.println("Неизвестная ошибка внутри метода!");
            Assert.assertTrue(false);
            ex.printStackTrace();
        }
    }

    //todo переделать тест с 0
    @Test
    public void saveOrUpdateTest() {
        String firstName = "Andrey";
        String name="null";
        String expectedfirstName = "Vladimir";
        String emailadress = "test@test.ru";

        try {
            User user = userDao.findUserByEmail(emailadress); // Берем информацию юзера по его почте
            name = user.getFirstName();
        } catch(Exception ex) {
            if (name == "null") { // Проверяем существует ли пользователь
                User usernew = new User(1, emailadress, false, "123", firstName, "Ivanov", UserStatus.ACTIVE, UserRole.USER, LocalDateTime.now());
                userDao.saveOrUpdate(usernew); // Создаем юзера
                userDao.findUserByEmail(emailadress); // Берем информацию по юзеру
                Assert.assertEquals(firstName, usernew.getFirstName()); // Проверяем, создался ли юзер
                User userchange = new User(1, emailadress, false, "123", expectedfirstName, "Ivanov", UserStatus.ACTIVE, UserRole.USER, LocalDateTime.now());
                userDao.saveOrUpdate(userchange); // Делаем изменение в юзере
                userDao.findUserByEmail(emailadress); // Берем информацию по юзеру
                Assert.assertEquals(expectedfirstName, userchange.getFirstName()); // Проверяем, произошли ли изменения
                userDao.delete(userchange); // удаляем созданного юзера в этом методе
             }
        }
    }
}
