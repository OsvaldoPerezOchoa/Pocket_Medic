package com.example.pocket_medic;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class LoginFragment extends Fragment {

    private TextView txt_regis;
    private EditText txt_usuario,txt_password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_login, container, false);

        txt_regis = (TextView)vista.findViewById(R.id.lbl_regis);
        txt_usuario = (EditText)vista.findViewById(R.id.edtUsuario);
        txt_password = (EditText)vista.findViewById(R.id.edtPassword);


        //Hacemos que un edittext sirva como un boton que nos lleve a un formulario de registro
        txt_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {
                Intent intent = new Intent(getActivity(),RegistroActivity.class);
                startActivity(intent);
            }
        });
        return vista;
    }
}