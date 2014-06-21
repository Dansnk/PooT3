import java.awt.Dimension;

import javax.swing.*;

public class PhysicsLabApplet extends JApplet
{  
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JMenuBar createLabMenuBar(LabMenuListener menu_l) {
	      JMenuBar mb = new JMenuBar();
	      
	      JMenu menu = new JMenu ("Configuration");
	      mb.add(menu);
	      JMenu subMenu = new JMenu("Insert");  
	      menu.add(subMenu);
	      
	      JMenuItem menuItem = new JMenuItem("Ball");
	      menuItem.addActionListener(menu_l);
	      subMenu.add(menuItem);
	      menuItem = new JMenuItem("Fixed Hook");
	      menuItem.addActionListener(menu_l);
	      subMenu.add(menuItem);
	      menuItem = new JMenuItem("Spring");
	      menuItem.addActionListener(menu_l);
	      subMenu.add(menuItem);
	      menuItem = new JMenuItem("Oscillator");
	      menuItem.addActionListener(menu_l);
	      subMenu.add(menuItem);
	      menuItem = new JMenuItem("My scenario");
	      menuItem.addActionListener(menu_l);
	      subMenu.add(menuItem);
	  
	      menu = new JMenu("MyWorld");
	      mb.add(menu);
	      menuItem = new JMenuItem("Start");
	      menuItem.addActionListener(menu_l);
	      menu.add(menuItem);
	      menuItem = new JMenuItem("Stop");
	      menuItem.addActionListener(menu_l);
	      menu.add(menuItem);
	      subMenu = new JMenu("Simulator");
	      menuItem = new JMenuItem("Delta time");
	      menuItem.addActionListener(menu_l);
	      subMenu.add(menuItem);
	      menuItem = new JMenuItem("View Refresh time");
	      menuItem.addActionListener(menu_l);
	      subMenu.add(menuItem);
	      menu.add(subMenu);      
	      return mb;          
	   }   
	
	
	
	public static int ball_1;
	/*public static String am;
	public static int fixedHookNum;
	public static int fixedHook_1;
	public static int ballNum;
	public static int fixedHookNum;
	public static int fixedHookNum;
	public static int fixedHookNum;
	public static int fixedHookNum;
	public static int fixedHookNum;
	public static int fixedHookNum;
	*/
		public static String[] palabrasSeparadas;
    //Called by init.
    protected void loadAppletParameters() {
        //Get the applet parameters.
        loadAppletParameters(); 
	   	String at = getParameter("ball.1");
	   	String delimitadores= "[ .,;?!\'\"\\[\\]]+";
	   	String[] palabrasSeparadas = at.split(delimitadores);
	   //Float.valueOf(palabrasSeparadas[0]
        
       
        //System.out.println("primera: "+)
    	
       
        /*String  at = getParameter("title");
        String at = getParameter("fixedHookNum");
        String at = getParameter("fixedHook.1");
        String at = getParameter("ballNum");
        
        String at = getParameter("ball.2");
        String at = getParameter("ball.3");
        String at = getParameter("oscillatorNum");
        String at = getParameter("oscillator.1");
        String at = getParameter("deltaTime");
        String at = getParameter("refreshTime");
        String at = getParameter("maxPlotTime");
       */
    }
	
	
   public void init()
   {  
	   
	   	
	   MyWorld world = new MyWorld();
	      Graphview graph1 = new Graphview(world, 1);
	      Graphview graph2 = new Graphview(world, 2);
	      Graphview graph3 = new Graphview(world, 3);
	      MyWorldView  worldView = new MyWorldView(world);
	      world.setView(worldView, graph1, graph2, graph3);
	      /*
	      world.setDelta_t(Double.parseDouble(getParameter("deltaTime")));
	      world.setRefreshPeriod (Double.parseDouble(getParameter("refreshTime"))); 
	      world.setPlotMaxTime(Double.parseDouble(getParameter("maxPlotTime")));
	      */
	      JSplitPane splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,worldView,graph3);
	      JSplitPane splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,graph1,graph2);
	      JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,splitPane1,splitPane2);
	      splitPane1.setDividerLocation(0.5);
	      splitPane2.setDividerLocation(0.5);
	      splitPane.setDividerLocation(0.5);
	      splitPane1.setPreferredSize(new Dimension(1000,500));
	      splitPane1.setPreferredSize(new Dimension(1000,500));
	      splitPane.setPreferredSize(new Dimension(1000,1000));
	      add(splitPane);
	      
	      LabMenuListener menuListener = new LabMenuListener(world);
	     setJMenuBar(createLabMenuBar(menuListener));
   }
   public void start()
   {
	   
	   
   }
   
}
