package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SimplePojoTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testGetInitial() throws Exception {
        SimplePojo pojo = new SimplePojo("name", LocalDateTime.now(), Instant.now());
        Assertions.assertThat(pojo.getInstant()).isGreaterThan(Instant.MIN);
    }

    @Test
    public void testSerialization() throws Exception {
        SimplePojo orig = new SimplePojo("name", LocalDateTime.now(), Instant.now());

        SimplePojo deserialized = mapper.readValue(mapper.writeValueAsString(orig), SimplePojo.class);

        assertThat(deserialized).isEqualTo(orig);
    }
}