package br.com.zupacademy.diego.ecommerce.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NaoCadastradoValidator.class)
public @interface NaoCadastrado {
    String message() default "{br.com.zupacademy.diego.casadocodigo}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String fieldName();
    Class<?> obj();
}
