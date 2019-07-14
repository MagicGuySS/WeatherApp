package sg.edu.np.s10189707.WeatherApp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    private static final String TAG = "MainActivity.java";
    private TextView tv_NewUser;

    SharedPreferences sharedPreferences;

    public static final String MY_GLOBAL_PREFS ="MyPrefs";
    public static final String MY_USERNAME = "MyUserName";
    public static final String MY_PASSWORD = "MyPassword";

    myDBHandler dbHandler = new myDBHandler(this,null,null,1);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_NewUser = (TextView) findViewById(R.id.tv_NewUser);
        tv_NewUser.setOnTouchListener(this);
    }

    public boolean onTouch(View v, MotionEvent event){

        Log.v(TAG,"");
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP); //start only 1 activity
        startActivity(intent);
        return true;
    }

    public void onClick(View v){
        final EditText etPassword = (EditText) findViewById(R.id.editText_Password);
        final EditText etName = (EditText) findViewById(R.id.editText_name);

/*      if(isValidUserName(etName.getText().toString()) && isValidPassword(etPassword.getText().toString())){*/
        if(isValidUser(etName.getText().toString(), etPassword.getText().toString())){
            Intent intent = new Intent(MainActivity.this ,MainActivity3.class);
            Toast.makeText(MainActivity.this , "Valid User",Toast.LENGTH_LONG).show();
            startActivity(intent);


        }
        else{
            Toast.makeText(MainActivity.this , "Invalid User",Toast.LENGTH_LONG).show();
        }

    }

    public boolean isValidUser(String username, String password){
       // sharedPreferences = getSharedPreferences(MY_USERNAME,MODE_PRIVATE);
        UserData dbData = dbHandler.findUser(username);
       // Log.v(TAG,"LOADING STUFF" + dbData.getMyUserName() +"-----" +dbData.getMyPassword()
       // + "VB: "+username + password) ;

        if((dbData.getMyUserName().equals(username)) && dbData.getMyPassword().equals(password))
        {
            return true;
        }
        return false;
    }




   public boolean isValidUserName(String username){

        sharedPreferences =getSharedPreferences(MY_USERNAME,MODE_PRIVATE);
        String sharedUsername = sharedPreferences.getString(MY_USERNAME,"");

        if(sharedUsername.equals(username)){
            return  true;
        }


        return false;
   }

    public boolean isValidPassword(String password){

        sharedPreferences =getSharedPreferences(MY_PASSWORD,MODE_PRIVATE);
        String sharedPassword = sharedPreferences.getString(MY_PASSWORD,"");

        if(sharedPassword.equals(password)){
            return  true;
        }
        else{
            return false;
        }

    }
}
