package com.majm.spring.validator;

import com.majm.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Locale;

/**
 * Errors 文案 Demo </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-30 23:57
 * @since
 */
@Slf4j
public class ErrorMessageDemo {

    public static void main(String[] args) {
        // 0. 创建 user对象
        User user = new User();
        user.setName("majm");

        // 1. 创建 Errors 实现  --> BeanPropertyBindingResult
        Errors errors = new BeanPropertyBindingResult(user, "user");

        // 2. 调用 reject or rejectValue()
        // reject 生成 ObjectError
        // rejectValue 生成  FieldError

        errors.reject("user.properties.not.null");
        errors.rejectValue("name", "name.required");

        // 3. 获取uErrors 中的 FieldError or ObjectError
        List<ObjectError> globalErrors = errors.getGlobalErrors();
        List<ObjectError> allErrors = errors.getAllErrors();


        // 4. 通过 ObjectError and FieldError 中的 code and args 来关联 MessageSource 实现
        // StaticMessageSource 可以静态化的添加一些数据
        StaticMessageSource messageSource = createMessageSource();

        allErrors.forEach(objectError -> {
            String message = messageSource.getMessage(objectError.getCode(), objectError.getArguments(), Locale.getDefault());
            log.info(message);
        });


    }

    static StaticMessageSource createMessageSource() {
        StaticMessageSource messageSource = new StaticMessageSource();
        messageSource.addMessage("user.properties.not.null", Locale.getDefault(), "User 所有属性不能为空.");
        messageSource.addMessage("name.required", Locale.getDefault(), "the name of User must not be null.");
        messageSource.addMessage("id.required", Locale.getDefault(), "the id of User must not be empty.");
        return messageSource;
    }
}
