package br.com.zupacademy.diego.ecommerce.validators;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NaoCadastradoValidator implements ConstraintValidator<NaoCadastrado, Object> {
    @PersistenceContext
    private EntityManager em;

    private String fieldName;
    private Class<?> obj;

    @Override
    public void initialize(NaoCadastrado constraintAnnotation) {
        this.obj = constraintAnnotation.obj();
        this.fieldName = constraintAnnotation.fieldName();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return !this.em.createQuery("SELECT 1 FROM " + this.obj.getName() + " WHERE " + fieldName + "= :field")
                .setParameter("field", value)
                .getResultList()
                .isEmpty();
    }
}
