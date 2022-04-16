/*
 * Copyright (c) 2022. Giovanni Vicentin Moratto - All rights reserved.
 * You may use, distribute and modify this code under the
 * terms of the MIT license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the MIT license with
 * this file. If not, please write to: morattotrabalho@gmail.com, or visit : https://www.linkedin.com/in/giovannimoratto/
 */

package br.com.giovannimoratto.codelibraryapi.core.validation.annotations;

import br.com.giovannimoratto.codelibraryapi.core.validation.validators.UniqueValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {UniqueValueValidator.class})

@Target({
        ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
        ElementType.PARAMETER, ElementType.TYPE_USE
})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueValue {

    Class <?> domainClass();

    String fieldName();

    String message() default "This value already exists!";

    Class <?>[] groups() default {};

    Class <? extends Payload>[] payload() default {};

}
