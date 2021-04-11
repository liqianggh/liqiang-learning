package thinking.in.spring.ioc.overview.dependency.injection;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import thinking.in.spring.ioc.overview.dependency.repository.UserRepository;


/**
 * 依赖查找实例
 *
 * @author liqiang
 * @date 2021-04-09 12:47 下午
 **/
public class DependencyInjectionDemo {
    public static void main(String[] args) {
        // 依赖注入
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:dependency-injection-context.xml");
        final UserRepository userRepository = (UserRepository) beanFactory.getBean("userRepository");
        System.out.println(userRepository);

        System.out.println(userRepository.getBeanFactory() == beanFactory);

        final ObjectFactory<ApplicationContext> userFactory = userRepository.getObjectFactory();
        System.out.println(userFactory.getObject() == beanFactory);

    }
}
