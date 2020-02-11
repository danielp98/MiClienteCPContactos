package net.ivanvega.miclientecpcontactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView lv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Log.d("MIURI", miuri.toString());*/

        lv = findViewById(R.id.lv);

        ActualizarListView(lv);


    }

    public void ActualizarListView(ListView lv){
        Cursor cursor  =   getContentResolver().query(
                ContactosContractProvider.CONTENT_URI_CONTACTOS
                //Uri.parse(ContactosContractProvider.CONTENT_URI_CONTACTOS.toString() + "/21")
                ,
                ContactosContractProvider.PROJECTION_CONTACTOS,
                null, null,null);

        SimpleCursorAdapter adp = new SimpleCursorAdapter(
                this,android.R.layout.simple_list_item_2, cursor,
                new String[]{
                        ContactosContractProvider.FIELD_USUARIO,
                        ContactosContractProvider.FIELD_EMAIL
                },
                new int[]{   android.R.id.text1, android.R.id.text2},
                SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE
        );

        lv.setAdapter(adp);

        registerForContextMenu(lv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.context_menu, menu);
    }

    Cursor cursorListView = null;

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        cursorListView = (Cursor) lv.getItemAtPosition(info.position);
        //cursorListView.moveToFirst();


        switch (item.getItemId()) {
            case R.id.actualizar:
                long idActualizar = cursorListView.getLong(cursorListView.getColumnIndex("_id"));
                String UsuarioActualizar = cursorListView.getString(cursorListView.getColumnIndex("usuario"));
                String CorreoActualizar = cursorListView.getString(cursorListView.getColumnIndex("email"));
                String TelefonoActualizar = cursorListView.getString(cursorListView.getColumnIndex("tel"));
                String uriStringActualizar = String.valueOf(ContactosContractProvider.CONTENT_URI_CONTACTOS);
                uriStringActualizar += "/" + idActualizar;
                //Toast.makeText(this, uriString, Toast.LENGTH_SHORT).show();
                //Uri uriContactoId = Uri.parse(uriStringActualizar);
                Intent intentActualizar = new Intent(this, ActivityActualizarContacto.class);
                intentActualizar.putExtra("uriContactoActualizar", uriStringActualizar);
                intentActualizar.putExtra("usuarioActualizar", UsuarioActualizar);
                intentActualizar.putExtra("correoActualizar", CorreoActualizar);
                intentActualizar.putExtra("telefonoActualizar", TelefonoActualizar);
                //Toast.makeText(this, uriStringActualizar + UsuarioActualizar + CorreoActualizar + TelefonoActualizar, Toast.LENGTH_SHORT).show();
                startActivity(intentActualizar);
                return true;
            case R.id.eliminar:
                long id = cursorListView.getLong(cursorListView.getColumnIndex("_id"));
                String uriString = String.valueOf(ContactosContractProvider.CONTENT_URI_CONTACTOS);
                uriString += "/" + id;
                Toast.makeText(this, uriString, Toast.LENGTH_SHORT).show();
                Uri uriContactoId = Uri.parse(uriString);
                int ContactoEliminado = getContentResolver().delete(uriContactoId, null, null);
                //Toast.makeText(this, "Id de contacto eliminado: " + ContactoEliminado, Toast.LENGTH_SHORT).show();
                ActualizarListView(lv);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        ActualizarListView(lv);

    }

    public void onClick(View view) {
        Intent intent = new Intent(this, ActivityAgregarContacto.class);
        startActivity(intent);
    }
}
