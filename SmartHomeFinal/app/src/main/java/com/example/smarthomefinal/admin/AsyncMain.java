package com.example.smarthomefinal.admin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.example.smarthomefinal.R;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncMain extends AsyncTask<String, Void, String> {
    @SuppressLint("StaticFieldLeak")
    Context cont;
    public AsyncMain(Context c){
        this.cont = c;
    }
    String operation;
    @Override
    protected String doInBackground(String... params) {

        String data = "";
        operation = params[1];
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(params[0] + params[1]).openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            JSONObject postJson = new JSONObject();
//            Handling all the admin functions
            switch (params[1]) {
                case "addFamiliar":
                    Log.e("DELETE_FAM", "REACHED_addfamiliar");
                    postJson.put("username", params[2]);
                    postJson.put("password", params[3]);
                    postJson.put("admin_flag", params[4]);
                    break;
                case "deleteFamiliar":
                    Log.e("DELETE_FAM", "REACHED_deleteFamiliar");
                    Log.e("DELETE_FAM", "0:" + params[0]);
                    Log.e("DELETE_FAM", "1:" + params[1]);
                    Log.e("DELETE_FAM", "2:" + params[2]);
                    Log.e("DELETE_FAM", "3:" + params[3]);
                    postJson.put("username", params[2]);
                    postJson.put("password", params[3]);
                    break;
                case "addSufferer":
                    postJson.put("patient_name", params[2]);
                    postJson.put("address", params[3]);
                    postJson.put("sound_name", params[4]);
                    break;
                case "deleteSufferer":
                    postJson.put("patient_name", params[2]);
                    break;
                case "addFS":
                    postJson.put("familiar_name", params[2]);
                    postJson.put("patient_name", params[3]);
                    postJson.put("sound", params[4]);
                    break;
                case "deleteFS":
                    postJson.put("familiar_name", params[2]);
                    postJson.put("sufferer_name", params[3]);
                    break;
                case "addSpeaker":
                    postJson.put("speaker_name", params[2]);
                    break;
                case "deleteSpeaker":
                    postJson.put("speaker_name", params[2]);
                    break;
                default:
                    Log.e("ERROR_JSON", "NO options match");
                    break;
            }
            wr.write(postJson.toString().getBytes());
            wr.flush();
            wr.close();

            InputStream in = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(in);

            int inputStreamData = inputStreamReader.read();
            while (inputStreamData != -1) {
                char current = (char) inputStreamData;
                inputStreamData = inputStreamReader.read();
                data += current;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return data;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if(result.equals("1")){
            Toast.makeText(cont, "Successful " + operation + " operation", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(cont, "Couldn't perform " + operation, Toast.LENGTH_SHORT).show();
        }
    }
}
