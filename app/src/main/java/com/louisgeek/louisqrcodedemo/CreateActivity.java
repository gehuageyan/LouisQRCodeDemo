package com.louisgeek.louisqrcodedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import cn.bingoogolapple.qrcode.zxing.QRCodeDecoder;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

public class CreateActivity extends AppCompatActivity {

    EditText editText;
    ImageView imageView;
    Button button_read;
    Bitmap bitmapNow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
       editText= (EditText) findViewById(R.id.id_et);
        imageView= (ImageView) findViewById(R.id.id_iv);
        button_read= (Button) findViewById(R.id.id_read);

        findViewById(R.id.id_create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String str= editText.getText().toString();
                if (str==null||str.equals("")||str==""){
                    Toast.makeText(CreateActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                QRCodeEncoder.encodeQRCode(str, 400, new QRCodeEncoder.Delegate() {
                    @Override
                    public void onEncodeQRCodeSuccess(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                        bitmapNow=bitmap;
                        button_read.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onEncodeQRCodeFailure() {
                        Toast.makeText(CreateActivity.this, "生成二维码出错", Toast.LENGTH_SHORT).show();
                        button_read.setVisibility(View.GONE);

                    }
                });
            }
        });

        findViewById(R.id.id_create_with_logo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String str= editText.getText().toString();
                if (str==null||str.equals("")||str==""){
                    Toast.makeText(CreateActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
               Bitmap bitmapLogo= BitmapFactory.decodeResource(getResources(),R.mipmap.logo);

                QRCodeEncoder.encodeQRCode(str,450, Color.parseColor("#ff0000"), bitmapLogo, new QRCodeEncoder.Delegate() {
                    @Override
                    public void onEncodeQRCodeSuccess(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                        bitmapNow=bitmap;
                        button_read.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onEncodeQRCodeFailure() {
                        Toast.makeText(CreateActivity.this, "生成带logo二维码出错", Toast.LENGTH_SHORT).show();
                        button_read.setVisibility(View.GONE);
                    }
                });

        }
        });


        button_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Bitmap bitmapNew2 = null;
                Drawable drawable = getResources().getDrawable(R.mipmap.test_chinese);

                if (drawable != null) {
                    bitmapNew2 = ((BitmapDrawable) drawable).getBitmap();
                }
*/
                Bitmap bitmapNew2= BitmapFactory.decodeResource(getResources(),R.mipmap.test_chinese);

                if (bitmapNow==null){
                return;
                }

                QRCodeDecoder.decodeQRCode(bitmapNow, new QRCodeDecoder.Delegate() {
                    @Override
                    public void onDecodeQRCodeSuccess(String result) {
                        Toast.makeText(CreateActivity.this, "解析成功result：" + result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDecodeQRCodeFailure() {
                        //I had the same problem.
                        // I used an image that I knew had a valid QR code and I also got the com.google.zxing.NotFoundException.
                        // 自己生成的有问题，再试试
                        Toast.makeText(CreateActivity.this, "解析二维码出错", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }



}
