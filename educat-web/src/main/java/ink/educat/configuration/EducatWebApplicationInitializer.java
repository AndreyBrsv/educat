package ink.educat.configuration;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class EducatWebApplicationInitializer implements WebApplicationInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(EducatWebApplicationInitializer.class);

    @Override
    public void onStartup(@NonNull ServletContext servletContext) throws ServletException {

        // Создание корневого контейнера
        System.out.println("Поднятие контекста");
        AnnotationConfigWebApplicationContext applicationContext =
                new AnnotationConfigWebApplicationContext();
        applicationContext.register(EducatServiceConfiguration.class);
//        applicationContext.refresh();
//
        System.out.println("Добавляем слушатель");
        servletContext.addListener(new ContextLoaderListener(applicationContext));

        // Создание диспечер контекста
        AnnotationConfigWebApplicationContext dispatcherContext =
                new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(DispatcherCongiguration.class);
        //dispatcherContext.refresh();

        // Создание диспетчер сервлета
        DispatcherServlet dispatcherServlet = new DispatcherServlet(dispatcherContext);
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher",dispatcherServlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        System.out.println("Запуск.");
    }
}
