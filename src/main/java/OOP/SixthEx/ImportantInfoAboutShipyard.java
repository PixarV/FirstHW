package OOP.SixthEx;

import java.lang.annotation.*;

@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ImportantInfoAboutShipyard {
    String captain() default "ritt";
    String value();
}
