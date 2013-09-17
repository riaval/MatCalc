package com.riaval.matcalc.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.riaval.matcalc.R;
import com.riaval.matcalc.model.Matrix;
import com.riaval.matcalc.model.Storage;

public class ResultActivity extends Activity {
	
	private double det;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		double[][] value = null;
		double[] answer = null;
		try {
			value = Storage.getA();
			answer = Storage.getB();
		} catch (NullPointerException e) {
			finish();
		}

		LinearLayout rootView = (LinearLayout) findViewById(R.id.resultLayout);
		LayoutInflater inflater = this.getLayoutInflater();
		TextView textView = (TextView) inflater.inflate(R.layout.text_view_result, null);
		det = Matrix.getDeterminant(value);
		textView.setText("Δ = " + String.valueOf(det));
		rootView.addView(textView);
		rootView.addView(new TextView(this));
		
		if (det == 0) {
			return;
		} 
		
		int length = answer.length;
		double[] swapedAnswers = new double[length];
		for (int i = 0; i < length; i++) {
			double[][] newArr = new double[length][length];
			for (int j = 0; j < length; j++) {
				for (int k = 0; k < length; k++) {
					if (k == i) {
						newArr[j][k] = answer[j];
					} else {
						newArr[j][k] = value[j][k];
					}
				}
			}
			TextView textVarView = (TextView) inflater.inflate(R.layout.text_view_result, null);
			double xDet = Matrix.round(Matrix.getDeterminant(newArr), 2);
			swapedAnswers[i] = Matrix.round((xDet/det), 2);
			textVarView.setText(Html.fromHtml(
					"Δx<sub><small><small>" + 
					String.valueOf(i+1) + 
					"</small></small></sub> = " + 
					String.valueOf(xDet))
			);
			rootView.addView(textVarView);
		}
		rootView.addView(new TextView(this));
		for (int i = 0; i < length; i++) {
			TextView textVarView = (TextView) inflater.inflate(R.layout.text_view_result, null);
			textVarView.setText(Html.fromHtml(
					"x<sub><small><small>" + 
					String.valueOf(i+1) + 
					"</small></small></sub> = " + 
					String.valueOf(swapedAnswers[i]))
			);
			rootView.addView(textVarView);
		}
		
	}

}
