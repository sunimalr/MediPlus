package com.mediplus.presentation;



import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphView.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;
//import com.jjoe64.graphview.GraphView.GraphViewSeries;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

public class Graph extends Activity{
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {  
			      new GraphViewData(1, 2.0d)  
			      , new GraphViewData(2, 1.5d)  
			      , new GraphViewData(3, 2.5d)  
			      , new GraphViewData(4, 1.0d)  
			});  
			  
			GraphView graphView = new LineGraphView(  
			      this // context  
			      , "GraphViewDemo" // heading  
			);  
			graphView.addSeries(exampleSeries); // data  
			  
			LinearLayout layout = new LinearLayout(this.getApplicationContext());  
			layout.addView(graphView); 
			setContentView(layout);
			

	}
	
}
