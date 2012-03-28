package com.mediplus.presentation;



import java.util.ArrayList;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphView.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;
import com.mediplus.core.MedicalChartManager;
import com.mediplus.entity.ElementData;
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
		drawGraph();
			

	}
	
	public void drawGraph(){
		ArrayList<ElementData> graph= MedicalChartManager.getMedicalChartManager().getPlotData();
		//ArrayList<GraphViewData> graphData=new ArrayList<GraphView.GraphViewData>();
		int ct=1;
		GraphViewData[] graphData=new GraphViewData[graph.size()];
		for (ElementData e : graph) {
			
			
			graphData[ct-1]=new GraphViewData(ct, e.getValue());
			ct+=1;
			
		}
		GraphViewSeries exampleSeries = new GraphViewSeries(graphData);  
			  
			GraphView graphView = new LineGraphView(  
			      this // context  
			      , MedicalChartManager.getMedicalChartManager().getCurrentChart() // heading  
			);  
			graphView.addSeries(exampleSeries); // data  
			  
			LinearLayout layout = new LinearLayout(this.getApplicationContext());  
			layout.addView(graphView); 
			setContentView(layout);		
	}
	
	
	
}
