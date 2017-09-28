package spi;

import java.lang.annotation.*;

/**
 * Created by 听风 on 2017/7/21.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface SPI {

    String value() default "";
}
