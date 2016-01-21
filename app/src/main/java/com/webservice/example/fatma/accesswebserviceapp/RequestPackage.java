package com.webservice.example.fatma.accesswebserviceapp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fatma on 2015-11-10.
 */
public class RequestPackage {
    private String uri;
    private String method = "POST";

    // we could have any number of parametrs here as a pair of value and key
    private Map<String,String> params = new HashMap<>();

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
    // So we can pass one by one paremetrs instead of all of them at once.
    public void setParam(String key,String value){
        params.put(key,value);
    }
    // browser usually does this adding key=value & the second parameter
    public String getEncodedParams() {
        StringBuilder sb = new StringBuilder();
        for (String key : params.keySet()) {
            String value = null;
            try {
                value = URLEncoder.encode(params.get(key), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(key + "=" + value);
        }
        return sb.toString();
    }
}



