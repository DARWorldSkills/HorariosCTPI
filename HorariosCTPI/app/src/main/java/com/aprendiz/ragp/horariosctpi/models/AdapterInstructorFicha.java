package com.aprendiz.ragp.horariosctpi.models;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aprendiz.ragp.horariosctpi.R;

import java.util.List;

public class AdapterInstructorFicha extends RecyclerView.Adapter<AdapterInstructorFicha.Holder> {
    String[] list;


    public AdapterInstructorFicha(String[] list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horario_instructor,parent,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.connectData(list[position]);
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(View itemView) {
            super(itemView);
        }

        public void connectData(String ficha){
            TextView txtFicha = itemView.findViewById(R.id.txtFicha);
            txtFicha.setText(ficha);
        }

    }
}
