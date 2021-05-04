package com.majm.spring.validator;

import com.majm.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.util.ClassUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Locale;

import static com.majm.spring.validator.ErrorMessageDemo.createMessageSource;

/**
 * Spring 自定义 {@link Validator} </br>
 *
 * spring自定义 validator 还是比较麻烦的, 1. 传入要校验的对象 2.创建 errors 对象，绑定要校验的对象
 *
 * @author majunmin
 * @description
 * @datetime 2021-05-01 08:32
 * @since
 */
@Slf4j
public class ValidatorDemo {

    public static void main(String[] args) {
        // 1. 创建 Validator
        UserValidator userValidator = new UserValidator();
        User user = new User();

        log.info("user 对象是否被 UserValidator 支持? : " + userValidator.supports(user.getClass()));
        // 2. 创建 errors 对象

        Errors errors = new BeanPropertyBindingResult(user, "user");
        userValidator.validate(user, errors);

        // 4. 获取 MessageSource 对象
        MessageSource messageSource = createMessageSource();
        // 5. 输出所有的errors 文案
        errors.getAllErrors().forEach(error -> {
            String message = messageSource.getMessage(error.getCode(), error.getArguments(), Locale.getDefault());
            log.info(message);
        });

    }

    static class UserValidator implements Validator {

        @Override
        public boolean supports(Class<?> clazz) {
            return ClassUtils.isAssignable(User.class, clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            User user = (User) target;
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
        }
    }
}
