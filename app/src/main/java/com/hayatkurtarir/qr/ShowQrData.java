package com.hayatkurtarir.qr;

import static com.hayatkurtarir.qr.CreQrCode.decode;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hayatkurtarir.R;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ShowQrData extends Activity {

    boolean SafeQrCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_qr_data);
        Uri URIdata = getIntent().getData();
        String url = " ";

        if(URIdata != null){
            url = URIdata.getPathSegments().toString();
        } else {
            url = getIntent().getExtras().getString("URL");
        }
        url = url.replace("+", "&")
                 .replace("-s", " ")
                 .replace("-p", "+")
                 .replace("[", "")
                 .replace("]", "");

        String[] params = url.toString().split("\\&");
        SafeQrCode = params[6].matches("yes");
        InfoString(R.id.name, R.string.name, params[0]);
        InfoString(R.id.num, R.string.emergency_num, params[1]);
        InfoString(R.id.birthday, R.string.birthday, params[2]);
        InfoString(R.id.dis, R.string.dis, decode(params[3]) + String.valueOf(SafeQrCode));
        InfoString(R.id.blood, R.string.blood, params[4]);
        Log.e("testtest", params[5]);
        if (!(params[5].contains("empty"))) {
            InfoString(R.id.note, R.string.note, decode(params[5]));
        }
    }

    void InfoString(int ResId, int StringId, String params) {
            ((TextView)(findViewById(ResId))).setText(getResources().getString(StringId) + " " + params);

    }
}
