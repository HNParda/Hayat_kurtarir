package com.hayatkurtarir.qr;

import android.app.Activity;
import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hayatkurtarir.R;

import java.io.Console;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class CreQrCode extends AppCompatActivity {

    private EditText Name;
    private EditText Num;
    private EditText Birthday;
    private EditText Dis;
    private EditText Blood;
    private EditText Note;
    public static String infos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cre_qr_code);

        Name = findViewById(R.id.name);
        Num = findViewById(R.id.num);
        Birthday = findViewById(R.id.birthday);
        Dis = findViewById(R.id.dis);
        Blood = findViewById(R.id.blood);
        Note = findViewById(R.id.note);
    }

    public void CreateaQrCode(View view) {
        if (checkFields()) {
            AlertDialog.Builder AD = new AlertDialog.Builder(this);
            AD.setCancelable(true)
                    .setTitle("Qr Kod kaydedilsin mi?")
                    .setMessage("Başkaları bu Qr Kodu okutduğunda Listelerinde Kayıtlı kalsın mı?")
                    .setNegativeButton(R.string.no, ClickListener())
                    .setPositiveButton(R.string.yes, ClickListener())
                    .create().show();
        } else {
            Toast.makeText(CreQrCode.this, getApplicationContext().getResources().getString(R.string.empty_input), Toast.LENGTH_SHORT).show();
        }
    }
    DialogInterface.OnClickListener ClickListener() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == -1) {
                    infos = infos + " yes";
                } else {
                    infos = infos + " no";
                }
                Intent qr = new Intent(getApplicationContext(), ShowQrCode.class);
                Log.e("StringCheck", infos);
                qr.putExtra("URL", "hayatkurtarir.com/" + infos);
                startActivity(qr);
            }
        };
    }
    public boolean checkFields() {
        String note = Note.getText().toString();


        String[] tempS = new String[]{
                " " + Name.getText().toString().replace(" ", "-s"),
                Num.getText().toString().replace("+", "-p"),
                Birthday.getText().toString(),
                encode(Dis.getText().toString()),
                Blood.getText().toString().replace("+", "-p")};


        if (note.matches("")) {
        infos = Arrays.toString(tempS) + ", empty,";
        } else {
            infos = Arrays.toString(tempS) + ", " + encode(note) + ",";

            Log.e("testtest encodedd", note);
            Log.e("testtest dedcoded", encode(note));

        }

        Log.e("StringCheck", infos);
        return !infos.contains(" ,");
    }

    public static String encode(String s) {
        return Base64.encodeToString(s.getBytes(), Base64.DEFAULT).replace("\n", "");
    }
    public static String decode(String s)  {
        try {
            return new String(Base64.decode(s, Base64.DEFAULT), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "Decode String failed";
        }
    }
}