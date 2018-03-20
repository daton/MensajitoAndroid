package org.unitec.mensajitoandroid;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new TareaBuscarmensajitos().execute();


    }

    public class TareaBuscarmensajitos extends AsyncTask<Void, Void, Mensajito[]> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Mensajito[] mensajitos) {
            super.onPostExecute(mensajitos);

            Toast.makeText(getApplicationContext(), "Mensajitos encontrados "+mensajitos.length,Toast.LENGTH_LONG).show();
        }

        @Override
        protected Mensajito[] doInBackground(Void... voids) {
            try {
                final String url = "https://jc-unitec.herokuapp.com/api/mensajito";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Mensajito[] mensajitos = restTemplate.getForObject(url, Mensajito[].class);
                return mensajitos;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }
    }
}
