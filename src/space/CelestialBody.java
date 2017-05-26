package space;

/**
 * Created by john on 12/23/16.
 * A celestial body is something massive enough that it exerts a constant gravitational force
 * on everything close to it.
 */
public abstract class CelestialBody extends MassiveObject {


    /** The universal gravitational constant. */
    private double G_CONSTANT = (6.674 * (Math.pow(10, -11)));


    /**
     * @return that this body is large enough to exert a force upon
     * nearby objects.
     */
    @Override
    public boolean isCelestial() {
        return true;
    }

    /**
     * @param P the Position of the second object.
     * @param M the Mass of the second object.
     * @return the Force pair {force (N), angle (rad)} that
     * this celestial body exerts on the second object.
     */
    public double[] gForce(double[] P, double M) {
        double dx = Math.sqrt(getLoc()[0] - P[0]);
        double dy = Math.sqrt(getLoc()[1] - P[1]);
        double dist = Math.sqrt(
                Math.pow(dx, 2) + Math.pow(dy, 2));
        double force = (G_CONSTANT * M * getMass()) / dist;
        double angle = Math.atan(dy / dx);
        return new double[]{force, angle};
    }


}
