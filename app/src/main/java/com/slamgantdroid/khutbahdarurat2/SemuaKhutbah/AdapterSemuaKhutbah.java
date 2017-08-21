package com.slamgantdroid.khutbahdarurat2.SemuaKhutbah;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.slamgantdroid.khutbahdarurat2.Database.GSONsemuakhutbah;
import com.slamgantdroid.khutbahdarurat2.DetailKhutbah.DetailKhutbah;
import com.slamgantdroid.khutbahdarurat2.R;

import java.util.List;

/**
 * Created by WIN10 on 02/07/2017.
 */

public class AdapterSemuaKhutbah extends RecyclerView.Adapter<AdapterSemuaKhutbah.ViewHolder> {

    Context context;
    public List<GSONsemuakhutbah.SemuaKhutbahList>semuaKhutbahLists;

    public AdapterSemuaKhutbah(Context context, List<GSONsemuakhutbah.SemuaKhutbahList> semuaKhutbahLists) {
        this.context = context;
        this.semuaKhutbahLists = semuaKhutbahLists;
    }

    @Override
    public AdapterSemuaKhutbah.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterSemuaKhutbah.ViewHolder holder, final int position) {

        ColorGenerator generator = ColorGenerator.MATERIAL;
        int colorRandom = generator.getRandomColor();

        TextDrawable.IBuilder builder = TextDrawable.builder()
                .round();

        TextDrawable drawable = builder.build(semuaKhutbahLists.get(position).huruf_depan_nama_ustad, colorRandom);

        holder.tvGambar_Ustad.setImageDrawable(drawable);

//        Glide.with(context)
//                .load(semuaKhutbahLists.get(position).url_gambar_ustad)
//                .crossFade()
//                .placeholder(R.mipmap.ic_launcher_round)
//                .into(holder.tvGambar_Ustad);

        holder.tvJudul.setText(semuaKhutbahLists.get(position).judul_khutbah);
        holder.tvNama_Ustad.setText(semuaKhutbahLists.get(position).nama_ustad);
        holder.tv_tema.setText(semuaKhutbahLists.get(position).nama_tema_khutbah);

        holder.tv_id.setText(semuaKhutbahLists.get(position).id_isi_khutbah);

        final String id_khutbah = semuaKhutbahLists.get(position).id_isi_khutbah;

        holder.card_item_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendToDetail = new Intent(view.getContext(), DetailKhutbah.class);
                sendToDetail.putExtra(semuaKhutbahLists.get(position).id_isi_khutbah, id_khutbah);
                view.getContext().startActivity(sendToDetail);
            }
        });

    }

    @Override
    public int getItemCount() {
        return semuaKhutbahLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView tvGambar_Ustad;
        TextView tvJudul;
        TextView tvNama_Ustad;
        TextView tv_tema;
        TextView tv_id;
        CardView card_item_list;

        public ViewHolder(View itemView) {
            super(itemView);

            tvGambar_Ustad = (ImageView) itemView.findViewById(R.id.gambar_list);
            tvJudul = (TextView)itemView.findViewById(R.id.tv_judul);
            tvNama_Ustad = (TextView)itemView.findViewById(R.id.tv_nama_ustad);
            tv_tema = (TextView)itemView.findViewById(R.id.tv_tema);
            tv_id = (TextView)itemView.findViewById(R.id.tv_id_khutbah);
            card_item_list = (CardView)itemView.findViewById(R.id.card_item_list);


        }
    }
}
