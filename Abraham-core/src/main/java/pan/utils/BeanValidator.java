package pan.utils;

import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by panqingrong on 05/10/2016.
 */
public class BeanValidator {
    private static Validator validator;

    static {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();
        validator = localValidatorFactoryBean.getValidator();
    }

    public static void validate(Object object) {
        Set constraintViolations = validator.validate(object);
        if (constraintViolations.isEmpty() == false) {
            throw new IllegalArgumentException("Bean validating can't be passed!",
                    new ConstraintViolationException(constraintViolations));
        }
    }
}
