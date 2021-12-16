package com.ayresmonteiro.app.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

import com.ayresmonteiro.app.lib.http.HTTPRequestHandler;

/**
 * Unit test for simple App.
 */
public class HTTPRequestHandlerUnitTests {
    @Test
    public void assertIfAcceptJSONHeaderIsSet() {
        Map<String, String> headers = HTTPRequestHandler.addAcceptJSONToHeaders(null);

        assertNotEquals(null, headers);
    }

    @Test
    public void assertIfAcceptJSONHeaderWasOverwrited() {
        Map<String, String> headers = new HashMap<String, String>() {
            {
                put("accept", "application/xml");
            }
        };

        headers = HTTPRequestHandler.addAcceptJSONToHeaders(null);

        assertEquals("application/json", headers.get("accept"));
    }

    @Test
    public void assertIfURIWasBuildedCorrectly() {
        String[] availableURIs = {
                "https://google.com",
                "https://facebook.com",
                "https://twitter.com",
        };

        Random rand = ThreadLocalRandom.current();

        String selectedURI = availableURIs[rand.nextInt((availableURIs.length - 0))];

        Map<String, String> headers = HTTPRequestHandler.addAcceptJSONToHeaders(null);

        HttpRequest request = HTTPRequestHandler.createBaseRequestBuilder(selectedURI, headers).build();

        assertEquals(selectedURI, request.uri().toString());
    }
}
