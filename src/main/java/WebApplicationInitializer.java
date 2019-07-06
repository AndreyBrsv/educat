import org.checkerframework.checker.nullness.qual.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebApplicationInitializer implements org.springframework.web.WebApplicationInitializer {

    //private static final Logger LOGGER = LoggerFactory.getLogger(WebApplicationInitializer.class);

    private static final String MAPPING_URL = "/*";

    @Override
    public void onStartup(@NonNull ServletContext servletContext) throws ServletException {

        // Создаем контекст с сервисами
        //LOGGER.debug("Поднимаю контейнер сервисов.");
        System.out.println("Поднимаю контейнер сервисов");
//        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
//        //rootContext.register(EducatServiceConfiguration.class);
//        rootContext.refresh();
//        // Добавляем слушателя к контейнеру сервлетов
//        servletContext.addListener(new ContextLoaderListener(rootContext));
        // Добавляем конфигурацию диспечер-сервлетов
        AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext =
                new AnnotationConfigWebApplicationContext();
        annotationConfigWebApplicationContext.register(WebMvcConfig.class);
        annotationConfigWebApplicationContext.refresh();
        // Добавляем сам диспечер сервлет
        final DispatcherServlet dispatcherServlet = new DispatcherServlet(annotationConfigWebApplicationContext);
        ServletRegistration.Dynamic registration = servletContext.addServlet("app", dispatcherServlet);
        registration.setLoadOnStartup(1);
        registration.addMapping(MAPPING_URL);
    }

}
