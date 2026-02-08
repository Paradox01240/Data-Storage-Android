package lk.tharusha.datastorage.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import lk.tharusha.datastorage.R;

public class ExternalStorageActivity extends AppCompatActivity {
    private Button exWriteBtn, exReadBtn, pickerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);
        this.exWriteBtn = findViewById(R.id.button);
        this.exReadBtn = findViewById(R.id.button2);
        this.pickerBtn = findViewById(R.id.btn3);

        exWriteBtn.setOnClickListener(v -> {
            writeFile();
            writeFileInDownload();
        });
        exReadBtn.setOnClickListener(v -> readFile());
        pickerBtn.setOnClickListener(v -> {
            openFilePicker();
        });
    }

    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 101);
    }


    private static final String FILE_NAME = "my_file.txt";

    private void writeFile() {
        try {
            File dir = getExternalFilesDir(null);
            File file = new File(dir, FILE_NAME);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            String txt = "Hello World";
            fileOutputStream.write(txt.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFile() {
        try {
            File dir = getExternalFilesDir(null);
            File file = new File(dir, FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            bufferedReader.close();
            Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    private void writeFileInDownload() {
        try {
            ContentValues values = new ContentValues();
            values.put(MediaStore.MediaColumns.DISPLAY_NAME, FILE_NAME);
            values.put(MediaStore.MediaColumns.MIME_TYPE, "text/plain");

            Uri uri = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                uri = getContentResolver().insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, values);
            }
            OutputStream outputStream = getContentResolver().openOutputStream(uri);
            String txt = "Hello World";
            outputStream.write(txt.getBytes());
            outputStream.flush();
            outputStream.close();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line);

                    }
                    bufferedReader.close();
                    Toast.makeText(this,sb.toString(),Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}