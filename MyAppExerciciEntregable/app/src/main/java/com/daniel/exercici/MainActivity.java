package com.daniel.exercici;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, surname, web, phone;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getSharedPreferences("MyPreferences", MODE_PRIVATE);

        name = findViewById(R.id.txtName);
        surname = findViewById(R.id.txtSurname);
        web = findViewById(R.id.txtWeb);
        phone = findViewById(R.id.txtPhone);


    }

    public void validate(View view) {

        if (checkFields()) {
            goToSecondActivity(view);
        }
    }

    private boolean checkFields() {

        boolean fieldsOk = true;
        if ("".equals(name.getText().toString())) {
            fieldsOk = false;
            name.setError(getString(R.string.errEmptyName));
        } else if ("".equals(surname.getText().toString())) {
            fieldsOk = false;
            surname.setError(getString(R.string.errEmptySurname));
        } else if ("".equals(web.getText().toString())) {
            fieldsOk = false;
            web.setError(getString(R.string.errEmptyWeb));
        } else if ("".equals(phone.getText().toString())) {
            fieldsOk = false;
            phone.setError(getString(R.string.errEmptyPhone));
        }
        return fieldsOk;

    }

    public void goToSecondActivity(View view) {


        if (checkFields()) {

            String nombre = name.getText().toString();
            String apellido = surname.getText().toString();
            String pagina = web.getText().toString();
            String telefono = phone.getText().toString();

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("name", nombre);
            intent.putExtra("surname", apellido);
            intent.putExtra("web", pagina);
            intent.putExtra("phone", telefono);
            startActivity(intent);
        } else {
            Toast.makeText(this, getString(R.string.errEmpty), Toast.LENGTH_SHORT).show();
        }
    }

    public void clean(View view) {
        openDialog();
    }

    public void openDialog() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle(getString(R.string.alertTitle)); //modificar el titol

        alertDialogBuilder.setMessage(R.string.alertText)

                .setCancelable(false)


                .setPositiveButton(getString(R.string.btnShow), new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {

                        //Què volem fer si clica que si.
                        deleteFields();

                    }

                })

                .setNegativeButton(getString(R.string.btnClear), new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {

                        dialog.dismiss(); // No fem res, tanquem el Alert.

                    }

                });

        AlertDialog alertDialog = alertDialogBuilder.create(); //crear el alert dialog

        alertDialog.show(); //mostrar per pantalla

    }

    private void deleteFields() {

        name.setText("");
        surname.setText("");
        web.setText("");
        phone.setText("");
    }


}