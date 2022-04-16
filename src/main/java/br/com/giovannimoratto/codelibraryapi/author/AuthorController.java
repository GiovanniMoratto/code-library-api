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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("code-library-api/author")
public class AuthorController {

    /* Dependencies Injection */

    @Autowired
    private AuthorService service;

    /* Methods */

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<?> createAuthor(@RequestBody @Valid AuthorRequest request, UriComponentsBuilder uriBuilder) {
        Long id = service.createAuthor(request);
        URI uri = uriBuilder.path("/code-library/author/profile/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<AuthorResponse> searchByName(@PathVariable("name") String name) {
        AuthorResponse response = service.findByName(name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/authors")
    public  ResponseEntity<Page<AuthorResponse>> showAuthors(@PageableDefault(
            page = 0, size = 10, sort = "createAt", direction = Sort.Direction.DESC)Pageable pageable) {
        Page<AuthorResponse> page = service.listAuthors();
        return ResponseEntity.ok(page);
    }

}
