package com.hayatkurtarir.qr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.client.android.Intents;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.hayatkurtarir.R;

public class SelQrCode extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sel_qr_code);
    }

    public void CreQrCode(View view) {
        Intent i = new Intent(this, CreQrCode.class);
        startActivity(i);
    }


    public void QrCodeList(View view) {
        Intent i = new Intent(this, QrCodeList.class);
        startActivity(i);
    }
}
