package com.example.a1342097.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Utility class to make HTTP server requests.
 * - Supports JSON request bodies only.
 * @author Ian Clement (ian.clement@johnabbott.qc.ca)
 */
public class HttpRequest {

    /**
     * Represents the supported HTTP request methods
     */
    public enum Method { GET, POST, PUT, DELETE }

    /* private helper for make */
    private static void copyStreamBuffered(InputStream in, OutputStream out) throws IOException {
        BufferedInputStream inBuf = new BufferedInputStream(in, 1024);
        BufferedOutputStream outBuf = new BufferedOutputStream(out, 1024);
        int i;
        while((i = inBuf.read()) > 0)
            outBuf.write(i);
        outBuf.flush();
    }

    /**
     * Make an HTTP Request
     * @param urlStr URL for request
     * @param method HTTP method
     * @return An HttpResponse with status, headers and response body (if given).
     * @throws IOException
     */
    public static HttpResponse make(String urlStr, Method method) throws IOException {
        return make(urlStr, method, null);
    }


    /**
     * Make an HTTP Request with JSON body
     * @param urlStr URL for request
     * @param method HTTP method
     * @param requestJson The JSON resquest body
     * @return An HttpResponse with status, headers and response body (if given).
     * @throws IOException
     */
    public static HttpResponse make(String urlStr, Method method, String requestJson) throws IOException {

        // Create URL object for HTTP connection
        URL url = new URL(urlStr);

        // Create HTTP connection from URL
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(method.toString().toUpperCase());

        // If the user has specified JSON for the request, configure connection, add content-type header
        // and copy JSON to output stream
        if(requestJson != null) {
            con.setDoOutput(true);
            con.setRequestProperty("content-type", "application/json");
            copyStreamBuffered(new ByteArrayInputStream(requestJson.getBytes()), con.getOutputStream());
        }

        // Retrieve information connection
        Map<String, List<String>> headers = con.getHeaderFields();
        int status = con.getResponseCode();
        String response = null;
        try {
            // copy JSON from output stream
            ByteArrayOutputStream responseBytes = new ByteArrayOutputStream();
            copyStreamBuffered(con.getInputStream(), responseBytes);
            response = new String(responseBytes.toByteArray());
        }
        catch(IOException e) { /* no response body */ }

        return new HttpResponse(status, headers, response);
    }


    // make HttpRequest non-instantiable: "utility" class.
    private HttpRequest() {
    }
}
