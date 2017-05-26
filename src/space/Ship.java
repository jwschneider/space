package space;

import java.util.Formatter;

/**
 * Created by john on 12/23/16.
 */
public class Ship extends MassiveObject {

    public Ship(double[] pos) {

        _p = pos;
        _r = 100;
        _m = 100;
        _v = new double[]{0, 0};
        _a = new double[] {0, 0};
        _th = 0;
        _w = 0;
        _al = 0;
        THRUST = 10000;
        STABILIZERS = 100;
        MAIN_ENGINES = TOP_STABILIZERS = BOTTOM_STABILIZERS = false;
    }

    /**
     * If dir is positive, rotates the ship counterclockwise;
     * otherwise rotates clockwise RAD radians.
     * @param dir either pos or neg 1 for direction
     * @param rad amount of radians to rotate
     */
    public void rotate(int dir, double rad) {
        _th += (rad * dir);
    }

    public void print() {

        Formatter out = new Formatter();
        out.format("Ship's position is %s, %s.%n", getLoc()[0], getLoc()[1]);
        out.format("Ship's veolcity is %s px/s in dir x and %s px/s in dir y.%n", getVel()[0], getVel()[1]);
        out.format("Ship's current rotation is %spi radians", getRot() / Math.PI);
        System.out.println(out.toString());
    }



    public void MainEngines() {
        double[] force = new double[2];
        force[0] = THRUST;
        force[1] = getRot();
        applyForce(force);
    }

    public void Stabilizers(int dir) {
        applyTorque(dir * STABILIZERS);
    }

    private static double THRUST;
    private static double STABILIZERS;


    boolean MAIN_ENGINES;
    boolean TOP_STABILIZERS;
    boolean BOTTOM_STABILIZERS;
}
