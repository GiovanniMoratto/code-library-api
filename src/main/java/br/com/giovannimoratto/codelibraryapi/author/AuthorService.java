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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class AuthorService {

    /* Dependencies Injection */

    @Autowired
    private AuthorRepository repository;

    /* Methods */

    public Long createAuthor(AuthorRequest request) {
        Author newAuthor = request.toModel();
        repository.save(newAuthor);
        return newAuthor.getId();
    }

    public AuthorResponse findByName(String name) {
        Author author = repository.findByName(name).orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND, "Author not found.")
        );
        return new AuthorResponse(author);
    }

    public Page<AuthorResponse> listAuthors() {
        List<Author> authors = repository.findAll();
        List<AuthorResponse> list = new ArrayList<>();
        authors.forEach(author -> list.add(new AuthorResponse(author)));
        return new PageImpl<AuthorResponse>(list);
    }

}
