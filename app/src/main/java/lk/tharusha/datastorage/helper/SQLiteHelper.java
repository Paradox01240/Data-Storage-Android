package lk.tharusha.datastorage.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "student_db";
    private static final int DB_VERSION = 1;
    private static SQLiteHelper sqLiteHelper;

    public SQLiteHelper(@Nullable Context context) {
        super(context, SQLiteHelper.DATABASE_NAME, null, SQLiteHelper.DB_VERSION);
    }

    public static synchronized SQLiteHelper getInstance(Context context) {
        if (sqLiteHelper == null) {
            sqLiteHelper = new SQLiteHelper(context.getApplicationContext());
        }
        return sqLiteHelper;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String StudentTableQuery = "CREATE TABLE IF NOT EXISTS `student`\n" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "name TEXT,\n" +
                "age INTEGER)";
        db.execSQL(StudentTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropStudentTableQuery = "DROP TABLE IF EXISTS `student`";
        db.execSQL(dropStudentTableQuery);
        onCreate(db);
    }
}
