package com.example.serverappnode;

import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import fi.iki.elonen.NanoHTTPD;

public class serverReceive extends NanoHTTPD {
    private final static int PORT = 8080;

    public serverReceive() throws IOException {
        super(PORT);
        start();
        Log.e("START", "The Server is running on 8080");
    }

    @Override
    public Response serve(IHTTPSession session) {
        Log.e("SVV", session.getUri());
        String msg = "<html><body><h1>Hello Server</h1>\n";
        msg += "<p>We serve " + session.getUri() + " !</p>";
        String[] api = session.getUri().split("/",5);
//        Manually routing through string comparison
        if(api[0].equals("getStrength")){
            new getStrength().execute(api[1]);
        }
        else if(api[0].equals("playSoundPhone")){
            new playSoundPhone().execute(api[1]);
        }
        return newFixedLengthResponse( msg + "</body></html>\n" );
    }
}
