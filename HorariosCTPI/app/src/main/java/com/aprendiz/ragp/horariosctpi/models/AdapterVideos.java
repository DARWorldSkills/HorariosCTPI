package com.aprendiz.ragp.horariosctpi.models;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aprendiz.ragp.horariosctpi.R;
import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by PRREK on 16/10/2018.
 */

public class AdapterVideos extends RecyclerView.Adapter<AdapterVideos.Holder>{

    String[] listaVideos;
    Context context;



    private OnItemClickListener mlistener;

    public AdapterVideos(String[] listaVideos, Context context) {
        this.listaVideos = listaVideos;
        this.context = context;
    }

    public void setMlistener(OnItemClickListener mlistener) {
        this.mlistener = mlistener;
    }

    public interface OnItemClickListener{
        void itemClick(int position);
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video,parent,false);
        Holder holder = new Holder(view,mlistener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.connectData(listaVideos[position]);
    }


    @Override
    public int getItemCount() {
        return listaVideos.length;
    }


    public class Holder extends RecyclerView.ViewHolder {
        ImageView imageView = itemView.findViewById(R.id.imgVideo);
        public Holder(final View itemView, final OnItemClickListener mlistener) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mlistener!=null){
                        int position = getLayoutPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            mlistener.itemClick(position);
                        }
                    }
                }
            });
        }

        public void connectData(String url){
            try {
                Glide.with(context).load(url).crossFade().into(imageView);

            }catch (Exception e){

            }

        }
    }
}
