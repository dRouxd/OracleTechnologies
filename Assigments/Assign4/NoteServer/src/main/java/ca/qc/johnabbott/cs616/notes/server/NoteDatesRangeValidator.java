package ca.qc.johnabbott.cs616.notes.server;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by ian on 15-10-02.
 */
public class NoteDatesRangeValidator implements ConstraintValidator<NoteDatesRange, Note> {

    @Override
    public void initialize(NoteDatesRange constraintAnnotation) {

    }

    @Override
    public boolean isValid(Note value, ConstraintValidatorContext context) {
        if(value.getReminder() == null)
            return true;
        else
            return value.getCreated().before(value.getReminder());
    }
}
