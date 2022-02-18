package com.hayatkurtarir.qr;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import com.google.zxing.WriterException;
import com.hayatkurtarir.R;

public class ShowQrCode extends Activity {

    Bitmap bitmap;
    QrEncoder qrgEncoder;
    private ImageView qrCodeIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_qr_code);
        qrCodeIV = findViewById(R.id.idIVQrcode);
        RenderQrCode();
    }

    public void RenderQrCode() {
            WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            int width = point.x;
            int height = point.y;
            int dimen = Math.min(width, height);
            dimen = dimen * 5;
            String infos = getIntent().getExtras().getString("URL").replace(", ", "+");
            infos = infos.replace(" ", "")
                     .replace("[", "")
                     .replace("]", "");
            qrgEncoder = new QrEncoder(infos, null, QrEncoder.QrContents.Type.TEXT, dimen);
        try {
            bitmap = qrgEncoder.encodeAsBitmap();
        } catch (WriterException e) {
            e.printStackTrace();
        }
        qrCodeIV.setImageBitmap(bitmap);
    }

}
