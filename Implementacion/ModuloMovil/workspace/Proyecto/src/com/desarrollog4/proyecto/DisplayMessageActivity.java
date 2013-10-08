package com.desarrollog4.proyecto;

import Services.ListaUsuariosWs;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DisplayMessageActivity extends Activity {
	public ListaUsuariosWs lws;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		

//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//            // Show the Up button in the action bar.
//            getActionBar().setDisplayHomeAsUpEnabled(true);
//        }
		
	    // Get the message from the intent
	    Intent intent = getIntent();
	    String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

	    // Create the text view
	    TextView textView = new TextView(this);
	    textView.setTextSize(25);
	    textView.setText("bienvenido: "+message);
	    
	    lws = new ListaUsuariosWs();
		lws.execute();
		
//		setupActionBar();
		setContentView(textView);
	}

//	/**
//	 * Set up the {@link android.app.ActionBar}, if the API is available.
//	 */
//	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
//	private void setupActionBar() {
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//			getActionBar().setDisplayHomeAsUpEnabled(true);
//		}
//	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void sendMessage(View view) {
			
		ListView lv=(ListView) findViewById(R.id.listView1);
		String usuarios[]= lws.obtenerUsuarios();
		

		ArrayAdapter<String> adaptador =
			        new ArrayAdapter<String>(DisplayMessageActivity.this,
			        android.R.layout.simple_list_item_1, usuarios);
			
		lv.setAdapter(adaptador);
			
	}


}
