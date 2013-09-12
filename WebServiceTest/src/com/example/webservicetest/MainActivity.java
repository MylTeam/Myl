package com.example.webservicetest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	GPSTracker gps;
	Button check;
	Timer timer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		check = (Button) findViewById(R.id.check);
		gps = new GPSTracker(MainActivity.this);
		final double latitude = gps.getLatitude();
		final double longitude = gps.getLongitude();

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {

				try {
					send(latitude, longitude);

				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				catch (Exception e) {
					e.printStackTrace();
				}
			}

		});

		thread.start();

		check.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
//				timer = new Timer();
//				timer.schedule(new RemindTask(), 5000);
				Intent myIntent = new Intent(v.getContext(), Listado.class);
				startActivity(myIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void send(double latitude, double longitude) throws JSONException,
			ClientProtocolException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(
				"http://192.168.226.197:8081/myl/rest/msgPost");
		post.setHeader("content-type", "application/json");

		JSONObject exp = new JSONObject();
		exp.put("id", "1");
		exp.put("nombre", "carlos");
		exp.put("apellido", "santiago");
		exp.put("latitude", latitude);
		exp.put("longitude", longitude);

		Log.v("datos", exp.toString());
		StringEntity entity = new StringEntity(exp.toString());
		post.setEntity(entity);
		HttpResponse resp = httpClient.execute(post);
		String respStr = EntityUtils.toString(resp.getEntity());
		Log.v("envio", respStr);
	}

	class RemindTask extends TimerTask {
		@Override
		public void run() {
			receive();
		}
	}

	public void receive() {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet getData = new HttpGet(
				"http://192.168.226.197:8081/myl/rest/msgGet");
		getData.setHeader("content-type", "application/json");
		try {
			HttpResponse resp = httpClient.execute(getData);
			String respStr = EntityUtils.toString(resp.getEntity());
			JSONObject jsonResponse = new JSONObject(new String(
					respStr.getBytes("ISO-8859-1"), "UTF-8"));
			JSONArray data = jsonResponse.getJSONArray("usuario");

			Log.v("usuario", data.toString());

			for (int i = 0; i < data.length(); i++) {
				JSONObject obj = data.getJSONObject(i);
				String login = obj.getString("login");
				Boolean tieneDeck = obj.getBoolean("tieneDeck");
				Log.v("usuario", login + " , " + tieneDeck);
			}

		} catch (Exception e) {
			Log.e("Servicio Rest", "Error!", e);
		}

	}

}
