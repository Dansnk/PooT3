import java.awt.event.*; 
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class LabMenuListener implements ActionListener {
   private MyWorld  world;
   public LabMenuListener (MyWorld  w){
      world = w;
   }
   public void actionPerformed(ActionEvent e) {
      JMenuItem menuItem = (JMenuItem)(e.getSource());
      String text = menuItem.getText();
      
      // Actions associated to main manu options
      if (text.equals("My scenario")) {  // here you define Etapa2's configuration
       // to be coded
         double mass = 1.0;      // 1 [kg] 
         double radius = 0.1;    // 10 [cm] 
         double position = 1.0;  // 1 [m] 
         double speed = 0.0;     // 0.5 [m/s]
         Ball b = new Ball(mass, radius, position, speed);
         FixedHook h = new FixedHook(0.2);
         Spring s = new Spring (0.5, 5);
         s.attachAend(h);
         s.attachBend(b);
         world.addElement(b);
         world.addElement(h);
         world.addElement(s);
      }
      if (text.equals("Ball")) 
        world.addElement(new Ball(1.0, 0.1, 1.2, 0));
      if (text.equals("Fixed Hook"))  
        world.addElement(new FixedHook(0.5));
      if (text.equals("Spring")) 
        world.addElement(new Spring(0.5, 5));
      if (text.equals("Oscillator")) 
        world.addElement(new Oscillator(0.5,0.1,0.5));

      // Actions associated to MyWorld submenu
      if (text.equals("Start"))   /* to be coded */
        world.start();
      
      if (text.equals("Stop"))    /* to be coded */
        world.stop();
        
      if (text.equals("Delta time")) {
         String data = JOptionPane.showInputDialog("Enter delta t [s]");
         world.setDelta_t(Double.parseDouble(data));
      }
      if (text.equals("View Refresh time")) {
         // to be coded
         String data = JOptionPane.showInputDialog("Enter view refresh time [s]");
         world.setRefreshPeriod(Double.parseDouble(data));
      }
   }
}