package com.aprendiz.ragp.horariosctpi.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aprendiz.ragp.horariosctpi.R;

import java.util.List;

public class AdapterHorarios extends RecyclerView.Adapter<AdapterHorarios.Holder> {
    int i=0;
    List<Horario> horarioList;
    Context context;
    int color;


    public AdapterHorarios(List<Horario> horarioList, Context context, int color) {
        this.horarioList = horarioList;
        this.context = context;
        this.color = color;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horario,parent,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.connectData(horarioList.get(position));

    }

    @Override
    public int getItemCount() {
        return horarioList.size();
    }
    public class Holder extends RecyclerView.ViewHolder {
        public Holder(View itemView) {
            super(itemView);
        }
        public void connectData(Horario horario){
            TextView txtLunesItem = itemView.findViewById(R.id.txtItemLunes);
            TextView txtMartesItem = itemView.findViewById(R.id.txtItemMartes);
            TextView txtMiercolesItem = itemView.findViewById(R.id.txtItemMiercoles);
            TextView txtJuevesItem = itemView.findViewById(R.id.txtItemJueves);
            TextView txtViernesItem = itemView.findViewById(R.id.txtItemViernes);

            txtLunesItem.setText(horario.getLunes());
            txtMartesItem.setText(horario.getMartes());
            txtMiercolesItem.setText(horario.getMiercoles());
            txtJuevesItem.setText(horario.getJueves());
            txtViernesItem.setText(horario.getViernes());
            txtLunesItem.setTextColor(color);
            txtMartesItem.setTextColor(color);
            txtMiercolesItem.setTextColor(color);
            txtJuevesItem.setTextColor(color);
            txtViernesItem.setTextColor(color);


        }
    }
}
