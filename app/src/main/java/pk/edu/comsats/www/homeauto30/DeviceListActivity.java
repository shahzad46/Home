package pk.edu.comsats.www.homeauto30;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;
import java.util.UUID;

public class DeviceListActivity extends Activity {
int flag=1;
    private static final String TAG = "Bluetooth2";
    private static final boolean D = true;
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private LinearLayout loadingsection=null;
    private ProgressBar progressBar;
    String address;
    String info;
    // declare textview for connection status

    TextView textView1;

    // EXTRA string to send on to mainactivity
    public static String EXTRA_DEVICE_ADDRESS = "device_address";

    // Member fields
    AlertDialog alert2;
    private BluetoothAdapter mBtAdapter;
    private ArrayAdapter<String> mPairedDevicesArrayAdapter;
    ProgressDialog progressDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_device_list);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        loadingsection= (LinearLayout) findViewById(R.id.loadingSection);

    }
    @Override
    public void onBackPressed() {
          //  moveTaskToBack(true);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog.Builder builder2=new AlertDialog.Builder(this);
        builder.setTitle("HomeAuto");
        builder.setMessage("Quitt Application?");
        builder2.setTitle("Bluetooth");
        builder2.setMessage("Disable Bluetooth also?");
        builder2.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mBtAdapter.disable();
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

                if(mBtAdapter.isEnabled())
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

                dialog.dismiss();


            }
        });

        AlertDialog alert = builder.create();
        alert2 = builder2.create();
        alert.show();
        //your code when back button pressed
    }



public void Btsettings(View view){
    Intent intentBluetooth = new Intent();
    intentBluetooth.setAction(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS);
    startActivity(intentBluetooth);
}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_CANCELED) {
            finish();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }


    }
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event)
//    {
//        if(keyCode == KeyEvent.KEYCODE_BACK)
//        {
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent.addCategory(Intent.CATEGORY_HOME);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//            return true;
//        }
//        return false;
//    }
    @Override
    public void onResume() {
        super.onResume();
        //***************

         checkBTState();
        mPairedDevicesArrayAdapter = new ArrayAdapter<String>(this, R.layout.device_name);
       // Log.d(TAG, "...Resume running...");
        // Find and set up the ListView for paired devices
        ListView pairedListView = (ListView) findViewById(R.id.paired_devices);
        pairedListView.setAdapter(mPairedDevicesArrayAdapter);
        pairedListView.setOnItemClickListener(mDeviceClickListener);

        // Get the local Bluetooth adapter
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();

        // Get a set of currently paired devices and append to 'pairedDevices'
        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
        // Add previosuly paired devices to the array
        if (pairedDevices.size() > 0) {
            findViewById(R.id.title_paired_devices).setVisibility(View.VISIBLE);//make title viewable
            for (BluetoothDevice device : pairedDevices) {
                mPairedDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
               // Log.d(TAG, "...Resume running if true...");
            }
        } else {
            String noDevices = getResources().getText(R.string.none_paired).toString();
            mPairedDevicesArrayAdapter.add(noDevices);
           // Log.d(TAG, "...Resume running if false...");
        }

    }


    // Set up on-click listener for the list (nicked this - unsure)
    private AdapterView.OnItemClickListener mDeviceClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {
            info = ((TextView) v).getText().toString();
            progressBar.setVisibility(View.VISIBLE);
            new backgroudJob().execute(v);

            //textView1.setText("Connecting...");
            // Get the device MAC address, which is the last 17 chars in the View


        }
    };
   private void    populate(BluetoothAdapter mBtadapter){
       mBtAdapter = mBtadapter;
    // Get a set of currently paired devices and append to 'pairedDevices'

}
    private void checkBTState() {
        // Check device has Bluetooth and that it is turned on
        mBtAdapter=BluetoothAdapter.getDefaultAdapter(); // CHECK THIS OUT THAT IT WORKS!!!
        if(mBtAdapter==null) {
            Toast.makeText(getBaseContext(), "Device does not support Bluetooth", Toast.LENGTH_SHORT).show();
        } else {
            if (mBtAdapter.isEnabled()) {
               // Log.d(TAG, "...Bluetooth ON...");
            } else {//Prompt user to turn on Bluetooth
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Application is requesting to enable Bluetooth")
                        .setPositiveButton("Enable", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                mBtAdapter.enable();
                                while (!mBtAdapter.isEnabled()){
                                   // Log.d(TAG, "...while working...");
                                }
                                DeviceListActivity.this.onResume();



                            }
                        })
                        .setNegativeButton("Exit", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                dialog.dismiss();
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);

                            }
                        });
                builder.create().show();
            }

                //Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
              //  startActivityForResult(enableBtIntent, 1);

            }
        }

    private class backgroudJob extends AsyncTask {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Connecting");
            progressDialog.show();

        }

        @Override
        protected Object doInBackground(Object[] params) {
                address = info.substring(info.length() - 17);


            // Make an intent to start next activity while taking an extra which is the MAC address.
            Intent i = new Intent(DeviceListActivity.this, MainActivity.class);
            i.putExtra(EXTRA_DEVICE_ADDRESS, address);
            startActivity(i);
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            progressDialog.dismiss();
        }
    }
}

