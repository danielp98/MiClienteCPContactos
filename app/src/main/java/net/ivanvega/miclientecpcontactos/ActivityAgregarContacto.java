package net.ivanvega.miclientecpcontactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ActivityAgregarContacto extends AppCompatActivity {

    EditText usuario, correo, telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contacto);

        usuario = findViewById(R.id.editTextUsuario);
        correo = findViewById(R.id.editTextEmail);
        telefono = findViewById(R.id.editTextTel);

    }

    public void onClick(View view) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(ContactosContractProvider.PROJECTION_CONTACTOS[1],
                usuario.getText().toString());
        contentValues.put(ContactosContractProvider.PROJECTION_CONTACTOS[2],
                correo.getText().toString());
        contentValues.put(ContactosContractProvider.PROJECTION_CONTACTOS[3],
                telefono.getText().toString());


        Uri miuri =  getContentResolver().insert(
                ContactosContractProvider.CONTENT_URI_CONTACTOS,
                contentValues
        );

        this.finish();
    }
}
