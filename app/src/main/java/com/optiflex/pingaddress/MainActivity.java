package com.optiflex.pingaddress;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    Button btn_ping_address;
    public static final String TAG="PING-STATUS";

    public static final String HOST = "192.168.8.100";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_ping_address = findViewById(R.id.btn_ping_address);

        new PingAddressAsyncTask().execute(HOST);
    }



    public static class PingAddressAsyncTask extends AsyncTask<String,Void,Void>
    {

        public PingAddressAsyncTask()
        {
        }

        public boolean pingByCrunchy()
        {
            Socket newSocket = new Socket();
            try {
                newSocket.connect(new InetSocketAddress(HOST,80),2000);
                return true;
            }catch (IOException e)
            {
                e.printStackTrace();

                return false;
            }

        }

        public void checkAddressNormal(String Host) {
            try {
                InetAddress addr=InetAddress.getByName(HOST);
                boolean reached = addr.isReachable(2000);
                if (reached)
                {
                    Log.i(TAG,"----------------------------ADDRESS REACHED-------------------------");
                }else {
                    Log.i(TAG,"------------------------------NOT REACHED------------------------------------");
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        @Override
        protected Void doInBackground(String... strings) {
            checkAddressNormal(strings[0]);
           /** boolean returned=pingByCrunchy();
            if(returned)
            {
                Log.i(TAG,"CRUNCH CONNECTION TRUE");
            }else {
                Log.i(TAG,"CRUNCH CONNECTION FALSE");
            }
            **/

            return null;
        }
    }


}