package com.example.pocket_medic;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginFragment extends Fragment {

    private TextView txt_regis;
    private EditText txt_usuario,txt_password;
    private Button btn_validar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_login, container, false);

        txt_regis = (TextView)vista.findViewById(R.id.lbl_regis);
        txt_usuario = (EditText)vista.findViewById(R.id.reg_usuario);
        txt_password = (EditText)vista.findViewById(R.id.reg_contra);
        btn_validar = (Button)vista.findViewById(R.id.btnLogin);


        //Hacemos que un edittext sirva como un boton que nos lleve a un formulario de registro
        txt_regis.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View vista) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);

                transaction.replace(R.id.fragment_container,RegistroFragment.class,null);
                transaction.commit();
            }
        });

        btn_validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarusuario("http://172.16.13.203/PocketMedic/login.php");
            }
        });
        return vista;
    }

    private void validarusuario(String URL)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty())
                {
                    Intent intent =  new Intent(getActivity(),MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getActivity(),"Correo o Contraseña incorrecta",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("correo",txt_usuario.getText().toString());
                parametros.put("contrasena",txt_password.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}