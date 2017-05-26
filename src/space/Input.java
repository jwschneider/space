package space;

/**
 * Created by john on 1/8/17.
 */
public class Input implements Runnable {

    public Input(Ship S, double hz) {
        _s = S;
        FRAMERATE = hz;
    }

    private Universe _u;
    private Ship _s;
    private double FRAMERATE;

    public void run() {
        if (_s.MAIN_ENGINES) {
            _s.MainEngines();
        }
        else {
            _s.clearForce();
        }
        if (_s.TOP_STABILIZERS) {
            _s.Stabilizers(-1);
        }
        else if (_s.BOTTOM_STABILIZERS) {
            _s.Stabilizers(1);
        }
        else {
            _s.clearTorque();
        }
    }
}
