package com.ayresmonteiro.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.ayresmonteiro.app.lib.http.HTTPRequestHandler;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void assertResponseIsNotNull() {
        HttpResponse<String> response = HTTPRequestHandler
                .doJSONRequest("https://pokeapi.co/api/v2/pokemon/charizard/", null);

        assertNotEquals(null, response);
    }

    @Test
    public void assertResponseIsString() {
        HttpResponse<String> response = HTTPRequestHandler
                .doJSONRequest("https://pokeapi.co/api/v2/pokemon/charizard/", null);

        assertEquals(" ".getClass(), response.body().getClass());
    }
}
