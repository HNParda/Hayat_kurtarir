package com.hayatkurtarir.qr;

import static com.hayatkurtarir.qr.CreQrCode.decode;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.hayatkurtarir.R;

public class ShowQrData extends Activity {

    boolean SafeQrCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_qr_data);
        Uri LinkData = getIntent().getData();
        String url;

        if(LinkData != null){
            url = LinkData.getPathSegments().toString();
        } else {
            url = getIntent().getExtras().getString("URL");
        }
        url = url.replace("+", "&")
                 .replace("-s", " ")
                 .replace("-p", "+")
                 .replace("[", "")
                 .replace("]", "");

        String[] params = url.split("&");
        SafeQrCode = params[6].matches("yes");
        InfoString(R.id.name, R.string.name, params[0]);
        InfoString(R.id.num, R.string.emergency_num, params[1]);
        InfoString(R.id.bday, R.string.bday, params[2]);
        InfoString(R.id.dis, R.string.dis, decode(params[3]) + SafeQrCode);
        InfoString(R.id.blood, R.string.blood, params[4]);
        Log.e("testtest", params[5]);
        if (!(params[5].contains("empty"))) {
            InfoString(R.id.note, R.string.note, decode(params[5]));
        }
    }

    @SuppressLint("SetTextI18n")
    void InfoString(int ResId, int StringId, String params) {
            ((TextView)(findViewById(ResId))).setText(getResources().getString(StringId) + " " + params);

    }
}
