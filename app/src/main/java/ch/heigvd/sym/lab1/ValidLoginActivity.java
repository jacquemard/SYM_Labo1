package ch.heigvd.sym.lab1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import ch.heigvd.sym.lab1.R;

public class ValidLoginActivity extends AppCompatActivity {

    private ImageView photo = null;
    private TextView mail = null;
    private TextView imei = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        //IMEI
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        this.imei.setText("imei : " + manager.getDeviceId());

        // We will return the imei value to the first activity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("imei", manager.getDeviceId());
        this.setResult(Activity.RESULT_OK,resultIntent);
    }



}