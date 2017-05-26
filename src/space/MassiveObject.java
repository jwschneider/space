package space;


/**
 * Created by john on 12/23/16.
 * A MASSIVEOBJECT is simply an object in the game that has mass,
 * and therefore interacts with the other objects in the universe.
 */
public abstract class MassiveObject {

    /**
     * returns whether the body is big enough to exert a gravitational
     * pull on other bodies.
     */
    public boolean isCelestial() {
        return false;
    }

    /**
     * @return the resillience of an object
     */
    public double getResil() {
        return _r;
    }
    /**
     * @return the mass of the object.
     * unis are kg
     */
    public double getMass() {
        return _m;
    }

    /**
     * @return a couplet (x, y) that represents the current location
     * of the center of mass of the object;
     */
    public double[] getLoc() {
        return _p;
    }


    /**
     * @return the information involved in the velocity of the object.
     * returns a double array of the form {dx, dy};
     * units are m / s
     */
    public double[] getVel() {
        return _v;
    }

    /**
     * @return the current acceleration of the object.
     * returns a double array of the form {d2x, d2y};
     * units are m^2 / s
     */
    public double[] getAcc() {
        return _a;
    }

    /**
     * @return the current rotational orientation of the object;
     */
    public double getRot() {
        return _th;
    }

    /**
     * @return the current rotational velocity of the object.
     * units are rad / s
     */
    public double getRotV() {
        return _w;
    }

    /**
     * @return the current rotational accel of the object.
     * units are rad ^2 / s
     */
    public double getRotA() {
        return _al;
    }

    /**
     * applys a force on the object.
     * data comes in the form of {force (N), theta}
     */
    public void applyForce(double[] F) {

        _a[0] = (F[0] / _m) * Math.cos(F[1]);
        _a[1] = (F[0] / _m) * Math.sin(F[1]);

    }

    /**
     * applies torque to the object;
     * data comes in the form of T (N * m);
     */
    public void applyTorque(double T) {

        _al = T / _m;
    }

    /**
     * updates the position and rotational angle of the object
     * as well as the positional and rotational velocities
     * based on its vel, acc, rotV, and rotA, after
     * time T having passed.
     */
    public void update(double T) {

        _v[0] += _a[0] * T;
        _v[1] += _a[1] * T;
        _w += _al * T;

        _p[0] += _v[0] * T;
        _p[1] -= _v[1] * T;
        _th += _w * T;

    }

    public void clearForce() {
        _a[0] = _a[1] = 0;
    }

    public void clearTorque() {
        _al = 0;
    }


    /** resillience of the object for survival of collisions. */
    double _r;

    /** mass of the object. */
    double _m;

    /** current {x, y} position of the object. */
    double[] _p;

    /** current {dx, dy} of the object. */
    double[] _v;

    /** current {d2x, d2y} of the object. */
    double[] _a;

    /** current theta (rotational orientation) of the object. */
    double _th;

    /** current omega (rotational velocity) of the object. */
    double _w;

    /** current alpha (rotatioal acceleration) of the object. */
    double _al;

}

