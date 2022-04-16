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

import br.com.giovannimoratto.codelibraryapi.core.validation.annotations.ExistsId;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsIdValidator implements ConstraintValidator <ExistsId, Long> {

    /* Properties */

    @PersistenceContext
    private EntityManager em;
    private String object;

    /* Methods */

    @Override
    public void initialize(ExistsId constraintAnnotation) {
        object = constraintAnnotation.domainClass().getSimpleName();
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        if (id == null) {
            return true;
        }
        Query query = em.createQuery("SELECT 1 FROM " + object + " o WHERE o.id = :VALUE");
        query.setParameter("VALUE", id);

        return !query.getResultList().isEmpty();
    }

}
