package com.louisgeek.louisqrcodedemo;

import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

public class ScanActivity extends AppCompatActivity implements QRCodeView.Delegate{

    ZXingView zXingView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);


        zXingView= (ZXingView) findViewById(R.id.id_zv);

        /*zXingView.setResultHandler(new QRCodeView.Delegate() {
            @Override
            public void onScanQRCodeSuccess(String result) {

            }

            @Override
            public void onScanQRCodeOpenCameraError() {

            }
        });*/

        zXingView.setResultHandler(this);
        //zXingView.startCamera();//打开摄像头开始预览，但是并未开始识别
        zXingView.startSpot();//延迟1.5秒后开始识别
        //zXingView.startSpotDelay(delay)//延迟delay毫秒后开始识别
        //zXingView.showScanRect();//显示扫描框
       // zXingView.startSpotAndShowRect();//显示扫描框，并且延迟1.5秒后开始识别
       // zXingView.openFlashlight();//打开闪光灯
    }

    @Override
    protected void onStart() {
        super.onStart();
        zXingView.startCamera();
    }

    @Override
    protected void onStop() {
        super.onStop();
        zXingView.stopCamera();
    }


    @Override
    public void onScanQRCodeSuccess(String result) {
        Toast.makeText(this,"扫描成功+result："+result,Toast.LENGTH_SHORT).show();
        /**
         * 震动
         */
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);

        zXingView.startSpot();//继续扫描

    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Toast.makeText(this,"打开相机出错",Toast.LENGTH_SHORT).show();
    }
}
