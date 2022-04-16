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

import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AuthorControllerTest {

    // URL
    private final String api = "/code-library-api";
    private final String feature = "/author";
    private final String url = api + feature;
    private final String create = "/create";

    /* Dependencies Injection */
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;

    /* Methods */

    // POST #######################################################################################################
    @Test
    @DisplayName("Should return status code 400 when try to POST with empty body")
    void shouldBeErrorWhenTryToPOSTWithEmptyBody() throws Exception {
        // Given
        String urlTemplate = url + create;

        // When
        MvcResult result = mockMvc.perform(
                post(urlTemplate)
                        .characterEncoding("UTF-8")
                        .contentType(APPLICATION_JSON)
        ).andReturn();

        // Then
        assertEquals(400, result.getResponse().getStatus());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\n", "\t"})
    @DisplayName("Should return status code 400 when try to POST with empty object")
    void shouldBeErrorWhenTryToPOSTWithEmptyObject(String json) throws Exception {
        // Given
        String urlTemplate = url + create;

        // When
        MvcResult result = mockMvc.perform(
                post(urlTemplate)
                        .content(json)
                        .characterEncoding("UTF-8")
                        .contentType(APPLICATION_JSON)
        ).andReturn();

        // Then
        assertEquals(400, result.getResponse().getStatus());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\n", "\t"})
    @DisplayName("Should return status code 400 when try to POST with empty name")
    void shouldBeErrorWhenEmptyName(String name) throws Exception {
        // Given
        AuthorRequest request = new AuthorRequest(
                name,
                "eiichiro.oda@email.com",
                "One Piece"
        );
        String json = gson.toJson(request);
        String urlTemplate = url + create;

        // When
        MvcResult result = mockMvc.perform(
                post(urlTemplate)
                        .content(json)
                        .characterEncoding("UTF-8")
                        .contentType(APPLICATION_JSON)
        ).andReturn();

        // Then
        assertEquals(400, result.getResponse().getStatus());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\n", "\t"})
    @DisplayName("Should return status code 400 when try to POST with empty email")
    void shouldBeErrorWhenEmptyEmail(String email) throws Exception {
        // Given
        AuthorRequest request = new AuthorRequest(
                "Eiichiro Oda",
                email,
                "One Piece"
        );
        String json = gson.toJson(request);
        String urlTemplate = url + create;

        // When
        MvcResult result = mockMvc.perform(
                post(urlTemplate)
                        .content(json)
                        .characterEncoding("UTF-8")
                        .contentType(APPLICATION_JSON)
        ).andReturn();

        // Then
        assertEquals(400, result.getResponse().getStatus());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\n", "\t"})
    @DisplayName("Should return status code 400 when try to POST with empty description")
    void shouldBeErrorWhenEmptyDescription(String description) throws Exception {
        // Given
        AuthorRequest request = new AuthorRequest(
                "Eiichiro Oda",
                "eiichiro.oda@email.com",
                description
        );
        String json = gson.toJson(request);
        String urlTemplate = url + create;

        // When
        MvcResult result = mockMvc.perform(
                post(urlTemplate)
                        .content(json)
                        .characterEncoding("UTF-8")
                        .contentType(APPLICATION_JSON)
        ).andReturn();

        // Then
        assertEquals(400, result.getResponse().getStatus());
    }

    @ParameterizedTest
    @ValueSource(strings = {"invalidEmail.com", "@invalid.com", "@.com", "@invalid"})
    @DisplayName("Should return status code 400 when try to POST with empty invalid format of email")
    void shouldBeErrorWhenInvalidFormatEmail(String email) throws Exception {
        // Given
        AuthorRequest request = new AuthorRequest(
                "Eiichiro Oda",
                email,
                "One Piece"
        );
        String json = gson.toJson(request);
        String urlTemplate = url + create;

        // When
        MvcResult result = mockMvc.perform(
                post(urlTemplate)
                        .content(json)
                        .characterEncoding("UTF-8")
                        .contentType(APPLICATION_JSON)
        ).andReturn();

        // Then
        assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    @DisplayName("Should return status code 400 when try to POST with a description bigger than 400 characters")
    void shouldBeErrorWhenBiggerDescription() throws Exception {
        // Given
        String description = "a".repeat(401);
        AuthorRequest request = new AuthorRequest(
                "Eiichiro Oda",
                "eiichiro.oda@email.com",
                description
        );
        String json = gson.toJson(request);
        String urlTemplate = url + create;

        // When
        MvcResult result = mockMvc.perform(
                post(urlTemplate)
                        .content(json)
                        .characterEncoding("UTF-8")
                        .contentType(APPLICATION_JSON)
        ).andReturn();

        // Then
        assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    @DisplayName("Should create an author and return status code 201")
    void shouldCreateAuthor() throws Exception {
        // Given
        AuthorRequest request = new AuthorRequest(
                "Eiichiro Oda",
                "eiichiro.oda@email.com",
                "One Piece"
        );
        String json = gson.toJson(request);
        String urlTemplate = url + create;

        // When
        MvcResult result = mockMvc.perform(
                post(urlTemplate)
                        .content(json)
                        .characterEncoding("UTF-8")
                        .contentType(APPLICATION_JSON)
        ).andReturn();

        // Then
        assertEquals(201, result.getResponse().getStatus());
    }

    // GET #######################################################################################################

    // DELETE #######################################################################################################

    // PUT #######################################################################################################
}
