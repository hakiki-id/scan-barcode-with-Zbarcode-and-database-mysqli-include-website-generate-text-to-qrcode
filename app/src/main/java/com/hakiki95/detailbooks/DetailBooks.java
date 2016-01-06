package com.hakiki95.detailbooks;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.transform.Result;

/**
 * Created by Hakiki on 20/12/2015.
 */
public class DetailBooks extends AppCompatActivity {
    TextView judulBuku,tahunBuku,penerbitBuku,sinopsisBuku,idBarcode;
    ImageView ImageBuku;

    Button btnScanUlang, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent databarcode = getIntent();
        String getdatabarcode = databarcode.getStringExtra(BarcodeScanner.DATABARCODE);
        //integrasi content

        judulBuku = (TextView) findViewById(R.id.tvjudulbuku);
        idBarcode = (TextView) findViewById(R.id.tvBarcode);
        tahunBuku = (TextView) findViewById(R.id.tvTahun);
        penerbitBuku = (TextView) findViewById(R.id.tvPenerbit);
        sinopsisBuku = (TextView) findViewById(R.id.tvSinopsis);
        ImageBuku = (ImageView) findViewById(R.id.imageBuku);
        btnScanUlang = (Button) findViewById(R.id.btnScan);
        btnExit = (Button) findViewById(R.id.btnSelesai);

        idBarcode.setText(getdatabarcode);

        btnScanUlang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBarcode = new Intent(DetailBooks.this, BarcodeScanner.class);
                startActivity(goBarcode);

            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHome = new Intent(DetailBooks.this, Beranda.class);
                startActivity(goHome);

            }
        });


            DoingBackgroudnTaks get = new DoingBackgroudnTaks(DetailBooks.this);
            get.execute(idBarcode.getText().toString());

    }



    private class DoingBackgroudnTaks extends AsyncTask <String, Void, String> {
        ProgressDialog dialog ;
        Context context;

        public DoingBackgroudnTaks(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(getApplicationContext());
            dialog.setTitle("Connecting Server");
            dialog.setMessage("mengambil data ...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String urlsLIKE = "http://192.168.43.19/buku/getdatalikeidJSON.php";
            String Params = params[0];
            String Respon="",Line="";
            try {
                URL urls = new URL(urlsLIKE);
                HttpURLConnection Connection = (HttpURLConnection) urls.openConnection();
                Connection.setRequestMethod("POST");
                Connection.setDoInput(true);
                Connection.setDoOutput(true);

                OutputStream OS = Connection.getOutputStream();
                BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(OS));

                String barcode = URLEncoder.encode("barcode","UTF-8")+"="+URLEncoder.encode(params.toString(),"UTF-8");

                BW.write(barcode);
                BW.close();
                BW.close();
                OS.close();

                InputStream IS  = Connection.getInputStream();
                BufferedReader BR = new BufferedReader(new InputStreamReader(IS));



                while ((Line=BR.readLine())!=null) {
                    Respon += Line;
                }



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return Respon;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            dialog.cancel();
            try {
                JSONObject data = new JSONObject(result);
                JSONArray dbuku = data.getJSONArray("buku");

                String judul = dbuku.getJSONObject(0).getString("judul");
                String penerbit = dbuku.getJSONObject(0).getString("penerbit");
                String tahun = dbuku.getJSONObject(0).getString("tahun");
                String sinopsis = dbuku.getJSONObject(0).getString("sinopsis");

                judulBuku.setText(judul);
                penerbitBuku.setText(penerbit);
                tahunBuku.setText(tahun);
                sinopsisBuku.setText(sinopsis);


            } catch (JSONException e) {
                e.printStackTrace();
            }



        }
    }
}
