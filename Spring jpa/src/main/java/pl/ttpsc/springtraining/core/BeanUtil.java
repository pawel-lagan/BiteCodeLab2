package pl.ttpsc.springtraining.core;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import pl.ttpsc.springtraining.customer.Customer;

@Component
public class BeanUtil implements ApplicationContextAware {
	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext newContext) throws BeansException {
		context = newContext;
	}

	public static <T> T getService(Class<T> classs) {
		return context.getBean(classs);
	}

	@SuppressWarnings("unchecked")
	public static <T, U, V> T getServiceByGeneric(Class<U> genericInterface, Class<V> argType) {
		ResolvableType serviceByResolvable = ResolvableType.forClassWithGenerics(AppRepository.class, Customer.class);
		String[] beanNames = context.getBeanNamesForType(serviceByResolvable);
		return (T) context.getBean(beanNames[0]);
	}
}
