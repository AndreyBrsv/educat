package ink.educat.core.service.impl.factory;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * Данный сервис используется в том случае, если что-то не удается сделать @Autowired,
 * но очень нужен сервис из контекста.
 *
 * Использовать крайне редко и очень осознанно. Мы, в первую очередь пытаемся добиться
 * прозрачной архитектуры.
 */
@Service
public class ServiceFactory implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public <T> T getService( T service) {
        return (T) applicationContext.getBean(service.getClass());
    }
}
