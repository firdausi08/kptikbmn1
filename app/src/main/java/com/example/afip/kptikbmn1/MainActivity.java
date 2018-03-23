package com.example.afip.kptikbmn1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout laporbutton, ruanganbutton;

   // @InjectView(R.id.button_lapor) Button laporbutton;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        laporbutton = (RelativeLayout) findViewById(R.id.button_lapor);
        ruanganbutton = (RelativeLayout) findViewById(R.id.button_aula);


        laporbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, LaporActivity.class);
                MainActivity.this.startActivity(in);
            }
        });

        ruanganbutton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
               Intent in = new Intent(MainActivity.this, Ruangan.class);
               MainActivity.this.startActivity(in);
        }
        });
    }
}
