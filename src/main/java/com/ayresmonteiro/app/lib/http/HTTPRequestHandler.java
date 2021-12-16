package com.ayresmonteiro.app.lib.http;

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

	public static HttpRequest.Builder createBaseRequestBuilder(String url, Map<String, String> headers) {
		URI uri = URI.create(url);

		HttpRequest.Builder requestBuilder = HttpRequest.newBuilder(uri);

		if (headers != null) {
			for (Entry<String, String> header : headers.entrySet()) {
				requestBuilder = requestBuilder.header(header.getKey(), header.getValue());
			}
		}

		return requestBuilder;
	}

	public static HttpResponse<String> sendRequest(HttpRequest request) {
		HttpClient client = getHttpClient();

		HttpResponse<String> response;

		try {
			response = client.send(request, BodyHandlers.ofString());
		} catch (Exception e) {
			response = null;
		}

		return response;
	}

	public static Map<String, String> addAcceptJSONToHeaders(Map<String, String> headers) {
		if (headers != null) {
			headers.replace("accept", "application/json");
		} else {
			headers = new HashMap<String, String>() {
				{
					put("accept", "application/json");
				}
			};
		}

		return headers;
	}

	public static HttpResponse<String> doGetRequest(String url, Map<String, String> headers) {
		HttpRequest.Builder requestBuilder = createBaseRequestBuilder(url, headers);

		requestBuilder = requestBuilder.GET();

		HttpRequest request = requestBuilder.build();

		return sendRequest(request);
	}

	public static HttpResponse<String> doGetJSONRequest(String url, Map<String, String> headers) {
		headers = addAcceptJSONToHeaders(headers);

		return doGetRequest(url, headers);
	}

	public static HttpClient getHttpClient() {
		if (httpClient == null) {
			httpClient = HttpClient.newHttpClient();
		}

		return httpClient;
	}
}
