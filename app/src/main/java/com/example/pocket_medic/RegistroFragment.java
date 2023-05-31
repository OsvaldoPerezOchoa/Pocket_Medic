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


public class RegistroFragment extends Fragment {

    private EditText reg_usuario, reg_nombre,reg_contra,reg_contra2,reg_telefono,reg_corre;
    private Button btn_registro;
    private TextView txt_back;

    String nombre,contra,contra2,usuar,telef,email;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registro, container, false);
        reg_usuario = (EditText)view.findViewById(R.id.reg_usuario);
        reg_nombre = (EditText)view.findViewById(R.id.reg_nombre);
        reg_contra = (EditText)view.findViewById(R.id.reg_contra);
        reg_contra2 = (EditText)view.findViewById(R.id.reg_contra2);
        reg_telefono = (EditText)view.findViewById(R.id.reg_telefono);
        reg_corre = (EditText)view.findViewById(R.id.reg_corre);
        btn_registro = (Button)view.findViewById(R.id.btn_registro);
        txt_back = (TextView)view.findViewById(R.id.txt_back);

        //Hacemos que un edittesxt sirva como un boton que nos lleve a un formulario de registro
        txt_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);

                transaction.replace(R.id.fragment_container,LoginFragment.class,null);
                transaction.commit();
            }
        });
        btn_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre = reg_nombre.getText().toString();
                contra = reg_contra.getText().toString();
                contra2 = reg_contra2.getText().toString();
                usuar = reg_usuario.getText().toString();
                telef = reg_telefono.getText().toString();
                email = reg_corre.getText().toString();

                if(!nombre.isEmpty() && !contra.isEmpty() && !contra2.isEmpty() && !usuar.isEmpty() && !telef.isEmpty() && !email.isEmpty())
                {
                    registrousuario("http://172.16.8.31/pocketmedic/registrar_usuario.php");
                }
                else
                {
                    Toast.makeText(getActivity(),"No se permiten campos vacios",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    private void registrousuario(String URL)
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
                    Toast.makeText(getActivity(),"Usuario ya registrado",Toast.LENGTH_SHORT).show();
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
                parametros.put("nombre",nombre);
                parametros.put("usuario",usuar);
                parametros.put("contrasena",contra);
                parametros.put("no_telefono",telef);
                parametros.put("correo",email);

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}