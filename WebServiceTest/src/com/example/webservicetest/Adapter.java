package com.example.webservicetest;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class Adapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;
 
	public Adapter(Context context, String[] values) {
		super(context, R.layout.listado, values);
		this.context = context;
		this.values = values;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.listado, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
		textView.setText(values[position]);
 
		String s = values[position];
 
//		if (s.equals("cancion1")) {
//			imageView.setImageResource(R.drawable.cancion1);
//		} else if (s.equals("cancion2")) {
//			imageView.setImageResource(R.drawable.cancion2);
//		} else if (s.equals("cancion3")) {
//			imageView.setImageResource(R.drawable.cancion3);			
//		}
 
		return rowView;
	}
}