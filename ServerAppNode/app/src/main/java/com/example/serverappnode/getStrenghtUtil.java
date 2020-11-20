package com.example.serverappnode;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class getStrengthUtil{
   String BT_address;
   String BT_strength;
    public getStrengthUtil(String BT_address){
        this.BT_address = BT_address;
   }
   public String start(){
//        Library function for Android 5.0+ devices, to send out broadcast scan.
       mBluetoothLeScanner = mBluetoothAdapter.getBluetoothLeScanner();
//       Starting scanner and Callback method mentioned
       mBluetoothLeScanner.startScan(customOnScanResult);
   }

//   Device Scan Callback
    private ScanCallBack = new ScanCallBack() {
        public void customOnScanResult ( int callBackType, ScanResult result){
            String retrievedAddress = result.getDevice();
            if(retrievedAddress.getAddress().toString().equals(BT_address));
                return String(result.getRssi());
        }
    }
}
