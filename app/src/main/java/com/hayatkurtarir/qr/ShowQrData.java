package com.hayatkurtarir.qr;

import static com.hayatkurtarir.qr.CreQrCode.decode;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
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
        setText(R.id.name, params[0]);
        setText(R.id.num, params[1]);
        setText(R.id.bday, params[2]);
        setText(R.id.dis, decode(params[3]));
        setText(R.id.blood, params[4]);
        Log.e("testtest", params[5]);
        if (!(params[5].contains("empty"))) {
            setText(R.id.note, getResources().getString(R.string.note) + " " );
            setText(R.id.note_i, decode(params[5]));
            findViewById(R.id.note_ll).setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("SetTextI18n")
    void setText(int ResId, String params) {
            ((TextView)(findViewById(ResId))).setText(params);

    }
}
