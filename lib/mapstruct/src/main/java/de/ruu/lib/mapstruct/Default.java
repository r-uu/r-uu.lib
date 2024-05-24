package de.ruu.lib.mapstruct;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({CONSTRUCTOR})
@Retention(CLASS)
@interface Default { }