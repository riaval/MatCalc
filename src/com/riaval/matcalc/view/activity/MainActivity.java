package com.riaval.matcalc.view.activity;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.riaval.matcalc.R;
import com.riaval.matcalc.model.Storage;

public class MainActivity extends Activity {

	private static int sFildSize = 3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		drow();
	}
	
	private void drow(){
		TableLayout table = (TableLayout) findViewById(R.id.mainGridLayout);
		table.removeAllViews();
		
		for(int i=0; i<sFildSize; i++){
			TableRow row = new TableRow(this);
			LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, 10);
			row.setLayoutParams(lp);

			for (int j = 0; j<sFildSize+1; j++) {
				EditText edit = (EditText) LayoutInflater.from(this).inflate(R.layout.text_view_inner, null);
				if (j == sFildSize){
					edit.setGravity(Gravity.RIGHT);
				}
				row.addView(edit);
			}
			table.addView(row);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = null;
	    switch (item.getItemId()) {
	        case R.id.action_edit:
	        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
			    LayoutInflater inflater = this.getLayoutInflater();
			    LinearLayout root = (LinearLayout) inflater.inflate(R.layout.number_picker_edit, null);
			    final NumberPicker numberPicker = (NumberPicker) root.findViewById(R.id.numberPicker);
			    numberPicker.setMaxValue(6);
			    numberPicker.setMinValue(2);
			    
			    builder.setView(root); 
			    builder.setTitle("Choose dimension");
				builder.setPositiveButton("OK",
	                    new DialogInterface.OnClickListener() {
	                        @Override
	                        public void onClick(DialogInterface dialog, int index) {
	                        	sFildSize = numberPicker.getValue();
	                        	drow();
	                            dialog.dismiss();
	                        }
	                    });
			    
			    AlertDialog dialog = builder.create();
			    dialog.show();
	            return true;
	        case R.id.action_result:
	        	prepareValues();	        	
	        	intent = new Intent(this, ResultActivity.class);
				startActivity(intent);
	            return true;
	        case R.id.action_rank:
	        	prepareValues();	        	
	        	intent = new Intent(this, InvertibleActivity.class);
				startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	private void prepareValues(){
		TableLayout table = (TableLayout) findViewById(R.id.mainGridLayout);
    	double[][] resultArray = new double[sFildSize][sFildSize];
    	for (int i = 0; i < sFildSize; i++) {
    		TableRow row = (TableRow) table.getChildAt(i);
    		for (int j = 0; j < sFildSize; j++) {
    			EditText edit = (EditText) row.getChildAt(j);
    			double value;
    			try {
    				value = Double.parseDouble(edit.getText().toString());
				} catch (NumberFormatException e) {
					value = 0;
				}
    			
    			resultArray[i][j] = value;
			}
		}
    	Storage.setA(resultArray);
    	
    	double[] answer = new double[sFildSize];
    	for (int i = 0; i < sFildSize; i++) {
    		TableRow row = (TableRow) table.getChildAt(i);
    		EditText edit = (EditText) row.getChildAt(sFildSize);
    		try {
//    			System.out.println(edit.getText().toString());
    			answer[i] = Double.parseDouble(edit.getText().toString());
			} catch (NumberFormatException e) {
				answer[i] = 0;
			}
    	}
    	Storage.setB(answer);
	}

}
