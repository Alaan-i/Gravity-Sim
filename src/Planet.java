import java.awt.*;

public enum Planet {
    MERCURY("mercury", Color.gray, 0, 50),
    VENUS("venus", Color.lightGray, 0, 100),
    EARTH("earth", Color.blue, 0, 133),
    MARS("mars", Color.red, 0, 166),
    JUPITER("jupiter", Color.yellow, 0, 275),
    SATURN("saturn", Color.orange, 0, 325),
    URANUS("uranus", Color.white, 0, 400),
    NEPTUNE("neptune", Color.CYAN, 0, 450);

    private final String name;
    private final Color color;
    private final double mass;
    private final int distanceFromSun;

    Planet(String name, Color color, double mass, int distanceFromSun) {
        this.name = name;
        this.color = color;
        this.mass = mass;
        this.distanceFromSun = distanceFromSun;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public double getMass() {
        return mass;
    }

    public int getDistanceFromSun(){
        return distanceFromSun;
    }
}