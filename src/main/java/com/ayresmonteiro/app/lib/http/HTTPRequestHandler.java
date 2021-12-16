package com.ayresmonteiro.app.lib.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class HTTPRequestHandler {
	private static HttpClient httpClient = null;
	private static Map<Integer, HttpRequest> pendentRequests;

	public static HttpResponse<String> doRequest(String url, Map<String, String> headers) {
		URI uri = URI.create(url);

		HttpRequest.Builder requestBuilder = HttpRequest.newBuilder(uri);

		if (headers != null) {
			for (Entry<String, String> header : headers.entrySet()) {
				requestBuilder = requestBuilder.header(header.getKey(), header.getValue());
			}
		}

		requestBuilder = requestBuilder.GET();

		HttpRequest request = requestBuilder.build();

		HttpClient client = getHttpClient();

		HttpResponse<String> response;

		try {
			response = client.send(request, BodyHandlers.ofString());
		} catch (Exception e) {
			response = null;
		}

		return response;
	}

	public static HttpResponse<String> doJSONRequest(String url, Map<String, String> headers) {
		if (headers != null) {
			headers.replace("accept", "application/json");
		} else {
			headers = new HashMap<String, String>() {
				{
					put("accept", "application/json");
				}
			};
		}

		return doRequest(url, headers);
	}

	public static HttpClient getHttpClient() {
		if (httpClient == null) {
			httpClient = HttpClient.newHttpClient();
		}

		return httpClient;
	}
}
