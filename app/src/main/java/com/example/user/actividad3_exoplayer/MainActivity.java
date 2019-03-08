package com.example.user.actividad3_exoplayer;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class MainActivity extends AppCompatActivity {

    //creamos dos variables donde vamos a guardar la vista y el reproductor
    private PlayerView playerView;    //vista va a guardar el objeto de la vista.
    private SimpleExoPlayer player;    //reproductor


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        playerView = findViewById(R.id.player_view);//la inicializamos, la buscamos por el id como siempre
    }

    @Override
    protected void onStart() {
        super.onStart();

        player = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector());//creamos el reproductor que guardamos en la variable player

        playerView.setPlayer(player);//añadimos el reproductor a la view

        //configuramos el origen de datos, el origen multimedia con la url que queremos de reproducir por streaming. Al origen de datos le pasamos el contexto y el nombre de la aplicacion
        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this, "ExoPlayer"));

        //el archivo multimedia es el origen del medio, por eso creamos otra instancia de la clase ExtractorMediaSource.
        ExtractorMediaSource archivoMultimedia = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse("http://blueappsoftware.in/layout_design_android_blog.mp4"));

        //preparar el reproductor con el archivo que queremos reproducir
        player.prepare(archivoMultimedia);

        //lo ponemos en marcha
        player.setPlayWhenReady(true);
    }

    //cuando la activity no sea visible lo paramos
    @Override
    protected void onStop() {
        super.onStop();

        playerView.setPlayer(null); // la view no va tener ningun player configurado
        player.release();//al reproductor lo dejamos limpio
        player=null;
    }
}
