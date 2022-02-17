package com.hayatkurtarir.qr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
