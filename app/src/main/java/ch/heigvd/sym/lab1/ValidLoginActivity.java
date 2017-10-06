package ch.heigvd.sym.lab1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        //Photo
        File photoFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + File.separator + "perso.jpg");
        if(photoFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(photoFile.getAbsolutePath());

            photo.setImageBitmap(myBitmap);

        }

        //email


    }


}
