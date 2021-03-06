package com.ejemplo.album.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.ejemplo.album.R;
import com.ejemplo.album.controller.AlbumController;
import com.ejemplo.album.model.Album;

import java.util.List;

import util.ResultListener;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        AlbumController albumController = new AlbumController(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewMain);


        if(albumController.hayInternet()){
        albumController.getAlbumsWEB(new ResultListener<List<Album>>() {
            @Override
            public void finish(List<Album> resultado) {
                AdapterAlbums adapterAlbums = new AdapterAlbums(resultado, MainActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapterAlbums);
            }
        });

    } else {
            AdapterAlbums adapterAlbums = new AdapterAlbums(albumController.getAlbumsDB(), MainActivity.this);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapterAlbums);
            }
        }
}











