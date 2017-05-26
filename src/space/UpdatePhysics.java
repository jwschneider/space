package space;

/**
 * Created by john on 12/27/16.
 */
public class UpdatePhysics implements Runnable {

    public UpdatePhysics(MassiveObject[] objects, double hz) {
        _objects = objects;
        FRAMERATE = hz;

    }

    private MassiveObject[] _objects;

    private double FRAMERATE;

    @Override
    public void run() {
        for (MassiveObject i : _objects) {
            i.update(1 / FRAMERATE);
        }
    }
}
