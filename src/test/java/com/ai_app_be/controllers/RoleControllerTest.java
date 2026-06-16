package com.ai_app_be.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RoleControllerTest extends com.ai_app_be.controllers.BaseControllerTest {
 
    @Test
    void listsRoles() throws Exception {
        createRole("user", "Regular user role");
        createRole("moderator", "Moderator role with editing permissions");
        java.net.http.HttpResponse<String> listResponse = get("/roles");
        assertThat(listResponse.statusCode()).isEqualTo(200);
        assertThat(listResponse.body()).contains("\"name\":\"user\"");
        assertThat(listResponse.body()).contains("\"name\":\"moderator\"");
        assertThat(listResponse.body()).contains("\"description\":\"Regular user role\"");
        assertThat(listResponse.body()).contains("\"description\":\"Moderator role with editing permissions\"");
    }
    
    @Test
    void findsRoleByName() throws Exception {
        createRole("guest", "Guest role with limited permissions");
        java.net.http.HttpResponse<String> byNameResponse = get("/roles/by-name?name=guest");
        assertThat(byNameResponse.statusCode()).isEqualTo(200);
        assertThat(byNameResponse.body()).contains("\"name\":\"guest\"");
        assertThat(byNameResponse.body()).contains("\"description\":\"Guest role with limited permissions\"");
    }

    @Test
    void createsAndFetchesRole() throws Exception {
        java.net.http.HttpResponse<String> createResponse = createRole("admin", "Administrator role with all permissions");

        assertThat(createResponse.statusCode()).isEqualTo(201);
        assertThat(createResponse.body()).contains("\"name\":\"admin\"");
        assertThat(createResponse.body()).contains("\"description\":\"Administrator role with all permissions\"");

        long id = readLongField(createResponse.body(), "id");

        java.net.http.HttpResponse<String> getResponse = get("/roles/" + id);

        assertThat(getResponse.statusCode()).isEqualTo(200);
        assertThat(getResponse.body()).contains("\"name\":\"admin\"");
        assertThat(getResponse.body()).contains("\"description\":\"Administrator role with all permissions\"");
    }

    private java.net.http.HttpResponse<String> createRole(String name, String description) throws Exception {
        String json = """
                {
                  "name": "%s",
                  "description": "%s"
                }
                """.formatted(name, description);
        return postJson("/roles", json);
    }
}
