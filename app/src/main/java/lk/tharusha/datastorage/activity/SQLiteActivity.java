package lk.tharusha.datastorage.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import lk.tharusha.datastorage.R;
import lk.tharusha.datastorage.helper.SQLiteHelper;

public class SQLiteActivity extends AppCompatActivity {
private Button insertBtn,updateBtn,deleteBtn,searchBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        this.insertBtn = findViewById(R.id.button3);
        this.updateBtn = findViewById(R.id.button4);
        this.deleteBtn = findViewById(R.id.button5);
        this.searchBtn = findViewById(R.id.button6);

insertBtn.setOnClickListener(v -> insertData());
updateBtn.setOnClickListener(v -> updateData());
deleteBtn.setOnClickListener(v -> deleteData());
searchBtn.setOnClickListener(v -> searchData());
    }
    private void insertData(){
        SQLiteHelper sqLiteHelper = SQLiteHelper.getInstance(this);
        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name","Anjana");
        values.put("age",22);
        db.insert("student",null,values);


    }
    private void updateData(){
        SQLiteHelper sqLiteHelper = SQLiteHelper.getInstance(this);
        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("age",25);
        db.update("student",values,"id = ?",new String[]{String.valueOf(1)});
    }
    private void deleteData(){
        SQLiteHelper sqLiteHelper = SQLiteHelper.getInstance(this);
        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
        db.delete("student","id = ?",new String[]{String.valueOf(1)});
    }
    private void searchData(){
        SQLiteHelper sqLiteHelper = SQLiteHelper.getInstance(this);
        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
       android.database.Cursor cursor = db.rawQuery("SELECT * FROM student",null);
       if (cursor.moveToFirst() && cursor.getCount() > 0){
           do{
               String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
               Log.d(SQLiteActivity.class.getSimpleName(),"Student Name : "+ name);

           }while (cursor.moveToNext());
       }
       cursor.close();

    }
}