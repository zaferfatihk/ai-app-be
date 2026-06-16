package com.ai_app_be.controllers;

import com.ai_app_be.support.BaseControllerTest;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;

import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static com.ai_app_be.support.TestUrlUtils.encodeQueryParam;

class UserControllerTest extends BaseControllerTest {
    private static final JsonMapper JSON_MAPPER = JsonMapper.builder().build();

    @Test
    void createsAndFetchesUser() throws Exception {
        HttpResponse<String> createResponse = createUser("controller-ada", "controller-ada@example.com");

        assertThat(createResponse.statusCode()).isEqualTo(201);
        assertThat(createResponse.body()).contains("\"username\":\"controller-ada\"");
        assertThat(createResponse.body()).contains("\"email\":\"controller-ada@example.com\"");
        assertThat(createResponse.body()).doesNotContain("passwordHash");

        long id = readLongField(createResponse.body(), "id");

        HttpResponse<String> getResponse = get("/users/" + id);

        assertThat(getResponse.statusCode()).isEqualTo(200);
        assertThat(getResponse.body()).contains("\"username\":\"controller-ada\"");
    }

    @Test
    void findsUserByUsernameAndEmail() throws Exception {
        createUser("controller-grace", "controller-grace@example.com");

        HttpResponse<String> byUsername = get("/users/by-username?username=controller-grace");
        HttpResponse<String> byEmail = get("/users/by-email?email=" + encodeQueryParam("controller-grace@example.com"));

        assertThat(byUsername.statusCode()).isEqualTo(200);
        assertThat(byUsername.body()).contains("\"username\":\"controller-grace\"");
        assertThat(byEmail.statusCode()).isEqualTo(200);
        assertThat(byEmail.body()).contains("\"email\":\"controller-grace@example.com\"");
    }

    @Test
    void listsAndDeletesUsers() throws Exception {
        HttpResponse<String> createResponse = createUser("controller-delete", "controller-delete@example.com");
        long id = readLongField(createResponse.body(), "id");

        HttpResponse<String> listResponse = get("/users");
        HttpResponse<String> deleteResponse = delete("/users/" + id);
        HttpResponse<String> getDeletedResponse = get("/users/" + id);

        assertThat(listResponse.statusCode()).isEqualTo(200);
        assertThat(listResponse.body()).contains("\"username\":\"controller-delete\"");
        assertThat(deleteResponse.statusCode()).isEqualTo(204);
        assertThat(getDeletedResponse.statusCode()).isEqualTo(404);
    }

    private HttpResponse<String> createUser(String username, String email) throws Exception {
        String json = """
                {
                  "username": "%s",
                  "email": "%s",
                  "passwordHash": "hashed-password"
                }
                """.formatted(username, email);

        return postJson("/users", json);
    }

    private long readLongField(String json, String fieldName) {
        return JSON_MAPPER.readTree(json).required(fieldName).asLong();
    }
}
