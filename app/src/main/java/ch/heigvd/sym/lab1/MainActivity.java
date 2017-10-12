/**
 * File     : MainActivity.java
 * Project  : SYM - Labo 1
 * Date     : 12.10.2017
 * Author   :
 * 		 Rémi Jacquemard
 *       Edward Ransome
 *       Michael Spierer
 * 
 */

package ch.heigvd.sym.lab1;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // For logging purposes
    private static final String TAG = MainActivity.class.getSimpleName();


	private static final ArrayList<Pair<String,String>> users = new ArrayList<Pair<String,String>>() {{
		add(new Pair("a@b.com","c"));
		add(new Pair("toto@tutu.com","tata"));
		add(new Pair("aaa@bbb.com","ccc"));
	}};

	private static final int IMEI_REQUEST = 1;

    // GUI elements
	private EditText email      = null;
	private EditText password   = null;
    private Button   signIn     = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		// Show the welcome screen / login authentication dialog
		setContentView(R.layout.authent);

		// Link to GUI elements
		this.email      = (EditText) findViewById(R.id.email);
		this.password      = (EditText) findViewById(R.id.password);
        this.signIn     = (Button)   findViewById(R.id.buttOk);

		// Then program action associated to "Ok" button
		signIn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				/*
				 * There you have to check out if the email/password
				 * combination given is valid or not
				 */
				String mail = email.getText().toString();
				String passwd = password.getText().toString();

				if (!mail.contains("@")){
					Toast.makeText(MainActivity.this, getResources().getString(R.string.wrongEmail), Toast.LENGTH_LONG).show();
				}
				else if (isValid(mail, passwd)) {
					/* Ok, valid combination, do something or launch another activity...
					 * The current activity could be finished, but it is not mandatory.
					 * To launch activity MyActivity.class, try something like :
					 * 
					 * 			Intent intent = new Intent(this, ch.heigvd.sym.MyActivity.class);
					 * 			intent.putExtra("emailEntered", mail);
					 *			intent.putExtra("passwordGiven", passwd);
					 *			this.startActivity(intent); 
					 *
					 * Alternately, you could also startActivityForResult if you are awaiting a result.
					 * In the latter case, you have to indicate an int parameter to identify MyActivity
					 * 
					 * If you haven't anything more to do, you may finish()...
					 * But just display a small message before quitting...
					 */

					Intent intent = new Intent(MainActivity.this, ch.heigvd.sym.lab1.ValidLoginActivity.class);
					intent.putExtra("emailEntered", mail);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					//intent.putExtra("passwordGiven", passwd);
					//MainActivity.this.startActivity(intent);

					// If we want to get a result from the activity :
					MainActivity.this.startActivityForResult(intent, IMEI_REQUEST);

					Toast.makeText(MainActivity.this, getResources().getString(R.string.good), Toast.LENGTH_LONG).show();

					//finish();
				} else {
					// Wrong combination, display pop-up dialog and stay on login screen
					showErrorDialog(mail, passwd);
				}
			}
			
		});
	}
	
	private boolean isValid(String mail, String passwd) {
        if(mail == null || passwd == null) {
            Log.w(TAG, "isValid(mail, passwd) - mail and passwd cannot be null !");
            return false;
        }
		// Return true if combination valid, false otherwise
		return users.contains(new Pair(mail,passwd));
	}
	
	protected void showErrorDialog(String mail, final String passwd) {
		/*
		 * Pop-up dialog to show error
		 */
		AlertDialog.Builder alertbd = new AlertDialog.Builder(this);
        alertbd.setIcon(R.drawable.ic_error_black_24dp);
		alertbd.setTitle(R.string.wronglogin);
	    alertbd.setMessage(R.string.wrong);
	    alertbd.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) {
				email.setText("");
				password.setText("");
                // dialog close automatically
	        }
	     });
	    alertbd.create().show();
	}

	// For test purpose
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == IMEI_REQUEST) {
			if(resultCode == Activity.RESULT_OK){
				String result=data.getStringExtra("imei");

				Toast.makeText(MainActivity.this, "imei : " + result, Toast.LENGTH_LONG).show();
			}
		}

	}
}