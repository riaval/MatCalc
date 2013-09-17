package com.riaval.matcalc.view.activity;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.riaval.matcalc.R;
import com.riaval.matcalc.model.Matrix;
import com.riaval.matcalc.model.Storage;

public class InvertibleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_invertible);
		
		double[][] value = null;
		try {
			value = Storage.getA();
		} catch (NullPointerException e) {
			finish();
		}
		
		LayoutInflater inflater = this.getLayoutInflater();
		
		double det = Matrix.getDeterminant(value);
		TextView detTextView = (TextView) findViewById(R.id.invertDet);
		detTextView.setText("Δ = " + String.valueOf( det ));
		if (det == 0) {
			return;
		}
		
		double[][] inverted = Matrix.getInvertible(value);
		TableLayout table = (TableLayout) findViewById(R.id.invertGridLayout);
		table.removeAllViews();
		for(int i=0; i<inverted.length; i++){
			TableRow row = new TableRow(this);
			LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, 10);
			row.setLayoutParams(lp);

			for (int j = 0; j<inverted.length; j++) {
				TextView textView = (TextView) inflater.inflate(R.layout.text_view_result, null);
				textView.setText(String.valueOf(Matrix.round(inverted[i][j], 2)));
				row.addView(textView);
			}
			table.addView(row);
		}
	}
	
}
