package com.example.serverappnode;

import android.os.AsyncTask;
import android.util.Log;

public class playSoundPhone extends AsyncTask<String, Void, String> {
    String sound_name;
    String play_sound_success;
    String path = "/tmp/ServerNodeApp/temporary_sounds/"
    @Override
    protected String doInBackground(String... params) {
        sound_name = params[0];
//      Part of the code where we tell the device to play a certain sound
        MediaPlayer mp = new MediaPlayer();

        try {
            mp.setDataSource(path + sound_name);
            mp.prepare();
            mp.start();
            play_sound_success = "Yes";
        } catch (Exception e) {
            e.printStackTrace();
            play_sound_success = "No";
        }
        return play_sound_success;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if(result.equals("Yes")){
            Log.e("Sound Play", "[+] Playing sound");
        }
        else{
            Log.e("Sound Play", "[-] Couldn't find file");
        }
    }
}
