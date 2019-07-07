import ink.educat.configuration.EducatDaoConfiguration;
import ink.educat.configuration.EducatServiceConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserServicePrimitiveTest {
    public static void main(String[] args) {
        // Поднимаем контекст вручную(но это только для теста)
        // На деле контекст будет подниматься сервером(как именно мы серверу объясним)
        AnnotationConfigApplicationContext configApplicationContext =
                new AnnotationConfigApplicationContext(EducatServiceConfiguration.class);
        System.out.println("Подяли контекст.");
    }
}
