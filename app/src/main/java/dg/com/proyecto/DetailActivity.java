package dg.com.proyecto;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DetailActivity extends AppCompatActivity {

    TextView temp_tv, pressure_tv, humidity_tv, name_tv;
    ImageView imgvw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgvw = (ImageView) findViewById(R.id.iv_image);
        name_tv = (TextView) findViewById(R.id.tv_name);
        temp_tv = (TextView) findViewById(R.id.tv_temperature);
        humidity_tv = (TextView) findViewById(R.id.tv_humidity);
        pressure_tv = (TextView) findViewById(R.id.tv_pressure);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        Intent i = getIntent();
        final String name = i.getExtras().getString("name");
        final int image = i.getExtras().getInt("image");

        imgvw.setImageResource(image);
        name_tv.setText(name);


        FetchData process = new FetchData();
        process.execute("http://213.99.97.28:3389/getdata.php");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class FetchData extends AsyncTask<String, String, String> {

        HttpURLConnection connection = null;
        BufferedReader reader = null;

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuilder buffer = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                JSONObject object = new JSONObject(result);
                JSONArray jsonarray = object.getJSONArray("Data");
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    temp_tv.setText("Temperature:  " + jsonobject.getString("temperature") + " ºC");
                    humidity_tv.setText("Humidity:  " + jsonobject.getString("pressure") + " %");
                    pressure_tv.setText("Pressure:  " + jsonobject.getString("humidity") + " mPa");
                }

            } catch (JSONException e)

            {
                e.printStackTrace();
            }
        }
    }
}