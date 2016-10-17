/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RESTful;

import java.io.IOException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

/**
 *
 * @author iosep
 */
public class Conexion {

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
            HttpPost httpPost = new HttpPost("http://192.168.99.100:8844/ords/wfbs/" + command);
            StringEntity stringEntity = new StringEntity(json.toString());
            httpPost.setEntity(stringEntity);
            httpPost.setHeader("Content-type", "application/json");
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            responseBody = httpClient.execute(httpPost, responseHandler);
        } catch (IOException e) {
            System.out.println("IOException from " + e.getMessage());
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return responseBody;
    }

}
