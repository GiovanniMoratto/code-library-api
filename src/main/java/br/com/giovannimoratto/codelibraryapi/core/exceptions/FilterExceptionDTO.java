/*
 * Copyright (c) 2022. Giovanni Vicentin Moratto - All rights reserved.
 * You may use, distribute and modify this code under the
 * terms of the MIT license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the MIT license with
 * this file. If not, please write to: morattotrabalho@gmail.com, or visit : https://www.linkedin.com/in/giovannimoratto/
 */

package br.com.giovannimoratto.codelibraryapi.core.exceptions;

public class FilterExceptionDTO {

    /* Properties */

    private final String field;
    private final String error;

    /* Constructors */

    public FilterExceptionDTO(String field, String error) {
        super();
        this.field = field;
        this.error = error;
    }

    /* Getters */

    public String getField() {
        return field;
    }

    public String getError() {
        return error;
    }

}
