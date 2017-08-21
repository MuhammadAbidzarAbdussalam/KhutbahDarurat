package com.slamgantdroid.khutbahdarurat2.Database;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by WIN10 on 09/08/2017.
 */

public class GSONdetailkhutbah {

    @SerializedName("hasil")
    public List<DetailSemuaKhutbahList> detailSemuaKhutbahLists;


    public class DetailSemuaKhutbahList {
        @SerializedName("id_isi_khutbah")
        public String id_isi_khutbah;

        @SerializedName("judul_khutbah")
        public String judul_khutbah;

        @SerializedName("huruf_depan_nama_ustad")
        public String huruf_depan_nama_ustad;

        @SerializedName("url_gambar_ustad")
        public String url_gambar_ustad;

        @SerializedName("url_gambar_khutbah")
        public String url_gambar_khutbah;

        @SerializedName("nama_ustad")
        public String nama_ustad;

        @SerializedName("nama_tema_khutbah")
        public String nama_tema_khutbah;

        @SerializedName("isi_khutbah")
        public String isi_khutbah;

    }
}
