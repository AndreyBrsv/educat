import ink.educat.configuration.EducatDaoConfiguration;
import ink.educat.user.api.Entities.User;
import ink.educat.user.api.UserDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Это ненастоящий тест, настоящие Unit Tests нужно писать несколько позже
 * тут просто main для проверки работы методов {@link  ink.educat.user.api.UserDao}
 */
public class UserDaoPrimitiveTest {
    public static void main(String[] args) {

        // Поднимаем контекст вручную(но это только для теста)
        // На деле контекст будет подниматься сервером(как именно мы серверу объясним)
        AnnotationConfigApplicationContext configApplicationContext =
                new AnnotationConfigApplicationContext(EducatDaoConfiguration.class);

        // Нужно будет использовать логгироввании (например log4j) System.out крайне неэффективен
        // при логгировании программы
        String stringBuilder = "Configuration context startup " +
                configApplicationContext;
        System.out.println(stringBuilder);

        // Получаем Bean из контейнера
        final UserDao userDao = (UserDao) configApplicationContext.getBean(UserDao.class);

        // Пытаемся вызвать метод findByEmail
        // Если таблиц нет в базе, то мы упадем с соответствующим исключением!
        // Однако, это будет означать, что наше приложение таки лезет в базу и все работает.
        User user = userDao.getUserByEmail("andreybrsv@yandex.ru");

    }
}
