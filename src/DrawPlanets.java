import javax.swing.*;
import java.awt.*;

public class DrawPlanets extends JComponent {
   private Ball sun;
   private Ball[] planets;

   public DrawPlanets(Ball sun, Ball[] planets) {
      super();
      setDoubleBuffered(true);
      this.sun = sun;
      this.planets = planets;
   }

   @Override
   protected void paintComponent(Graphics graphics) {
      super.paintComponent(graphics);

      graphics.setColor(Color.black);
      graphics.fillRect(0, 0, getWidth(), getHeight());

      graphics.setColor(sun.color);
      graphics.fillOval((int) (sun.position.x - 12.5), (int) (sun.position.y - 12.5), 25, 25);

      for (int i = 0; i < planets.length; i++) {
         graphics.setColor(planets[i].color);
         graphics.fillOval((int) (planets[i].position.x - 5), (int) (planets[i].position.y - 5), 10, 10);

         graphics.drawString(planets[i].name, (int) planets[i].position.x - 10, (int) planets[i].position.y - 5);

         for (int k = 0; k < planets[i].dotsList.size(); k++) {
            Dot d = planets[i].dotsList.get(k);
            graphics.fillOval(d.x, d.y, 2, 2);
         }
      }
   }
}