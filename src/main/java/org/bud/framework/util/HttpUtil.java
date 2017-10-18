package org.bud.framework.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chenlong
 * Date：2017/9/26
 * time：11:07
 */
public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private static final String USER_AGENT = "Mozilla/5.0";

    public static String doPost(String url, Object argument, Map<String, String> header) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        //请求报文头
        if (null != header) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                httpPost.addHeader(entry.getKey(), entry.getValue());
            }
        }
        //封装参数
        if(argument instanceof Map){
            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            if (null!=argument){
                for (Map.Entry<String, String> entry : ((Map<String,String>)argument).entrySet()) {
                    urlParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
            }
            HttpEntity postParams = new UrlEncodedFormEntity(urlParameters);
            httpPost.setEntity(postParams);
        } else if (argument instanceof String) {
            if (argument != null) {
                StringEntity stringEntity = new StringEntity((String)argument);
                stringEntity.setContentEncoding("UTF-8");
                httpPost.setEntity(stringEntity);
            }
        }

        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        logger.info("POST Response Status: " + httpResponse.getStatusLine().getStatusCode());
        BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();
        httpClient.close();

        logger.info("response content: " + response.toString());
        return response.toString();
    }


    public static String doGet(String url, Map<String, String> params) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        if (null!=params){
            String s = "";
            for (Map.Entry<String, String> entry : params.entrySet()) {
                s += "&" + entry.getKey() + "=" + entry.getValue();
            }
            url += s.replaceFirst("&", "?");
        }

        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("User-Agent", USER_AGENT);
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        logger.info("GET Response Status: " + httpResponse.getStatusLine().getStatusCode());

        BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();
        httpClient.close();

        logger.info("response content: " + response.toString());
        return response.toString();
    }

}
