package com.ai_app_be.services;

import com.ai_app_be.model.User;
import com.ai_app_be.support.BaseServiceTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class UserServiceTest extends BaseServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void savesAndFindsUserWithBaseServiceMethods() {
        User saved = userService.save(new User("service-ada", "service-ada@example.com", "hashed-password"));

        assertThat(saved.getId()).isNotNull();
        assertThat(userService.existsById(saved.getId())).isTrue();
        assertThat(userService.findById(saved.getId()))
                .hasValueSatisfying(found -> assertThat(found.getUsername()).isEqualTo("service-ada"));
        assertThat(userService.findAll())
                .extracting(User::getUsername)
                .contains("service-ada");
    }

    @Test
    void findsAndChecksUsersByUsernameAndEmail() {
        User saved = userService.save(new User("service-grace", "service-grace@example.com", "hashed-password"));

        assertThat(userService.findByUsername("service-grace"))
                .hasValueSatisfying(found -> assertThat(found.getId()).isEqualTo(saved.getId()));
        assertThat(userService.findByEmail("service-grace@example.com"))
                .hasValueSatisfying(found -> assertThat(found.getId()).isEqualTo(saved.getId()));
        assertThat(userService.existsByUsername("service-grace")).isTrue();
        assertThat(userService.existsByEmail("service-grace@example.com")).isTrue();
    }

    @Test
    void deletesUserById() {
        User saved = userService.save(new User("service-delete", "service-delete@example.com", "hashed-password"));

        userService.deleteById(saved.getId());

        assertThat(userService.existsById(saved.getId())).isFalse();
    }
}
