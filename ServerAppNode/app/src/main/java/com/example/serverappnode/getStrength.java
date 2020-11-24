package play_sound_success = "No"com.example.serverappnode;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class getStrength extends AsyncTask<String, Void, String> {
    Context cont;
    String patient_BT_address;
    String patient_BT_strength;
    String server_code;
    private String api_url = "http://192.168.29.240:4444/sortBTSignal";

    @Override
    protected String doInBackground(String... params) {
        patient_BT_strength = "";
        Log.e("API_CONTENT", "reached async for getStrength");
        patient_BT_address = params[0];
//        Using getStrengthUtil to fetch the strength
        patient_BT_strength = getStrenghtUtil(String BT_address).start();
        return patient_BT_strength;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        HttpURLConnection httpURLConnection = null;
        server_code = "";
        try {

            httpURLConnection = (HttpURLConnection) new URL(api_url).openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            JSONObject postJson = new JSONObject();
            postJson.put("bluetooth_strength", result);
            wr.write(postJson.toString().getBytes());
            wr.flush();
            wr.close();

            InputStream in = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(in);

            int inputStreamData = inputStreamReader.read();
            while (inputStreamData != -1) {
                char current = (char) inputStreamData;
                inputStreamData = inputStreamReader.read();
                server_code += current;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        if(server_code.equals("1")){
            Log.e("SERVER_RETURN","[+] IT WORKED");
        }
        else{
            Log.e("SERVER_RETURN","[-] DIDNT WORK");
        }
    }
}
