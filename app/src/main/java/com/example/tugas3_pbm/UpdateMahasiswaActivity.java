package com.example.tugas3_pbm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateMahasiswaActivity extends AppCompatActivity {

    // variables for our edit text, button, strings and dbhandler class.
    private EditText namaMahasiswaEdt, nimMahasiswaEdt, jurusanMahasiswaEdt;
    private Button updateCourseBtn;
    private DBHelper dbHelper;
    String namaMahasiswa, nimMahasiswa, jurusanMahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_mahasiswa2);

        namaMahasiswaEdt = findViewById(R.id.idEdtNamaMahasiswa);
        nimMahasiswaEdt = findViewById(R.id.idEdtNimMahasiswa);
        jurusanMahasiswaEdt = findViewById(R.id.idEdtJurusanMahasiswa);
        updateCourseBtn = findViewById(R.id.idBtnUpdateMahasiswa);

        dbHelper = new DBHelper(UpdateMahasiswaActivity.this);

        namaMahasiswa = getIntent().getStringExtra("nama");
        nimMahasiswa = getIntent().getStringExtra("nim");
        jurusanMahasiswa = getIntent().getStringExtra("jurusan");

        namaMahasiswaEdt.setText(namaMahasiswa);
        nimMahasiswaEdt.setText(nimMahasiswa);
        jurusanMahasiswaEdt.setText(jurusanMahasiswa);

        // adding on click listener to our update course button.
        updateCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // inside this method we are calling an update course
                // method and passing all our edit text values.
                dbHelper.updateMahasiswa(namaMahasiswa, namaMahasiswaEdt.getText().toString(), nimMahasiswaEdt.getText().toString(), jurusanMahasiswaEdt.getText().toString());

                // displaying a toast message that our course has been updated.
                Toast.makeText(UpdateMahasiswaActivity.this, "Mahasiswa Berhasil Diupdate!", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent i = new Intent(UpdateMahasiswaActivity.this, MainActivity.class);
                startActivity(i);
            }
        });


    }
}
