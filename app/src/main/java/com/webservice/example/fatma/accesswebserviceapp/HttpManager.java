package com.webservice.example.fatma.accesswebserviceapp;

/**
 * Created by Fatma on 2015-11-10.
 */
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpManager {

    public static String getData(RequestPackage p) {

        Log.d("HttpManager", " getting data from web");
        BufferedReader reader = null;
        String uri = p.getUri();
        int status;

        try {
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Content-Type", "application/json");
            //con.setRequestProperty("Accept","application/json" );
            con.setRequestMethod("POST");
            Log.d("HttpManager", " connected by now!");

            //paremters to be serailised to jason format
            JSONObject json = new JSONObject(p.getParams());

            //Log.d("HttpManager", "value: "+ json.toString(1));
            String params =  json.toString();
// writing the parmerts to the body
            if (p.getMethod().equals("POST")) {
                //allow to put some content to the body of the request
                Log.d("HttpManager", " we are doing POST");
                con.setDoOutput(true);
                // writer to send information to the comnection
                OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
                // write to the body of the requst
                writer.write(params);
                //insure whatevr we wrote to the memory have sent to the server
                writer.flush();
                Log.d("HttpManager", " flush has happened");
            }
            //check the response code first
            status = con.getResponseCode();
            Log.d("HttpMAnager", "value: "+ status) ;

            // ready to get content from the web
            StringBuilder sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            // ready to read the code line by line
            String line;
            while ((line = reader.readLine()) != null) {
                Log.d("HttpMAnager", "value: "+ line) ;
                sb.append(line + "\n");
            } // we recived all the content from the request
            Log.d("HttpMAnager", "value: "+ sb.toString()) ;
            return sb.toString();
            // error handling and clean up
        } catch (Exception e) {
            // e.printStackTrace();
            Log.e("MYAPP", "exception", e);
            Log.d("HttpManager", " exception happened and null will return");

            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("HttpManager", "second  exception happened and null will return");
                    return null;
                }
            }
        }

    }

}
