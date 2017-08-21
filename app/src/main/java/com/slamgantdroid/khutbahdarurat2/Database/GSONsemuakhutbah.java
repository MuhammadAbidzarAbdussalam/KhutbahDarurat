package com.slamgantdroid.khutbahdarurat2.Database;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by WIN10 on 02/07/2017.
 */

public class GSONsemuakhutbah {

    @SerializedName("hasil")
    public List<SemuaKhutbahList>semuaKhutbahLists;


    public class SemuaKhutbahList {
        @SerializedName("id_isi_khutbah")
        public String id_isi_khutbah;

        @SerializedName("judul_khutbah")
        public String judul_khutbah;

        @SerializedName("huruf_depan_nama_ustad")
        public String huruf_depan_nama_ustad;

        @SerializedName("url_gambar_ustad")
        public String url_gambar_ustad;

        @SerializedName("nama_ustad")
        public String nama_ustad;

        @SerializedName("nama_tema_khutbah")
        public String nama_tema_khutbah;

        @SerializedName("nama_kategori")
        public String nama_kategori;
    }
}
