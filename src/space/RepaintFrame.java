package space;

import java.awt.*;

/**
 * Created by john on 12/26/16.
 */
public class RepaintFrame implements Runnable {

    Universe _uni;
    private ShipModel _s;
    private Main _m;

    public RepaintFrame(Main M) {
        _m = M;
    }

    @Override
    public void run() {
        _m.centerView();
        _m.repaint();
    }

}
