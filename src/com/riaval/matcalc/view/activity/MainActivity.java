package com.riaval.matcalc.view.activity;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.riaval.matcalc.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TableLayout table = (TableLayout) findViewById(R.id.mainGridLayout);
//		gridview.setColumnCount(3);
		
		
		for(int i=0; i<10; i++){
			TableRow row = new TableRow(this);
			LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, 10);
			row.setLayoutParams(lp);

			for (int j = 0; j<10; j++) {
				EditText edit = (EditText) LayoutInflater.from(this).inflate(R.layout.text_view_inner, null);
				row.addView(edit);
			}
			table.addView(row);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.action_edit:
	            return true;
	        case R.id.action_result:
	        	Intent intent = new Intent(this, ResultActivity.class);
				startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

}
