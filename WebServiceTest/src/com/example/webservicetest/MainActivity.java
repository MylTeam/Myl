package com.example.webservicetest;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {
	
	GPSTracker gps;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_main);
		
		gps=new GPSTracker(MainActivity.this);
		final double latitude = gps.getLatitude();
        final double longitude = gps.getLongitude();
        
		Thread thread = new Thread(new Runnable(){
		    @Override
		    public void run() {		        
		
		try {
			send(latitude,longitude);
			
			
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
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void send(double latitude,double longitude) throws JSONException, ClientProtocolException, IOException{
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://192.168.226.221:8081/myl/rest/msgPost");
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

}
