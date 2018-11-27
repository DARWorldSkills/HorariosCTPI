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
    public static boolean bandera = true;
    List<Horario> horarioList;
    Context context;
    int color;
    private OnItemCLickListener onItemCLickListener1;
    private OnItemCLickListener onItemCLickListener2;
    private OnItemCLickListener onItemCLickListener3;
    private OnItemCLickListener onItemCLickListener4;
    private OnItemCLickListener onItemCLickListener5;
    private OnItemCLickListener onItemCLickListener6;
    public interface OnItemCLickListener{
        void itemClick(String txtNombre, int position);
    }


    public AdapterHorarios(List<Horario> horarioList, Context context, int color) {
        this.horarioList = horarioList;
        this.context = context;
        this.color = color;
    }

    public void setOnItemCLickListener1(OnItemCLickListener onItemCLickListener1) {
        this.onItemCLickListener1 = onItemCLickListener1;
    }

    public void setOnItemCLickListener2(OnItemCLickListener onItemCLickListener2) {
        this.onItemCLickListener2 = onItemCLickListener2;
    }

    public void setOnItemCLickListener3(OnItemCLickListener onItemCLickListener3) {
        this.onItemCLickListener3 = onItemCLickListener3;
    }

    public void setOnItemCLickListener4(OnItemCLickListener onItemCLickListener4) {
        this.onItemCLickListener4 = onItemCLickListener4;
    }

    public void setOnItemCLickListener5(OnItemCLickListener onItemCLickListener5) {
        this.onItemCLickListener5 = onItemCLickListener5;
    }

    public void setOnItemCLickListener6(OnItemCLickListener onItemCLickListener6) {
        this.onItemCLickListener6 = onItemCLickListener6;
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
            final TextView txtLunesItem = itemView.findViewById(R.id.txtItemLunes);
            final TextView txtMartesItem = itemView.findViewById(R.id.txtItemMartes);
            final TextView txtMiercolesItem = itemView.findViewById(R.id.txtItemMiercoles);
            final TextView txtJuevesItem = itemView.findViewById(R.id.txtItemJueves);
            final TextView txtViernesItem = itemView.findViewById(R.id.txtItemViernes);

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

            txtLunesItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (bandera) {
                        if (onItemCLickListener1 != null) {
                            int postion = getAdapterPosition();
                            if (postion != RecyclerView.NO_POSITION) {
                                onItemCLickListener1.itemClick(txtLunesItem.getText().toString(), postion);
                            }
                        }
                        bandera=false;
                    }
                }
            });

            txtMartesItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (bandera) {
                        if (onItemCLickListener1 != null) {
                            int postion = getAdapterPosition();
                            if (postion != RecyclerView.NO_POSITION) {
                                onItemCLickListener1.itemClick(txtMartesItem.getText().toString(), postion);
                            }
                        }
                        bandera=false;
                    }
                }
            });


            txtMiercolesItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (bandera) {
                        if (onItemCLickListener1 != null) {
                            int postion = getAdapterPosition();
                            if (postion != RecyclerView.NO_POSITION) {
                                onItemCLickListener1.itemClick(txtMiercolesItem.getText().toString(), postion);
                            }
                        }
                        bandera=false;
                    }
                }
            });


            txtJuevesItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (bandera) {
                        if (onItemCLickListener1 != null) {
                            int postion = getAdapterPosition();
                            if (postion != RecyclerView.NO_POSITION) {
                                onItemCLickListener1.itemClick(txtJuevesItem.getText().toString(), postion);
                            }
                        }
                        bandera=false;
                    }
                }
            });

            txtViernesItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (bandera) {
                        if (onItemCLickListener1 != null) {
                            int postion = getAdapterPosition();
                            if (postion != RecyclerView.NO_POSITION) {
                                onItemCLickListener1.itemClick(txtViernesItem.getText().toString(), postion);
                            }
                        }
                        bandera=false;
                    }
                }
            });




        }
    }
}
