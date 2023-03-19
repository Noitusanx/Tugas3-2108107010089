package com.example.tugas3_pbm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tugas3_pbm.MahasiswaModal;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "prak4";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "mahasiswa";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    private static final String NAME_COL = "nama";

    // below variable id for our course duration column.
    private static final String NPM_COL = "nim";

    // below variable for our course description column.
    private static final String JURUSAN_COL = "jurusan";

    // creating a constructor for our database handler.
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + NPM_COL + " TEXT,"
                + JURUSAN_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void tambahMahasiswa(String nama, String nim, String jurusan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME_COL, nama);
        values.put(NPM_COL, nim);
        values.put(JURUSAN_COL, jurusan);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<MahasiswaModal> bacaSemuadata(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorMahasiswa = db.rawQuery("SELECT * FROM "+ TABLE_NAME, null);

        ArrayList<MahasiswaModal> mahasiswaModalArrayList = new ArrayList<>();

        if(cursorMahasiswa.moveToFirst()){
            do {
                mahasiswaModalArrayList.add(new MahasiswaModal(
                        cursorMahasiswa.getString(1),
                        cursorMahasiswa.getString(2),
                        cursorMahasiswa.getString(3)
                ));
            }while (cursorMahasiswa.moveToNext());
        }

        cursorMahasiswa.close();
        return  mahasiswaModalArrayList;
    }

    public void deleteMahasiswa(String namaMahasiswa) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, "nama=?", new String[]{namaMahasiswa});
        db.close();
    }

    // below is the method for updating our courses
    public void updateMahasiswa(String originalNamaMahasiswa, String namaMahasiswa, String nimMahasiswa,
                                String jurusanMahasiswa) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, namaMahasiswa);
        values.put(NPM_COL, nimMahasiswa);
        values.put(JURUSAN_COL, jurusanMahasiswa);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update(TABLE_NAME, values, "name=?", new String[]{originalNamaMahasiswa});
        db.close();
    }


}
