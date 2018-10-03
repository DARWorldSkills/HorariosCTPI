package com.aprendiz.ragp.horariosctpi.models;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aprendiz.ragp.horariosctpi.R;

import java.util.List;

public class AdapterHorarios extends RecyclerView.Adapter<AdapterHorarios.Holder> {
    List<Horario> horarioList;

    public AdapterHorarios(List<Horario> horarioList) {
        this.horarioList = horarioList;
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
        int i=0;
        public Holder(View itemView) {
            super(itemView);
        }
        public void connectData(Horario horario){
            TextView txtLunesItem = itemView.findViewById(R.id.txtItemLunes);
            TextView txtMartesItem = itemView.findViewById(R.id.txtItemMiercoles);
            TextView txtMiercolesItem = itemView.findViewById(R.id.txtItemMiercoles);
            TextView txtJuevesItem = itemView.findViewById(R.id.txtItemJueves);
            TextView txtViernesItem = itemView.findViewById(R.id.txtItemViernes);
            TextView txtSabadoItem = itemView.findViewById(R.id.txtItemSabado);

            txtLunesItem.setText(horario.getLunes());
            txtMartesItem.setText(horario.getMartes());
            txtMiercolesItem.setText(horario.getMiercoles());
            txtJuevesItem.setText(horario.getJueves());
            txtViernesItem.setText(horario.getViernes());
            txtSabadoItem.setText(horario.getSabado());


        }
    }
}
