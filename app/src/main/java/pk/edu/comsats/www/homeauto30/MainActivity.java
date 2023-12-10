package pk.edu.comsats.www.homeauto30;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.UUID;

public class MainActivity extends Activity{

    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    TextView textView8;
    TextView textView9;
    TextView textView10;
    TextView textView11;
    ToggleButton toggleButton1;
    ToggleButton toggleButton2;
    ToggleButton toggleButton3;
    ToggleButton toggleButton4;
    ToggleButton toggleButton5;
    ToggleButton toggleButton6;
    ToggleButton toggleButton7;
    ToggleButton toggleButton8;
    ToggleButton toggleButton9;
    ToggleButton toggleButton10;
    ToggleButton toggleButton11;
    final int RECIEVE_MESSAGE = 1;        // Status  for Handler
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private StringBuilder sb = new StringBuilder();
    private static final String TAG = "bluetooth2";
    private ConnectedThread mConnectedThread;
    // SPP UUID service
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    // MAC-address of Bluetooth module
    private static String address;
    Handler h;

    AlertDialog alert2;
    ImageView edit;
    ProgressDialog progressDialog;
    public SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_main);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,    R.layout.maintitlebar);
        edit = (ImageView) findViewById(R.id.pencil);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");

        textView1 = (TextView) findViewById(R.id.textView11);
        textView2 = (TextView) findViewById(R.id.textView12);
        textView3 = (TextView) findViewById(R.id.textView13);
        textView4 = (TextView) findViewById(R.id.textView14);
        textView5 = (TextView) findViewById(R.id.textView15);
        textView6 = (TextView) findViewById(R.id.textView16);
        textView7 = (TextView) findViewById(R.id.textView17);
        textView8 = (TextView) findViewById(R.id.textView18);
        textView9 = (TextView) findViewById(R.id.textView19);
        textView10 = (TextView) findViewById(R.id.textView20);
        textView11 = (TextView) findViewById(R.id.textView21);
        toggleButton1 = (ToggleButton) findViewById(R.id.toggleButton10);
        toggleButton2 = (ToggleButton) findViewById(R.id.toggleButton11);
        toggleButton3 = (ToggleButton) findViewById(R.id.toggleButton12);
        toggleButton4 = (ToggleButton) findViewById(R.id.toggleButton13);
        toggleButton5 = (ToggleButton) findViewById(R.id.toggleButton14);
        toggleButton6 = (ToggleButton) findViewById(R.id.toggleButton15);
        toggleButton7 = (ToggleButton) findViewById(R.id.toggleButton16);
        toggleButton8 = (ToggleButton) findViewById(R.id.toggleButton17);
        toggleButton9 = (ToggleButton) findViewById(R.id.toggleButton18);
        toggleButton10 = (ToggleButton) findViewById(R.id.toggleButton19);
        toggleButton11 = (ToggleButton) findViewById(R.id.toggleButton20);




        h = new Handler() {
            public void handleMessage(android.os.Message msg) {
                switch (msg.what) {
                    case RECIEVE_MESSAGE:                                                    // if receive massage
                        byte[] readBuf = (byte[]) msg.obj;
                        String strIncom = new String(readBuf, 0, msg.arg1);                    // create string from bytes array
                        sb.append(strIncom);

                        // append string
                        int endOfLineIndex = sb.indexOf("\r\n");                            // determine the end-of-line
                        if (endOfLineIndex > 0) {                                            // if end-of-line,
                            String sbprint = sb.substring(0, endOfLineIndex);
                            Log.d(TAG,sbprint);
                            // extract string

                               if(sb.charAt(0)=='#') {
                                  try {

                                          if (sb.substring(1, 2).equals("0")) {
                                              toggleButton1.setChecked(false);
                                              // Log.d(TAG, "If 1 working");
                                          } else //if (sb.substring(1, 4).equals("ON1")) {
                                              toggleButton1.setChecked(true);
                                              //  Log.d(TAG, "else 1 working");



                                      if (sb.substring(2, 3).equals("0") )
                                      {
                                          //Log.d(TAG,"If 2 working");
                                          toggleButton2.setChecked(false);//txtArduino.setText("Data from pin 2: " + sbprint);
                                      }
                                      else// if (sb.substring(4, 7).equals("ON2"))
                                          toggleButton2.setChecked(true);
                                          //Log.d(TAG, "else 2 working");

                                          if (sb.substring(3, 4).equals("0"))
                                              toggleButton3.setChecked(false);//txtArduino.setText("Data from pin 2: " + sbprint);
                                          else //if (sb.substring(7, 10).equals("ON3"))
                                              toggleButton3.setChecked(true);

                                          if (sb.substring(4, 5).equals("0"))
                                              toggleButton4.setChecked(false);//txtArduino.setText("Data from pin 2: " + sbprint);
                                          else //if (sb.substring(10, 13).equals("ON4"))
                                              toggleButton4.setChecked(true);

                                          if (sb.substring(5,6).equals("0"))
                                              toggleButton5.setChecked(false);//
                                          else //if (sb.substring(13, 16).equals("ON5"))
                                              toggleButton5.setChecked(true);

                                          if (sb.substring(6, 7).equals("0")) {
                                              toggleButton6.setChecked(false);

                                          } else //if (sb.substring(16, 19).equals("ON6")) {
                                              toggleButton6.setChecked(true);


                                              if (sb.substring(7, 8).equals("0")) {
                                                  //Log.d(TAG,"If 2 working");
                                                  toggleButton7.setChecked(false);//txtArduino.setText("Data from pin 2: " + sbprint);
                                              } else// if (sb.substring(19, 22).equals("ON7")) {
                                                  toggleButton7.setChecked(true);
                                                  //Log.d(TAG, "else 2 working");



                                              if (sb.substring(8, 9).equals("0"))
                                                  toggleButton8.setChecked(false);//txtArduino.setText("Data from pin 2: " + sbprint);
                                              else //if (sb.substring(22, 25).equals("ON8"))
                                                  toggleButton8.setChecked(true);


                                              if (sb.substring(9, 10).equals("0"))
                                                  toggleButton9.setChecked(false);//txtArduino.setText("Data from pin 2: " + sbprint);
                                              else //if (sb.substring(25, 28).equals("ON9"))
                                                  toggleButton9.setChecked(true);


                                              if (sb.substring(10,11).equals("0"))
                                                  toggleButton10.setChecked(false);//
                                              else// if (sb.substring(28, 31).equals("ONB"))
                                                  toggleButton10.setChecked(true);


                                              if (sb.substring(11,12).equals("0"))
                                                  toggleButton11.setChecked(false);//
                                              else //if (sb.substring(31, 34).equals("ONA"))
                                                  toggleButton11.setChecked(true);


                                  }
                                   catch (Exception e){
                                       Log.d(TAG,"Exception Caught");

                                   }


                               }else{
                                   mConnectedThread.write("c");

                               }
                            sb.delete(0, sb.length());

                        }
                        break;

                }
                //Log.d(TAG, "...String:"+ sb.toString() +  "Byte:" + msg.arg1 + "...");

            }

        };
        btAdapter = BluetoothAdapter.getDefaultAdapter();        // get Bluetooth adapter

        //Toast.makeText(this,text,Toast.LENGTH_LONG).show();


    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog.Builder builder2=new AlertDialog.Builder(this);
        builder.setTitle("HomeAuto");
        builder.setMessage("Quitt Application?");
        builder2.setTitle("Bluetooth");
        builder2.setMessage("Disable Bluetooth also?");
        builder2.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btAdapter.disable();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        // Do nothing but close the dialog
        builder2.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                if(btAdapter.isEnabled())
                alert2.show();
                else {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }


            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert2 = builder2.create();
        alert.show();
        //your code when back button pressed
    }

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        if (Build.VERSION.SDK_INT >= 10) {
            try {
                final Method m = device.getClass().getMethod("createInsecureRfcommSocketToServiceRecord", new Class[]{UUID.class});
                return (BluetoothSocket) m.invoke(device, MY_UUID);
            } catch (Exception e) {
              //  Log.e(TAG, "Could not create Insecure RFComm Connection", e);
            }
        }
        return device.createRfcommSocketToServiceRecord(MY_UUID);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
        textView1.setText(sharedPreferences.getString("Text0","Load 1"));
        textView2.setText(sharedPreferences.getString("Text1","Load 2"));
        textView3.setText(sharedPreferences.getString("Text2","Load 3"));
        textView4.setText(sharedPreferences.getString("Text3","Load 4"));
        textView5.setText(sharedPreferences.getString("Text4","Load 5"));
        textView6.setText(sharedPreferences.getString("Text5","Load 6"));
        textView7.setText(sharedPreferences.getString("Text6","Load 7"));
        textView8.setText(sharedPreferences.getString("Text7","Load 8"));
        textView9.setText(sharedPreferences.getString("Text8","Load 9"));
        textView10.setText(sharedPreferences.getString("Text9","Load 10"));
        textView11.setText(sharedPreferences.getString("Text10","Load 11"));

        Intent intent = getIntent();

        //Get the MAC address from the DeviceListActivty via EXTRA

        address = intent.getStringExtra(DeviceListActivity.EXTRA_DEVICE_ADDRESS);

        Log.d(TAG, "...onResume - try connect...");

        // Set up a pointer to the remote node using it's address.
        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        // Two things are needed to make a connection:
        //   A MAC address, which we got above.
        //   A Service ID or UUID.  In this case we are using the
        //     UUID for SPP.

        try {
            btSocket = createBluetoothSocket(device);
        } catch (IOException e) {
            errorExit("Fatal Error", "In onResume() and socket create failed: " + e.getMessage() + ".");
        }

    /*try {
      btSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
    } catch (IOException e) {
      errorExit("Fatal Error", "In onResume() and socket create failed: " + e.getMessage() + ".");
    }*/

        // Discovery is resource intensive.  Make sure it isn't going on
        // when you attempt to connect and pass your message.
        btAdapter.cancelDiscovery();

        // Establish the connection.  This will block until it connects.
        // Log.d(TAG, "...Connecting...");
        try {
            btSocket.connect();
            // Log.d(TAG, "....Connection ok...");
            Toast.makeText(MainActivity.this,
                    "Connection established with your home",
                    Toast.LENGTH_SHORT).show();


        } catch (IOException e) {
            try {
                btSocket.close();

                Toast.makeText(MainActivity.this,
                        "Connection Not established with your home",
                        Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this, DeviceListActivity.class);
                startActivity(i);
                //return;
            } catch (IOException e2) {
                errorExit("Fatal Error", "In onResume() and unable to close socket during connection failure" + e2.getMessage() + ".");
            }
        }


        // Create a data stream so we can talk to server.
        //  Log.d(TAG, "...Create Socket...");

        mConnectedThread = new ConnectedThread(btSocket);
        mConnectedThread.start();

        mConnectedThread.write("c");


    }

    public void CallC(View v){
        switch (v.getId()){
            case R.id.textView:
                Intent i = new Intent(MainActivity.this, ActivityC.class);
                startActivity(i);
                break;
            case R.id.pencil:
                Intent i2 = new Intent(MainActivity.this, ActivityC.class);
                startActivity(i2);
                break;
            case R.id.toggleButton10:

                if(toggleButton1.isChecked())
                mConnectedThread.write("1");
                else
                    mConnectedThread.write("2");
               // Log.d(TAG,"Button1 Pressed");
                break;
            case R.id.toggleButton11:
                if(toggleButton2.isChecked())
                    mConnectedThread.write("3");
                else
                    mConnectedThread.write("4");
               // Log.d(TAG,"Button2 Pressed");
                break;
            case R.id.toggleButton12:
                if(toggleButton3.isChecked())
                    mConnectedThread.write("5");
                else
                    mConnectedThread.write("6");
               // Log.d(TAG,"Button3 Pressed");
                break;
            case R.id.toggleButton13:
                if(toggleButton4.isChecked())
                    mConnectedThread.write("7");
                else
                    mConnectedThread.write("8");
               // Log.d(TAG,"Button4 Pressed");
                break;
            case R.id.toggleButton14:
                if(toggleButton5.isChecked())
                    mConnectedThread.write("9");
                else
                    mConnectedThread.write("0");
               // Log.d(TAG,"Button5 Pressed");
                break;
            case R.id.toggleButton15:
                if(toggleButton6.isChecked())
                    mConnectedThread.write("A");
                else
                    mConnectedThread.write("F");
               // Log.d(TAG,"Button6 Pressed");
                break;
            case R.id.toggleButton16:
                if(toggleButton7.isChecked())
                    mConnectedThread.write("B");
                else
                    mConnectedThread.write("G");
              //  Log.d(TAG,"Button7 Pressed");
                break;
            case R.id.toggleButton17:
                if(toggleButton8.isChecked())
                    mConnectedThread.write("C");
                else
                    mConnectedThread.write("H");
              //  Log.d(TAG,"Button8 Pressed");
                break;
            case R.id.toggleButton18:
                if(toggleButton9.isChecked())
                    mConnectedThread.write("D");
                else
                    mConnectedThread.write("I");
              //  Log.d(TAG,"Button9 Pressed");
                break;
            case R.id.toggleButton19:
                if(toggleButton10.isChecked())
                    mConnectedThread.write("E");
                else
                    mConnectedThread.write("J");
              //  Log.d(TAG,"Button10 Pressed");
                break;
            case R.id.toggleButton20:
                if(toggleButton11.isChecked())
                    mConnectedThread.write("K");
                else
                    mConnectedThread.write("L");
              //  Log.d(TAG,"Button11 Pressed");
                break;
        }


        }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            btSocket.close();
        } catch (IOException e2) {
            errorExit("Fatal Error", "In onPause() and failed to close socket." + e2.getMessage() + ".");
        }

    }

    private void checkBTState() {
        // Check for Bluetooth support and then check to make sure it is turned on
        // Emulator doesn't support Bluetooth and will return null
        if (btAdapter == null) {
            errorExit("Fatal Error", "Bluetooth not support");
        } else {
            if (!btAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
                //    Log.d(TAG, "...Bluetooth ON...");
            }
        }
    }

    private void errorExit(String title, String message) {
        Toast.makeText(getBaseContext(), title + " - " + message, Toast.LENGTH_LONG).show();
        finish();
    }




    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[256];  // buffer store for the stream
            int bytes; // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    Log.d(TAG, "...Reading Data...");
                    // Read from the InputStream
                    bytes = mmInStream.read(buffer);        // Get number of bytes and message in "buffer"
                    h.obtainMessage(RECIEVE_MESSAGE, bytes, -1, buffer).sendToTarget();        // Send to message queue Handler
                } catch (IOException e) {
                    Log.d(TAG, "...Can not read data...");
                    break;
                }
            }
        }

        /* Call this from the main activity to send data to the remote device */
        public void write(String message) {
            Log.d(TAG, "...Data to send: " + message + "...");
            byte[] msgBuffer = message.getBytes();
            try {
                mmOutStream.write(msgBuffer);
            } catch (IOException e) {
               // Toast.makeText(MainActivity.this, "Could not Send data", Toast.LENGTH_LONG).show();
            }
        }
    }

}