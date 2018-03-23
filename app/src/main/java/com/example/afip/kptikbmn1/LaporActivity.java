package com.example.afip.kptikbmn1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.afip.kptikbmn1.app.AppController;


import java.util.HashMap;
import java.util.Map;

import static com.example.afip.kptikbmn1.Config.DATA_URL;

public class LaporActivity extends AppCompatActivity {

    private EditText edit_title, edit_detail;
    private LinearLayout submit_lapor;
    ProgressDialog pd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lapor);

        edit_title = (EditText) findViewById(R.id.edit_title);
        edit_detail = (EditText) findViewById(R.id.edit_detail);
        submit_lapor = (LinearLayout) findViewById(R.id.submit_lapor);

        pd = new ProgressDialog(this);

        submit_lapor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String title = edit_title.getText().toString().trim();
                String detail = edit_detail.getText().toString().trim();
               // String foto = foto.getText().toString().trim();
                if (!title.isEmpty() && !detail.isEmpty()) {
                    simpanData(title, detail);
                }

//                else if (title.isEmpty()) {
//                    title.setError("title tidak boleh kosong");
//                    title.requestFocus();
//                } else if (detail.isEmpty()) {
//                    title.setError("detail gangguan tidak boleh kosong");
//                    title.requestFocus();
//                }

                Intent in = new Intent(LaporActivity.this, RiwayatActivity.class);
                LaporActivity.this.startActivity(in);
            }
        });
    }



    private void simpanData(final String title, final String detail) {
      //  String url_simpan = helper.main_url + "simpan.php";

        String tag_json = "tag_json";

        pd.setCancelable(false);
        pd.setMessage("Menyimpan...");
        showDialog();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.DATA_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response", response.toString());
                hideDialog();

                try {
                    JSONObject jObject = new JSONObject(response);
                    String title = jObject.getString("title");
                    String detail = jObject.getString("detail");
                    if (detail.equalsIgnoreCase("true")) {
                        Toast.makeText(LaporActivity.this, title, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(LaporActivity.this, title, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(LaporActivity.this, "Error JSON", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("ERROR", error.getMessage());
                Toast.makeText(LaporActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("title", title);
                param.put("detail", detail);
              //  param.put("foto", foto);
                return param;
            }
        };

      //  AppController.getAppController().addToRequestQueue(stringRequest, tag_json);
    }

    private void showDialog() {
        if (!pd.isShowing())
            pd.show();
    }

    private void hideDialog() {
        if (pd.isShowing())
            pd.dismiss();
    }



}
