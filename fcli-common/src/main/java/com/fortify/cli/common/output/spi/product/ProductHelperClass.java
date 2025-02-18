package com.fortify.cli.common.output.spi.product;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
public @interface ProductHelperClass {
    Class<? extends IProductHelper> value();
}
