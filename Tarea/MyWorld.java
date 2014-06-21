import java.util.*;
import java.io.*;
import java.net.URL;

import javax.swing.Timer;

import java.applet.AudioClip;
import java.awt.event.*;

public class MyWorld implements ActionListener {
   private PrintStream out;
   
   private ArrayList<PhysicsElement> elements;  // array to hold everything in my world.
   private MyWorldView view;   // NEW
   private Graphview graph1;
   private Graphview graph2;
   private Graphview graph3;
   private Timer passingTime;   // NEW
   private double t;        // simulation time
   private double delta_t;        // in seconds
   private double refreshPeriod;  // in seconds
   private AudioClip clipConnection;
   
   public MyWorld(){
      this(System.out);  // delta_t= 0.1[ms] and refreshPeriod=200 [ms]
   }
   public MyWorld(PrintStream output){
      out = output;
      t = 0;
      refreshPeriod = 0.03;      // 60 [ms]
      delta_t = 0.00001;          // 0.01 [ms]
      elements = new ArrayList<PhysicsElement>();
      view = null;
      passingTime = new Timer((int)(refreshPeriod*1000), this); 
      
      URL sound = this.getClass().getClassLoader().getResource("menuhit.wav");
	  
	  clipConnection = java.applet.Applet.newAudioClip(sound);
	  
      
   }

   public void addElement(PhysicsElement e) {
      elements.add(e);
      view.repaintView();
   }
   public void setView(MyWorldView view, Graphview graph1, Graphview graph2, Graphview graph3) {
      this.view = view;
      this.graph1 = graph1;
      this.graph2 = graph2;
      this.graph3 = graph3;
   }
   public void setDelta_t(double delta) {
      delta_t = delta;
   }
   public void setRefreshPeriod (double rp) {
      refreshPeriod = rp;
      passingTime.setDelay((int)(refreshPeriod*1000)); // convert from [s] to [ms]
   }
   public void start() {
      if(passingTime.isRunning()) return;
      passingTime.start();
      view.desableMouseListener();    
   }
   public void stop(){
      passingTime.stop();
      view.enableMouseListener();
   }
   public double getME(){
	   double ME = 0;
	   double E = 0;
	   for(PhysicsElement e: elements){
		   if(e instanceof Spring){
			   E = (1/2)*((Spring)e).getStiffness()*((Spring)e).getStretch()*((Spring)e).getStretch();
		   }
		   if(e instanceof Ball){
			   E = (1/2)*((Ball)e).getSpeed()*((Ball)e).getSpeed()*((Ball)e).getMass();
		   }
		   ME = E + ME;
	   }
	   return ME;
   }
   
   public double getKE(){
	   double KE = 0;
	   for(PhysicsElement e: elements){
		   if(e instanceof Ball){
			   KE = (1/2)*((Ball)e).getSpeed()*((Ball)e).getSpeed()*((Ball)e).getMass() + KE;
		   }
	   }
	   return KE;
   }
   
   public double getPE(){
	   double PE = 1;
	   for(PhysicsElement e: elements){
		   if(e instanceof Spring){
			   PE = (1/2)*((Spring)e).getStiffness()*((Spring)e).getStretch()*((Spring)e).getStretch() + PE;
			   System.out.println("PEEE" +PE);
		   }
	   }
	   return PE;
   }
   public void actionPerformed (ActionEvent event) {  // like simulate method of Assignment 1, 
      double nextStop=t+refreshPeriod;                // the arguments are attributes here.
      for (; t<nextStop; t+=delta_t){
         for (PhysicsElement e: elements)
            if (e instanceof Simulateable) {
               Simulateable s = (Simulateable) e;
               s.computeNextState(delta_t,this); // compute each element next state based on current global state
            }
         for (PhysicsElement e: elements)  // for each element update its state. 
            if (e instanceof Simulateable) {
               Simulateable s = (Simulateable) e;
               s.updateState();            // update its state
            }
      }
      repaintView();
   }
   
   public void repaintView(){
      view.repaintView();
      graph1.repaintView(t);
      graph2.repaintView(t);
      graph3.repaintView(t);
   }

   public Ball findCollidingBall(Ball me) {
      for (PhysicsElement e: elements)
         if ( e instanceof Ball) {
            Ball b = (Ball) e;
            if ((b!=me) && b.collide(me)) 
            {
            	clipConnection.play();
            	return b;
            	}
         }
      return null;
   }
 
   public ArrayList<PhysicsElement> getPhysicsElements(){
      return elements;
   }
   
   public PhysicsElement find(double x, double y) {
      for (PhysicsElement e: elements)
            if (e.contains(x,y)) return e;
      return null;
   }  
   public PhysicsElement findNext(PhysicsElement element, double x, double y) {
      for (int i = elements.indexOf(element)+1; i< elements.size(); i++) { // find
          if (elements.get(i).contains(x,y))    // next element in that position ahead in array
            return elements.get(i);
      }
      for (PhysicsElement e: elements)   // There was no element in that position ahead in array
          if (e.contains(x,y)) return e; // search for an element in that position from begining.
      return element;
   }  
   public SpringAttachable findAttachableElement(double x) {
      for (PhysicsElement e: elements)
         if (e instanceof SpringAttachable)
            if (e.contains(x,0)) return (SpringAttachable)e;
      return null;
   }  
} 

