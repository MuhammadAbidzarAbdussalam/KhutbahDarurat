package com.slamgantdroid.khutbahdarurat2.DetailKhutbah;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.slamgantdroid.khutbahdarurat2.Database.GSONdetailkhutbah;
import com.slamgantdroid.khutbahdarurat2.R;

public class DetailKhutbah extends AppCompatActivity {

    TextView tv_judul, tv_tema, tv_ustad, tv_isi_khutbah, tv_tanggal_kajian;
    ImageView tv_huruf_depan_nama_ustad, gambar_kajian;
    GSONdetailkhutbah gsoNdetailkhutbah;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_khutbah);

        tv_judul = (TextView)findViewById(R.id.tv_judul_detail);
        tv_tema = (TextView)findViewById(R.id.tv_tema_khutbah_detail);
        tv_ustad = (TextView)findViewById(R.id.tv_nama_ustad_detail);
        tv_isi_khutbah = (TextView)findViewById(R.id.tv_detail_khutbah);
        tv_tanggal_kajian = (TextView)findViewById(R.id.tv_tanggal_detail);

        tv_huruf_depan_nama_ustad = (ImageView)findViewById(R.id.img_nama_ustad_detail);
        gambar_kajian = (ImageView)findViewById(R.id.gambar_khutbah);

        Intent getIntentDetail = getIntent();
        String id_isi_khutbah = getIntentDetail.getStringExtra("id_isi_khutbah");

        RequestDetailKhutbahfromServer("http://10.10.10.7/khutbah_darurat/detailKhutbah.php?send_id_articel=" + id_isi_khutbah);



    }

    private void RequestDetailKhutbahfromServer(String urlRequest) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlRequest, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();

                    gsoNdetailkhutbah = gson.fromJson(response, GSONdetailkhutbah.class);
                    setDetailContent(gsoNdetailkhutbah, position);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                View v = null;
                Snackbar.make(v, "Koneksi internet terputus !!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        requestQueue.add(stringRequest);
    }

    private void setDetailContent(GSONdetailkhutbah gsoNdetailkhutbah, int position) {
        tv_judul.setText(gsoNdetailkhutbah.detailSemuaKhutbahLists.get(position).judul_khutbah);
        tv_ustad.setText(gsoNdetailkhutbah.detailSemuaKhutbahLists.get(position).nama_ustad);
        tv_tema.setText(gsoNdetailkhutbah.detailSemuaKhutbahLists.get(position).nama_tema_khutbah);
        tv_tanggal_kajian.setText(gsoNdetailkhutbah.detailSemuaKhutbahLists.get(position).judul_khutbah);
        tv_isi_khutbah.setText(gsoNdetailkhutbah.detailSemuaKhutbahLists.get(position).isi_khutbah);

        ColorGenerator generator = ColorGenerator.MATERIAL;
        int colorRandom = generator.getRandomColor();

        TextDrawable.IBuilder builder = TextDrawable.builder()
                .round();

        TextDrawable drawable = builder.build(gsoNdetailkhutbah.detailSemuaKhutbahLists.get(position).huruf_depan_nama_ustad, colorRandom);

        tv_huruf_depan_nama_ustad.setImageDrawable(drawable);

        Glide.with(this)
                .load(gsoNdetailkhutbah.detailSemuaKhutbahLists.get(position).url_gambar_khutbah)
                .crossFade()
                .placeholder(R.drawable.gambar)
                .into(gambar_kajian);

    }
}
