package com.hakiki95.detailbooks;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;

/**
 * Created by kvprasad on 10/3/2015.
 */
public class BarcodeScanner extends AppCompatActivity {

    private Camera mCamera;
    private CameraPreview mPreview;
    private Handler autoFocusHandler;

    private Button btnBatal, btnStartScanner;
    private ImageScanner scanner;

    private boolean barcodeScanned = false;
    private boolean previewing = true;

    public static final String DATABARCODE ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        btnStartScanner = (Button) findViewById(R.id.btnStartScan);

        btnStartScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStartScanner.setVisibility(View.GONE);
                initControls();
            }
        });
        btnBatal = (Button) findViewById(R.id.btnbatal);
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBeranda = new Intent(BarcodeScanner.this, Beranda.class);
                releaseCamera();
                startActivity(goBeranda);
                finish();
            }
        });




    }

    private void initControls() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        autoFocusHandler = new Handler();
        mCamera = getCameraInstance();

        // Instance barcode scanner
        scanner = new ImageScanner();
        scanner.setConfig(0, Config.X_DENSITY, 3);
        scanner.setConfig(0, Config.Y_DENSITY, 3);

        mPreview = new CameraPreview(BarcodeScanner.this, mCamera, previewCb,
                autoFocusCB);
        FrameLayout preview = (FrameLayout) findViewById(R.id.FrameCamera);
        preview.addView(mPreview);



    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            releaseCamera();
        }
        return super.onKeyDown(keyCode, event);
    }


    /**
     * A safe way to get an instance of the Camera object.
     */
    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open();
        } catch (Exception e) {
        }
        return c;
    }

    private void releaseCamera() {
        if (mCamera != null) {
            previewing = false;
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }

    private Runnable doAutoFocus = new Runnable() {
        public void run() {
            if (previewing)
                mCamera.autoFocus(autoFocusCB);
        }
    };
    // Mimic continuous auto-focusing

    Camera.AutoFocusCallback autoFocusCB = new Camera.AutoFocusCallback() {
        public void onAutoFocus(boolean success, Camera camera) {
            autoFocusHandler.postDelayed(doAutoFocus, 1000);
        }
    };

    Camera.PreviewCallback previewCb = new Camera.PreviewCallback() {
        public void onPreviewFrame(byte[] data, Camera camera) {
            Camera.Parameters parameters = camera.getParameters();
            Camera.Size size = parameters.getPreviewSize();

            Image barcode = new Image(size.width, size.height, "Y800");
            barcode.setData(data);

            int result = scanner.scanImage(barcode);

            if (result != 0) {
                previewing = false;
                mCamera.setPreviewCallback(null);
                mCamera.stopPreview();

                SymbolSet syms = scanner.getResults();

                for (Symbol sym : syms) {

                    Log.i("<<<<<<Asset Code>>>>> ",
                            "<<<<Bar Code>>> " + sym.getData());
                    String scanResult = sym.getData().trim();

                                        showAlertDialog(scanResult);

                  /*  Toast.makeText(BarcodeScanner.this, scanResult,
                            Toast.LENGTH_SHORT).show();*/

                    barcodeScanned = true;

                    break;
                }
            }
        }
    };



    private void showAlertDialog(final String message) {

        new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.app_name))
                .setCancelable(false)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent goDetail = new Intent(BarcodeScanner.this, DetailBooks.class);
                          goDetail.putExtra(DATABARCODE, message);
                          releaseCamera();
                          startActivity(goDetail);


                    }
                })

                .setNegativeButton("SCAN ULANG", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (barcodeScanned) {
                            barcodeScanned = false;
                            mCamera.setPreviewCallback(previewCb);
                            mCamera.startPreview();
                            previewing = true;
                            mCamera.autoFocus(autoFocusCB);
                        }
                    }
                })

                .show();
    }



}
