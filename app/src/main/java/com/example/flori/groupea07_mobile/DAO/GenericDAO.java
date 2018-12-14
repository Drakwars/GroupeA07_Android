package com.example.flori.groupea07_mobile.DAO;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.example.flori.groupea07_mobile.Model.Callback;
import com.example.flori.groupea07_mobile.Model.Member;
import com.example.flori.groupea07_mobile.Model.MemberSession;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class GenericDAO extends AsyncTask<String, Void, String>{
    private static String base_url = "https://groupea07.azurewebsites.net/api/";
    private String spec_url;
    private Callback callback;

    public GenericDAO(Callback callback) {this.callback = callback;}

    @Override
    protected String doInBackground(String... params)
    {
        spec_url = params[0];
        String type = params[1];
        //Should be a user json
        JSONObject obj = new JSONObject();
        try {
            obj = new JSONObject(params[2]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try
        {
            return httpQuery(type,obj);
        }
        catch (IOException e)
        {
            return "Unable to retrieve web page. URL may be invalid.";
        } catch (JSONException e) {
            return "The json object is invalid.";
        }


    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        callback.onPreExecute("user");
    }

    @Override
    protected void onPostExecute(String result)
    {
        super.onPostExecute(result);
        callback.onPostExecute(result);
    }

    private String httpQuery(String type,JSONObject obj) throws IOException, JSONException {
        URL url = new URL(base_url+spec_url);

        // 1. create HttpURLConnection
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(type);
        conn.addRequestProperty("Content-Type", "application/json; charset=utf-8");


        // 3. add JSON content to POST request body
        if(obj.length() != 0)
            setRequestContent(conn, obj);

        // 4. make POST request to the given URL
        conn.connect();

        // 5. return response message

        //The api was called successfully
        if(conn.getResponseCode() == 200)
        {
            return getOutput(conn.getInputStream());
        }
        else
        {
            return "Error"+getOutput(conn.getErrorStream());
        }
    }

    private String getOutput(InputStream stream) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        StringBuilder sb = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
            sb.append(output);
        }
        return sb.toString();
    }

    private void setRequestContent(HttpURLConnection conn, JSONObject jsonObject) throws IOException
    {
        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(jsonObject.toString());
        writer.flush();
        writer.close();
        os.close();
    }
}

