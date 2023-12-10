package pk.edu.comsats.www.homeauto30;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityC extends Activity {

    public SharedPreferences.Editor editor;
    public SharedPreferences sharedPreferences;
    ProgressDialog progressDialog;
    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;
    EditText editText6;
    EditText editText7;
    EditText editText8;
    EditText editText9;
    EditText editText10;
    EditText editText11;
    public static String EXTRA_DEVICE_ADDRESS = "device_address";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_c2);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,    R.layout.maintitlebar2);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText5 = (EditText) findViewById(R.id.editText5);
        editText6 = (EditText) findViewById(R.id.editText6);
        editText7 = (EditText) findViewById(R.id.editText7);
        editText8 = (EditText) findViewById(R.id.editText8);
        editText9 = (EditText) findViewById(R.id.editText9);
        editText10 = (EditText) findViewById(R.id.editText10);
        editText11 = (EditText) findViewById(R.id.editText11);
    }


    public void Save(View v)  {
        //progressDialog.show();
        if(v.getId()==R.id.savebtn)
            Log.d("bluetooth2","save text working");
        sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if (!editText1.getText().toString().equals(""))
            editor.putString("Text0", editText1.getText().toString());
        if (!editText2.getText().toString().equals(""))
            editor.putString("Text1", editText2.getText().toString());
        if (!editText3.getText().toString().equals(""))
            editor.putString("Text2", editText3.getText().toString());
        if (!editText4.getText().toString().equals(""))
            editor.putString("Text3", editText4.getText().toString());
        if (!editText5.getText().toString().equals(""))
            editor.putString("Text4", editText5.getText().toString());
        if (!editText6.getText().toString().equals(""))
            editor.putString("Text5", editText6.getText().toString());
        if (!editText7.getText().toString().equals(""))
            editor.putString("Text6", editText7.getText().toString());
        if (!editText8.getText().toString().equals(""))
            editor.putString("Text7", editText8.getText().toString());
        if (!editText9.getText().toString().equals(""))
            editor.putString("Text8", editText9.getText().toString());
        if (!editText10.getText().toString().equals(""))
            editor.putString("Text9", editText10.getText().toString());
        if (!editText11.getText().toString().equals(""))
            editor.putString("Text10", editText11.getText().toString());
        editor.apply();
       // progressDialog.dismiss();
            finish();
        Toast.makeText(ActivityC.this, "Changes Saved", Toast.LENGTH_LONG).show();



//            Intent intent=new Intent(ActivityC.this,MainActivity.class);
//            startActivity(intent);







    }


}








