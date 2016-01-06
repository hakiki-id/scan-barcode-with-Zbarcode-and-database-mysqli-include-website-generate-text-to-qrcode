package com.hakiki95.detailbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Beranda extends AppCompatActivity {
    Button btnScan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        btnScan = (Button) findViewById(R.id.btnCari);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goScan = new Intent(Beranda.this, BarcodeScanner.class);
                startActivity(goScan);
            }
        });
    }
}
