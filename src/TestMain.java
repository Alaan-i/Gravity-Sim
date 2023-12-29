import javax.swing.*;
import java.awt.*;

public class TestMain extends JFrame {
   private boolean isRunning = true;

   private Ball sun;
   private Ball[] planets;
   private DrawPlanets drawPlanets;

   public TestMain() {
      super("Gravity");

      sun = new Ball(
         ScreenDimensions.width / 2,
         ScreenDimensions.height / 2 - 50,
         0,
         0,
         80,
         Color.orange,
         "sun"
      );
      planets = new Ball[Planet.values().length];
      drawPlanets = new DrawPlanets(sun, planets);

      CreatePlanets();
      SetUpGUI();

      StartSimulation();
   }

   private void CreatePlanets() {
      for (Planet planet : Planet.values()) {
         int index = planet.ordinal();
         double spacing = planet.getDistanceFromSun();
         double initialVelocity = Math.sqrt((Constants.GRAVITY * sun.mass) / spacing);
         Color color = planet.getColor();
         planets[index] = new Ball(
            sun.position.x - spacing,
            sun.position.y,
            0,
            initialVelocity,
            0.1,
            color,
            planet.name().toLowerCase()
         );
      }
   }

   private void SetUpGUI() {
      setLayout(new FlowLayout());

      setSize(
         ScreenDimensions.width,
         ScreenDimensions.height
      );
      setLocation(
         ScreenDimensions.width / 2 - getSize().width / 2,
         ScreenDimensions.height / 2 - getSize().height / 2
      );
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      setVisible(true);

      drawPlanets.setPreferredSize(
         new Dimension(
            ScreenDimensions.width,
            ScreenDimensions.height
      ));
      drawPlanets.setMinimumSize(
         new Dimension(
            600, 
            600
      ));
      drawPlanets.setMaximumSize(
         new Dimension(
            ScreenDimensions.width / 2 - getSize().width / 2,
            ScreenDimensions.height / 2 - getSize().height / 2
      ));

      add(drawPlanets);
      revalidate();
   }

   public void StartSimulation() {
      double lastUpdate = System.nanoTime();
      double updateDuration =  System.nanoTime()- lastUpdate;
      double time;
      double distance, deltaY, theta, accel, xAccel, yAccel;

      while(true)
      {
         try {
            Thread.sleep(1);
         } 
         catch (Exception exc) {
         }
         
         if(isRunning) {                      
            lastUpdate = System.nanoTime();
            time = updateDuration * 0.000000001;

            for(int i = 0; i < Planet.values().length; i++) {

               //distance using pythagorean theorem
               distance = Math.sqrt(Math.pow(planets[i].position.x - sun.position.x,2)+Math.pow(planets[i].position.y - sun.position.y,2));
               deltaY = Math.abs(planets[i].position.y - sun.position.y);
               theta = Math.asin(deltaY/distance);
               //acceleration due to gravity, changes based on distance from body
               accel = Constants.GRAVITY*sun.mass/Math.pow(distance,2);

               if (distance < 20) {
                  xAccel = 0;
                  yAccel = 0;
               } else {

                  if(planets[i].position.x <= sun.position.x) {
                     xAccel = accel * Math.cos(theta);
                  } else {
                     xAccel = -accel * Math.cos(theta);
                  }

                  if(planets[i].position.y <= sun.position.y) {
                     yAccel = accel * Math.sin(theta);
                  } else {
                     yAccel = -accel * Math.sin(theta);
                  }
               }

               planets[i].velocity.x = planets[i].velocity.x+(xAccel*time);
               planets[i].velocity.y = planets[i].velocity.y+(yAccel*time);
               planets[i].position.x = planets[i].position.x + planets[i].velocity.x*time;
               planets[i].position.y = planets[i].position.y + planets[i].velocity.y*time;
               planets[i].updateDotsList();
            }
         }
         drawPlanets.repaint();
         updateDuration = System.nanoTime() - lastUpdate;
      }
   }  
   public static void main(String[] args) {
      new TestMain();
   }
}