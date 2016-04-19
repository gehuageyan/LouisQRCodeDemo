package com.louisgeek.louisqrcodedemo;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

//实现接口
public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    private  String TAG="louis_log";
    private static final int REQUEST_CODE_PERMISSIONS_CAMERA = 1;
    private static final int REQUEST_CODE_PERMISSIONS_DUO = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.id_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermissions4Duo();
            }
        });
        findViewById(R.id.id_btn_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CreateActivity.class));
            }
        });
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

        // Some permissions have been granted一些已授予的权限
        Log.d(TAG, "onPermissionsGranted:" + requestCode + ":" + perms.size());
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {


        // Some permissions have been denied一些被拒绝的权限
        Log.d(TAG, "onPermissionsGranted:" + requestCode + ":" + perms.size());
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions结果转发给EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

        @AfterPermissionGranted(REQUEST_CODE_PERMISSIONS_DUO)
        private void requestPermissions4Duo(){
            String[] perms = {Manifest.permission.CAMERA, Manifest.permission.FLASHLIGHT};
            if (!EasyPermissions.hasPermissions(this, perms)) {
                EasyPermissions.requestPermissions(this, "扫描二维码需要打开相机和散光灯的权限", REQUEST_CODE_PERMISSIONS_DUO, perms);
            }else{
                startActivity(new Intent(MainActivity.this,ScanActivity.class));
            }
        }


}
