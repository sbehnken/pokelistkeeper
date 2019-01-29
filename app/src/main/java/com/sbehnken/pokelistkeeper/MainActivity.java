package com.sbehnken.pokelistkeeper;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Pokemon List");
        setSupportActionBar(toolbar);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        Button mStartOverlayButton = findViewById(R.id.start_overlay_button);
//
//        mStartOverlayButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, PokemonList.class));
//            }
//        });
//
//
//    }

}

  //  This will make it clickable, but it won't be over the lock screen WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
