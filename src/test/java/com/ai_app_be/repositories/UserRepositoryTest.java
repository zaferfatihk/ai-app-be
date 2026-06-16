package com.ai_app_be.repositories;

import com.ai_app_be.model.User;
import com.ai_app_be.support.BaseRepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTest extends BaseRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void savesUserWithBaseModelFields() {
        User user = new User("ada", "ada@example.com", "hashed-password");

        User saved = userRepository.saveAndFlush(user);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getVersion()).isNotNull();
        assertThat(saved.getCreatedAt()).isNotNull();
        assertThat(saved.getUpdatedAt()).isNotNull();
        assertThat(userRepository.findByUsername("ada"))
                .hasValueSatisfying(found -> assertThat(found.getId()).isEqualTo(saved.getId()));
        assertThat(userRepository.findByEmail("ada@example.com"))
                .hasValueSatisfying(found -> assertThat(found.getId()).isEqualTo(saved.getId()));
    }
}
