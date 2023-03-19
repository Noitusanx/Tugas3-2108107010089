package com.example.tugas3_pbm;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MahasiswaRVAdapter extends RecyclerView.Adapter<MahasiswaRVAdapter.ViewHolder> {

    // variable for our array list and context
    private final ArrayList<MahasiswaModal> mahasiswaModalArrayList;
    private final Context context;

    // constructor
    public MahasiswaRVAdapter(ArrayList<MahasiswaModal> mahasiswaModalArrayList, Context context) {
        this.mahasiswaModalArrayList = mahasiswaModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mahasiswa_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        MahasiswaModal modal = mahasiswaModalArrayList.get(position);
        holder.mahasiswaNamaTV.setText(modal.getNama());
        holder.mahasiswaNimTV.setText(modal.getNim());
        holder.mahasiswaJurusanTV.setText(modal.getJurusan());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ActivityUpdateMahasiwa.class);

                i.putExtra("nama", modal.getNama());
                i.putExtra("nim", modal.getNim());
                i.putExtra("jurusan", modal.getJurusan());

                context.startActivity(i);
            }
        });

        // below line is to add on click listener for our recycler view item.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on below line we are calling an intent.
                Intent i = new Intent(context, UpdateMahasiswaActivity.class);

                // below we are passing all our values.
                i.putExtra("name", modal.getNama());
                i.putExtra("nim", modal.getNim());
                i.putExtra("jurusan", modal.getJurusan());

                // starting our activity.
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return mahasiswaModalArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private final TextView mahasiswaNamaTV;
        private final TextView mahasiswaNimTV;
        private final TextView mahasiswaJurusanTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            mahasiswaNamaTV = itemView.findViewById(R.id.idTVMahasiswaNama);
            mahasiswaNimTV = itemView.findViewById(R.id.idTVMahasiswaNim);
            mahasiswaJurusanTV = itemView.findViewById(R.id.idTVMahasiswaJurusan);

        }
    }
}

