package com.example.tugas3_pbm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ViewMahasiswa extends AppCompatActivity {

    private ArrayList<MahasiswaModal> mahasiswaModalArrayList;
    private DBHelper dbHelper;
    private MahasiswaRVAdapter mahasiswaRVAdapter;
    private RecyclerView mahasiswaRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmahasiswa);

        mahasiswaModalArrayList = new ArrayList<>();
        dbHelper = new DBHelper(ViewMahasiswa.this);

        mahasiswaModalArrayList = dbHelper.bacaSemuadata();

        mahasiswaRVAdapter = new MahasiswaRVAdapter(mahasiswaModalArrayList, ViewMahasiswa.this);
        mahasiswaRV = findViewById(R.id.idRVMahasiswa);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewMahasiswa.this, RecyclerView.VERTICAL, false);
        mahasiswaRV.setLayoutManager(linearLayoutManager);

        mahasiswaRV.setAdapter(mahasiswaRVAdapter);
    }

    public void clickHandler(View view) {
        Intent intent = new Intent(ViewMahasiswa.this, MainActivity.class);
        startActivity(intent);
    }
}