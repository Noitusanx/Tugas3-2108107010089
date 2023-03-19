package com.example.tugas3_pbm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityUpdateMahasiwa extends AppCompatActivity {

    private EditText namaEdit, nimEdit, jurusanEdit;
    private Button updateMahasiswa, deleteMahasiswa;
    private DBHelper dbHelper = new DBHelper(ActivityUpdateMahasiwa.this);
    String namaMahasiswa, nimMahasiswa, jurusanMahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_mahasiswa);

        namaEdit = findViewById(R.id.editNama);
        nimEdit = findViewById(R.id.editNim);
        jurusanEdit = findViewById(R.id.editJurusan);
//        updateMahasiswa = findViewById(R.id.update);
        deleteMahasiswa = findViewById(R.id.delete);

        namaMahasiswa = getIntent().getStringExtra("nama");
        nimMahasiswa = getIntent().getStringExtra("npm");
        jurusanMahasiswa = getIntent().getStringExtra("jurusan");

        namaEdit.setText(namaMahasiswa);
        nimEdit.setText(nimMahasiswa);
        jurusanEdit.setText(jurusanMahasiswa);

        updateMahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.updateMahasiswa(namaMahasiswa, namaEdit.getText().toString(), nimEdit.getText().toString(), jurusanEdit.getText().toString());
                Toast.makeText(ActivityUpdateMahasiwa.this, "Mahasiswa Berhasil Diupdate!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ActivityUpdateMahasiwa.this, MainActivity.class);
                startActivity(i);
            }
        });

        deleteMahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.deleteMahasiswa(namaMahasiswa);
                Toast.makeText(ActivityUpdateMahasiwa.this, "Mahasiswa telah terhapus", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ActivityUpdateMahasiwa.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}