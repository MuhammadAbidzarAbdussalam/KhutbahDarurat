package com.slamgantdroid.khutbahdarurat2.Kultum;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.slamgantdroid.khutbahdarurat2.Database.GSONsemuakhutbah;
import com.slamgantdroid.khutbahdarurat2.R;
import com.slamgantdroid.khutbahdarurat2.SemuaKhutbah.AdapterSemuaKhutbah;

/**
 * A simple {@link Fragment} subclass.
 */
public class KultumFragment extends Fragment {

    RecyclerView recyclerView;
    RequestQueue requestQueue;
    StringRequest stringRequest;
    GSONsemuakhutbah gsoNsemuakhutbah;
    SwipeRefreshLayout swipeRefreshLayout;
    ImageView imgError;

    public static KultumFragment newInstance() {
        // Required empty public constructor
        return new KultumFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kultum, container, false);

        imgError = (ImageView)view.findViewById(R.id.imgerror);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_kultum);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        //==
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        //==
        requestQueue = Volley.newRequestQueue(getActivity());


        //==
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_semua_khutbah);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_green_light,
                android.R.color.holo_blue_dark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);

                loadData();

            }
        });
        //==

        loadData();

        return view;
    }

    private void loadData() {
        swipeRefreshLayout.setRefreshing(true);
        stringRequest = new StringRequest(Request.Method.POST, "http://10.10.10.7/khutbah_darurat/listKultum.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();

                    gsoNsemuakhutbah = gson.fromJson(response, GSONsemuakhutbah.class);
                    AdapterSemuaKhutbah semuaKhutbah = new AdapterSemuaKhutbah(getActivity(), gsoNsemuakhutbah.semuaKhutbahLists);
                    recyclerView.setAdapter(semuaKhutbah);
                    swipeRefreshLayout.setRefreshing(false);
                } catch (NullPointerException e) {
                    Toast.makeText(getActivity(), "Maaf Konten Masih Kosong !?!?", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                swipeRefreshLayout.setRefreshing(false);
                Snackbar.make(getView(), "Koneksi internet terputus !!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                imgError.setVisibility(View.VISIBLE);
            }
        });

        requestQueue.add(stringRequest);

    }

}
