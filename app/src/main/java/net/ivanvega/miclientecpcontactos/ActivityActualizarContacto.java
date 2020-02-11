package net.ivanvega.miclientecpcontactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityActualizarContacto extends AppCompatActivity {

    EditText usuario, correo, telefono;
    String uriActualizar = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_contacto);

        usuario = findViewById(R.id.editTextUsuario);
        correo = findViewById(R.id.editTextEmail);
        telefono = findViewById(R.id.editTextTel);

        Bundle uri = this.getIntent().getExtras();
        if(uri !=null){
            uriActualizar = uri.getString("uriContactoActualizar");

            usuario.setText(uri.getString("usuarioActualizar"));
            correo.setText(uri.getString("correoActualizar"));
            telefono.setText(uri.getString("telefonoActualizar"));

            //Toast.makeText(this, uri.getString("uriContactoActualizar") + uri.getString("usuarioActualzar") + uri.getString("correoActualizar") + uri.getString("telefonoActualizar"), Toast.LENGTH_SHORT).show();
        }

    }


    public void onClick(View view) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(ContactosContractProvider.PROJECTION_CONTACTOS[1],
                usuario.getText().toString());
        contentValues.put(ContactosContractProvider.PROJECTION_CONTACTOS[2],
                correo.getText().toString());
        contentValues.put(ContactosContractProvider.PROJECTION_CONTACTOS[3],
                telefono.getText().toString());

        Uri uriContactoActualizar = Uri.parse(uriActualizar);

        int miuri = getContentResolver().update(uriContactoActualizar, contentValues, null, null);

        this.finish();
    }
}
