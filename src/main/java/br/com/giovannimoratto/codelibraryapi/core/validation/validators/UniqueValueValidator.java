/*
 * Copyright (c) 2022. Giovanni Vicentin Moratto - All rights reserved.
 * You may use, distribute and modify this code under the
 * terms of the MIT license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the MIT license with
 * this file. If not, please write to: morattotrabalho@gmail.com, or visit : https://www.linkedin.com/in/giovannimoratto/
 */

package br.com.giovannimoratto.codelibraryapi.core.validation.validators;

import br.com.giovannimoratto.codelibraryapi.core.validation.annotations.UniqueValue;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, String> {

    /* Properties */

    @PersistenceContext
    private EntityManager em;
    private String object;
    private String field;

    /* Methods */

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        object = constraintAnnotation.domainClass().getSimpleName();
        field = constraintAnnotation.fieldName();
    }

    @Override
    @Transactional
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) {
            return false;
        }

        Query query = em.createQuery("SELECT 1 FROM " + object + " WHERE " + field + " = :VALUE");
        query.setParameter("VALUE", value);

        return query.getResultList().isEmpty();
    }

}
