package lk.tharusha.datastorage.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import lk.tharusha.datastorage.R;

public class InternalStorageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);

        Button storeBtn = findViewById(R.id.storeBtn);
        Button retriveBtn = findViewById(R.id.retriveBtn);

storeBtn.setOnClickListener(v -> storeData());
retriveBtn.setOnClickListener(v -> retriveData());
    }

    private void storeData(){
        //File file = new File(getFilesDir(),"my_file.txt");
try {
    FileOutputStream fileOutputStream = openFileOutput("AppData.txt",MODE_PRIVATE);
    String txt = "Hello World";
    fileOutputStream.write(txt.getBytes());
    fileOutputStream.flush();
    fileOutputStream.close();

} catch (Exception e) {
    throw new RuntimeException(e);
}
    }
    private void retriveData() {
        File file = new File(getFilesDir(), "my_file.txt");
        try {
            FileInputStream fileInputStream = openFileInput("AppData.txt");
           BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
    StringBuilder sb = new StringBuilder();
    String line;
while ((line = bufferedReader.readLine()) != null){
    sb.append(line);
}
            System.out.println(sb);
            Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}