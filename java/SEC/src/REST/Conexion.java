/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import java.io.IOException;
import java.net.URI;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

/**
 *
 * @author iosep
 */
public class Conexion {

    private String ip = "192.168.99.100";
    private String port = "8080";

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(String port) {
        this.port = port;
    }

    /**
     * Performs HTTP post.
     *
     * @param command resource to post
     * @param json resource to post
     * @return string response
     */
    public String post(String command, JSONObject json) {
        HttpClient httpClient = new DefaultHttpClient();
        String responseBody = null;
        try {
            HttpPost httpPost = new HttpPost("http://" + ip + ":" + port + "/ords/wfbs/" + command);
            StringEntity stringEntity = new StringEntity(json.toString());
            httpPost.setEntity(stringEntity);
            httpPost.setHeader("Content-type", "application/json");
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            responseBody = httpClient.execute(httpPost, responseHandler);
        } catch (IOException e) {
            System.out.println("IOException Conexion: " + e.getMessage());
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return responseBody;
    }

    public String put(String command, JSONObject json) {
        HttpClient httpClient = new DefaultHttpClient();
        String responseBody = null;
        try {
            HttpPut httpPut = new HttpPut("http://" + ip + ":" + port + "/ords/wfbs/" + command);
            StringEntity stringEntity = new StringEntity(json.toString());
            httpPut.setEntity(stringEntity);
            httpPut.setHeader("Content-type", "application/json");
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            responseBody = httpClient.execute(httpPut, responseHandler);
        } catch (IOException e) {
            System.out.println("IOException Conexion: " + e.getMessage());
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return responseBody;
    }

    public String delete(String command, JSONObject json) {
        HttpClient httpClient = new DefaultHttpClient();
        String responseBody = null;
        try {
            HttpDeleteWithBody httpDelete = new HttpDeleteWithBody("http://" + ip + ":" + port + "/ords/wfbs/" + command);
            StringEntity stringEntity = new StringEntity(json.toString());
            httpDelete.setEntity(stringEntity);
            httpDelete.setHeader("Content-type", "application/json");
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            responseBody = httpClient.execute(httpDelete, responseHandler);
        } catch (IOException e) {
            System.out.println("IOException Conexion: " + e.getMessage());
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return responseBody;
    }

    @NotThreadSafe
    class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {

        public static final String METHOD_NAME = "DELETE";

        @Override
        public String getMethod() {
            return METHOD_NAME;
        }

        public HttpDeleteWithBody(final String uri) {
            super();
            setURI(URI.create(uri));
        }

        public HttpDeleteWithBody(final URI uri) {
            super();
            setURI(uri);
        }

        public HttpDeleteWithBody() {
            super();
        }
    }

}
