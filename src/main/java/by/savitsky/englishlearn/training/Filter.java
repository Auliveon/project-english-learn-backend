package by.savitsky.englishlearn.training;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.lang.annotation.*;

@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@ApplicationScope
public @interface Filter {
    @AliasFor(
            annotation = Component.class
    )
    String value() default "";
}

