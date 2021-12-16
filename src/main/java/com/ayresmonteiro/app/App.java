package com.ayresmonteiro.app;

import java.net.http.HttpResponse;

import com.ayresmonteiro.app.lib.http.HTTPRequestHandler;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        HttpResponse<String> response = HTTPRequestHandler
                .doJSONRequest("https://pokeapi.co/api/v2/pokemon/charizard/", null);

        if (response != null) {
            System.out.println(response.body());
        } else {
            System.out.println("Null");
        }
    }
}
