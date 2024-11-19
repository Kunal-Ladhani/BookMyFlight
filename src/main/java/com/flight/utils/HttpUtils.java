package com.flight.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Component
public class HttpUtils {

	@Autowired
	private RestTemplate restTemplate;

	// Common method for making REST API calls
	private <T> ResponseEntity<T> exchange(String url, HttpMethod method, Map<String, String> headers,
										   Map<String, String> params, Object body, Class<T> responseType) {

		// Set headers
		HttpHeaders httpHeaders = new HttpHeaders();
		if (headers != null) {
			headers.forEach(httpHeaders::set);
		}

		// Set query parameters
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		if (params != null) {
			params.forEach(builder::queryParam);
		}

		// Create HttpEntity with headers and body (for methods that support a body)
		HttpEntity<Object> entity = new HttpEntity<>(body, httpHeaders);

		// Make the REST call
		return restTemplate.exchange(builder.toUriString(), method, entity, responseType);
	}

	// GET request
	public <T> ResponseEntity<T> get(String url, Map<String, String> headers, Map<String, String> params, Class<T> responseType) {
		return exchange(url, HttpMethod.GET, headers, params, null, responseType);
	}

	// POST request
	public <T> ResponseEntity<T> post(String url, Map<String, String> headers, Map<String, String> params, Object body, Class<T> responseType) {
		return exchange(url, HttpMethod.POST, headers, params, body, responseType);
	}

	// PUT request
	public <T> ResponseEntity<T> put(String url, Map<String, String> headers, Map<String, String> params, Object body, Class<T> responseType) {
		return exchange(url, HttpMethod.PUT, headers, params, body, responseType);
	}

	// DELETE request
	public <T> ResponseEntity<T> delete(String url, Map<String, String> headers, Map<String, String> params, Class<T> responseType) {
		return exchange(url, HttpMethod.DELETE, headers, params, null, responseType);
	}

	// PATCH request
	public <T> ResponseEntity<T> patch(String url, Map<String, String> headers, Map<String, String> params, Object body, Class<T> responseType) {
		return exchange(url, HttpMethod.PATCH, headers, params, body, responseType);
	}
}

