import java.awt.Color;
import java.util.ArrayList;

public class Ball {
   Position position;
   Velocity velocity;
   double mass;
   Color color;
   String name;
   
   ArrayList<Dot> dotsList;
   
   public Ball(double x, double y, double xVelocity, double yVelocity, double mass, Color color, String name) {
      this.position = new Position(x, y);
      this.velocity = new Velocity(xVelocity, yVelocity);
      this.mass = mass;
      this.color = color;
      this.name = name;
      dotsList = new ArrayList<Dot>();
   }
   
   public void updateDotsList() {
      dotsList.add(new Dot((int)position.x, (int)position.y));
      if (dotsList.size() > 1000)
         dotsList.remove(0);
   }
}