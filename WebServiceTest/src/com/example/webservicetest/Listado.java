package com.example.webservicetest;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import com.example.webservicetest.MainActivity.RemindTask;
import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.Toast;
import android.util.Log;
import android.view.View;
 
public class Listado extends ListActivity {
 
	Timer timer;
	String[] usuarios;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		timer = new Timer();
		timer.schedule(new RemindTask(), 5000 , 5000);		 

	}
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
//		String selectedValue = (String) getListAdapter().getItem(position);
//		
//		Intent myIntent = new Intent(v.getContext(), Player.class);
//		myIntent.putExtra("seleccion",selectedValue);
//		startActivity(myIntent);
	} 
	
	class RemindTask extends TimerTask {
		@Override
		public void run() {
			Log.v("usuario", "timer");
			receive();						
			mHandler.obtainMessage(1).sendToTarget();
			
		}
	}
	
	public Handler mHandler = new Handler() {
	    public void handleMessage(Message msg) {
	    	setListAdapter(new Adapter(Listado.this, usuarios));
	    }
	};
	

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

//			Log.v("usuario", data.toString());

			usuarios=new String[data.length()];
			
			
			
			for (int i = 0; i < data.length(); i++) {
				JSONObject obj = data.getJSONObject(i);
				String login = obj.getString("login");
				Boolean tieneDeck = obj.getBoolean("tieneDeck");
				Log.v("usuario", login + " , " + tieneDeck);
				usuarios[i]=login;
			}
		
			
		} catch (Exception e) {
			Log.e("Servicio Rest", "Error!", e);
		}

	}
}