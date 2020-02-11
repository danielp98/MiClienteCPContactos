package net.ivanvega.miclientecpcontactos;

import android.net.Uri;

public class ContactosContractProvider {

    public static final Uri CONTENT_URI_CONTACTOS =
            Uri.parse( "content://net.ivanvega.mibasedatosp77a.provider/contactos");


    public static final  String [] PROJECTION_CONTACTOS
            = {
            "_id", "usuario", "email", "tel", "fecNacimiento"
    };

    public static final  String FIELD_ID = "_id";
    public static final  String FIELD_USUARIO = "usuario";
    public static final  String FIELD_EMAIL = "email";

}
