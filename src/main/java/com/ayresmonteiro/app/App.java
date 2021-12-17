package com.ayresmonteiro.app;

import java.net.http.HttpResponse;

import com.ayresmonteiro.app.lib.format.JSONableHashMap;
import com.ayresmonteiro.app.lib.http.HTTPRequestHandler;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        JSONableHashMap<String, String> body = new JSONableHashMap<String, String>() {
            {
                put("email", "any@mail.com");
                // ... More Data
            }
        };

        HttpResponse<String> response = HTTPRequestHandler
                .doPostJSONRequest("http://localhost/post/route", null, body.toJson());

        if (response != null) {
            System.err.println(response.body());
        } else {
            System.err.println("POST: Null");
        }

        response = HTTPRequestHandler
                .doGetJSONRequest("http://localhost/get/route", null);

        if (response != null) {
            System.out.println(response.body());
        } else {
            System.out.println("GET: Null");
        }
    }
}
