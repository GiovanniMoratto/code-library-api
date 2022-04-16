/*
 * Copyright (c) 2022. Giovanni Vicentin Moratto - All rights reserved.
 * You may use, distribute and modify this code under the
 * terms of the MIT license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the MIT license with
 * this file. If not, please write to: morattotrabalho@gmail.com, or visit : https://www.linkedin.com/in/giovannimoratto/
 */

package br.com.giovannimoratto.codelibraryapi.author;

import br.com.giovannimoratto.codelibraryapi.core.validation.annotations.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AuthorRequest {

    /* Properties */
    @NotBlank
    @UniqueValue(fieldName = "name", domainClass = Author.class)
    private final String name;
    @NotBlank
    @Email
    @UniqueValue(fieldName = "email", domainClass = Author.class)
    private final String email;
    @NotBlank
    @Size(max = 400)
    private final String description;

    /* Constructors */
    public AuthorRequest(String name, String email, String description) {
        this.name = name;
        this.email = email;
        this.description = description;
    }

    /* Methods */
    public Author toModel() {
        return new Author(name, email, description);
    }

}
