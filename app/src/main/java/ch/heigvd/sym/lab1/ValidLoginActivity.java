package ch.heigvd.sym.lab1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import ch.heigvd.sym.lab1.R;

public class ValidLoginActivity extends AppCompatActivity {

    private ImageView photo = null;
    private TextView mail = null;
    private TextView imei = null;
    private static final String LOG_TAG = "Exercice 8: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "OnCreate() called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valid_login);

        //Linking GUI components

        this.photo = (ImageView) this.findViewById(R.id.photo);
        this.mail = (TextView) this.findViewById(R.id.mail);
        this.imei = (TextView) this.findViewById(R.id.imei);


        //Photo
        File photoFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + File.separator + "perso.jpg");
        if(photoFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(photoFile.getAbsolutePath());

            photo.setImageBitmap(myBitmap);
        }

        //email
        this.mail.setText("email : " + getIntent().getStringExtra("emailEntered"));

        //EMEI
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        this.imei.setText("imei : " + manager.getDeviceId());

    }

    @Override
    public void onPause(){
        Log.d(LOG_TAG, "OnPause() called");
        super.onPause();
    }

    @Override
    public void onResume(){
        Log.d(LOG_TAG, "OnResume() called");
        super.onResume();
    }

    @Override
    public void onStop(){
        Log.d(LOG_TAG, "OnStop() called");
        super.onStop();
    }

    @Override
    public void onDestroy(){
        Log.d(LOG_TAG, "OnDestroy() called");
        super.onDestroy();
    }

    @Override
    public void onStart(){
        Log.d(LOG_TAG, "OnStart() called");
        super.onStart();
    }

    @Override
    public void onRestart(){
        Log.d(LOG_TAG, "OnRestart() called");
        super.onRestart();
    }

}