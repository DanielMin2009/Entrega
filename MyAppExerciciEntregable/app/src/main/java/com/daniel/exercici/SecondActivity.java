package com.daniel.exercici;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView tv_name, tv_surname, tv_web, tv_phone, tv_contador;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        tv_name = findViewById(R.id.tv_name);
        tv_surname = findViewById(R.id.tv_surname);
        tv_web = findViewById(R.id.tv_web);
        tv_phone = findViewById(R.id.tv_phone);
        tv_contador = findViewById(R.id.tv_contador);

        String name = getIntent().getStringExtra("name");
        String surname = getIntent().getStringExtra("surname");
        String web = getIntent().getStringExtra("web");
        String phone = getIntent().getStringExtra("phone");

        prefs = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        int counter = prefs.getInt("counter", 0);
        counter = counter +1;
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("counter", counter);
        editor.commit();


        tv_name.setText(name);
        tv_surname.setText(surname);
        tv_web.setText(web);
        tv_phone.setText(phone);
        tv_contador.setText(String.valueOf(prefs.getInt("counter", 0)));



    }

    public void goToDial(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);

        startActivity(intent);

    }

    public void goToWeb(View view) {
        String web = getIntent().getStringExtra("web");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://" + web));
        startActivity(intent);

    }


    public void reset(View view) {
        prefs = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();


    }

}

