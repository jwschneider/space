package space;

/**
 * Created by john on 12/27/16.
 */
public class UpdatePhysics implements Runnable {

    public UpdatePhysics(Universe universe, MassiveObject[] objects, double hz) {
        _objects = objects;
        FRAMERATE = hz;
        U = universe;

    }

    private MassiveObject[] _objects;
    private Universe U;
    private double FRAMERATE;

    @Override
    public void run() {
        for (MassiveObject i : _objects) {
            i.update(1 / FRAMERATE, U.getWidth());
        }
    }
}
