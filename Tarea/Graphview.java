import org.math.plot.*;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import org.math.plot.plotObjects.*;

public class Graphview extends Plot2DPanel{ 
	private MyWorld world;
	private ArrayList<Double> ME;
	private ArrayList<Double> KE;
	private ArrayList<Double> PE;
	private ArrayList<Double> TIME;
	private BaseLabel title;
	private int choice;
	
	 public Graphview(MyWorld w, int pick){
		 world = w;
		 choice = pick;
		 if(choice == 1){
			 title = new BaseLabel("Energía Cinética",Color.BLACK, 0.5, 1.1);			 
		 }
		 if(choice == 2){
			 title = new BaseLabel("Energía Potencial",Color.BLACK, 0.5, 1.1);			 
		 }
		 if(choice == 3){
			 title = new BaseLabel("Energía Mecánica", Color.BLACK, 0.5, 1.1);			 
		 }
		 this.addPlotable(title);
	     title.setFont(new Font("Courier", Font.BOLD, 20));
	     this.setAxisLabels("Time","Energy");
	     ME = new ArrayList<Double>();
	     KE = new ArrayList<Double>();
	     PE = new ArrayList<Double>();
	     TIME = new ArrayList<Double>();
	 }

	 public void repaintView(double t){
		  TIME.add(t);
		  ME.add(world.getME());
		  PE.add(world.getPE());
		  KE.add(world.getKE());
		  double[] MEA = new double[ME.size()];
		  double[] KEA = new double[KE.size()];
		  double[] PEA = new double[PE.size()];
		  double[] TIMEA = new double[TIME.size()];
		  for(int i = 0; i < MEA.length; i++){
			     MEA[i] = ME.get(i);
			     KEA[i] = KE.get(i);
			     PEA[i] = PE.get(i);
			     TIMEA[i] = TIME.get(i);
		  }
		  
		  if(choice == 1) this.addScatterPlot("my plot",Color.RED,TIMEA,KEA);
		  if(choice == 2) this.addScatterPlot("my plot",Color.RED,TIMEA,PEA);
		  if(choice == 3) this.addScatterPlot("my plot",Color.RED,TIMEA,MEA);		  
	 }
}