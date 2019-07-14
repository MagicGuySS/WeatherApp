package sg.edu.np.s10189707.WeatherApp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main2Activity extends AppCompatActivity {


    SharedPreferences sharedPreferences;
    public static final String MY_GLOBAL_PREFS = "MyPrefs";
    public static final String MY_USERNAME = "MyUserName";
    public static final String MY_PASSWORD = "MyPassword";

    myDBHandler dbHandler = new myDBHandler(this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void onCreateClick(View v){

        final EditText etPassword = (EditText) findViewById(R.id.editText_PasswordCreate);
        final EditText etName = (EditText) findViewById(R.id.editText_UsernameCreate);

        if(isValidUsername(etName.getText().toString()) && isValidPass(etPassword.getText().toString())){

            /*sharedPreferences = getSharedPreferences(MY_GLOBAL_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(MY_USERNAME,etName.getText().toString());
            editor.putString(MY_PASSWORD,etPassword.getText().toString());*/

            UserData dbData = dbHandler.findUser(etName.getText().toString());
            if(dbData == null){
                String dbUserName = etName.getText().toString();
                String dbPassword = etPassword.getText().toString();

                UserData dbUserData = new UserData();
                dbUserData.setMyUserName(dbUserName);
                dbUserData.setMyPassword(dbPassword);
                dbHandler.addUser(dbUserData);

                Toast.makeText(Main2Activity.this, "User Successfully created", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(Main2Activity.this, "User not created", Toast.LENGTH_LONG).show();
            }


            finish();

        }
        else {
            Toast.makeText(Main2Activity.this, "Invalid User Created",Toast.LENGTH_LONG).show();
        }


    }

    public boolean isValidPass(String password){

        Pattern passwordPattern;
        Matcher passwordMatcher;


        //                              "^(?=.*[0-9])(?=.*[a-zA-Z]*).(?=.*[!@#$%]).[6,12]$";
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@$%]).{6,12}$";

        passwordPattern = Pattern.compile(PASSWORD_PATTERN);
        passwordMatcher = passwordPattern.matcher(password);


        return passwordMatcher.matches();
    }

    public boolean isValidUsername(String username){

        Pattern namePattern;
        Matcher nameMatcher;

        //final String NAME_PATTERN = "^(?=.*[0-9])(?=.*[a-zA-Z]*).{6,12}$";
          final String NAME_PATTERN = "^(?=.*[0-9])(?=.*[a-zA-Z]).{6,12}$";

        namePattern = Pattern.compile(NAME_PATTERN);
        nameMatcher = namePattern.matcher(username);


        return nameMatcher.matches();

    }


}
