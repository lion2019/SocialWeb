package com.social.domain;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 標記該屬性為流水號，自動編號
 */
@Documented
@Retention(RUNTIME)
@Target({ TYPE, FIELD })
public @interface AutoIncrement {
}
