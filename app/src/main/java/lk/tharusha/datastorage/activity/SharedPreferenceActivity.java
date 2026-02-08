package lk.tharusha.datastorage.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import lk.tharusha.datastorage.R;

public class SharedPreferenceActivity extends AppCompatActivity {
    private static String APP_DATA = "my_app_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);
        EditText emailInput = findViewById(R.id.emaiIInput);
        EditText passwordInput = findViewById(R.id.passwordinput);
        Button submitBtn = findViewById(R.id.submitBtn);

checkIsLoggedIn();
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();
                if(email.equals("anjana@gmail.com") && password.equals("1234")){
                    Toast.makeText(SharedPreferenceActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = getSharedPreferences(APP_DATA,MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isLoggedIn",true);
                    editor.apply(); //Immediately
                    //editor.commit(); //asynchronously

                }else{
                    Toast.makeText(SharedPreferenceActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
    private void checkIsLoggedIn(){
        SharedPreferences sharedPreferences = getSharedPreferences(APP_DATA,MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn",false);
if (isLoggedIn){
    Toast.makeText(this, "Already Logged In", Toast.LENGTH_SHORT).show();
}else{
    Toast.makeText(this, "Not Logged In", Toast.LENGTH_SHORT).show();
}
    }
}