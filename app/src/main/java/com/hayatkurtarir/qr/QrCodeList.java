package com.hayatkurtarir.qr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.client.android.Intents;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.hayatkurtarir.R;

public class QrCodeList extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_code_list);
    }

    public void ReadQrCode(View view) {
        Intent intentScan = new Intent(this, ReadQrCode.class);
        intentScan.setAction(Intents.Scan.ACTION);
        intentScan.putExtra(Intents.Scan.PROMPT_MESSAGE, "");
        startActivityForResult(intentScan, 0x0000c0de);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                return;
            } else {
                if (intentResult.getContents().contains("hayatkurtarir.com/")) {
                    Intent i = new Intent(this, ShowQrData.class);
                    i.putExtra("URL", intentResult.getContents().replace("hayatkurtarir.com/", ""));
                    startActivity(i);
                } else {
                    Toast.makeText(getBaseContext(), getResources().getString(R.string.unsuported_link), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}
