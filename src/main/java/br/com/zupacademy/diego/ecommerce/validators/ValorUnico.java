package br.com.zupacademy.diego.ecommerce.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValorUnicoValidator.class)
public @interface ValorUnico {
    String message() default "{br.com.zupacademy.diego.casadocodigo}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String fieldName();
    Class<?> obj();
}
