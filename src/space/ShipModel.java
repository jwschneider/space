package space;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by john on 12/26/16.
 */
public class ShipModel extends JComponent {

    public ShipModel(Ship S) {
        _ship = S;

        _shipM = new Model(new int[5], new int[5], 5);
        _shipA = new Afterburner(new int[3], new int[3], 3, 1);
        _shipA2 = new Afterburner(new int[3], new int[3], 3, .5);
        _shipTS = new TopStab(new int[3], new int[3], 3);
        _shipBS = new BotStab(new int[3], new int[3], 3);

        scale = 10;
    }


    public void paint(Graphics g) {
        Graphics2D _graphics = (Graphics2D) g;


        _graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);


        double speed = Math.sqrt(Math.pow(_ship.getVel()[0], 2) + Math.pow(_ship.getVel()[1], 2));

        _shipM.updateShape(scale);
        _graphics.setPaint(Color.WHITE);
        _graphics.fill(_shipM);

        if (_ship.MAIN_ENGINES) {
            _shipA.updateShape(scale);
            _shipA2.updateShape(scale);
            _graphics.setPaint(Color.RED);
            _graphics.fill(_shipA);
            _graphics.setPaint(Color.ORANGE);
            _graphics.fill(_shipA2);
        }

        if (_ship.TOP_STABILIZERS) {
            _shipTS.updateShape(scale);
            _graphics.setPaint(Color.WHITE);
            _graphics.fill(_shipTS);
        }

        if (_ship.BOTTOM_STABILIZERS) {
            _shipBS.updateShape(scale);
            _graphics.setPaint(Color.WHITE);
            _graphics.fill(_shipBS);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private int scale;

    private Ship _ship;
    private Model _shipM;
    private Afterburner _shipA;
    private Afterburner _shipA2;
    private TopStab _shipTS;
    private BotStab _shipBS;

    public class Afterburner extends Polygon {

        public Afterburner( int[] xArr, int[] yArr, int n, double s) {
            super(xArr, yArr, n);
            rand = new Random();
            scale2 = s;

        }
        private double scale2;
        private Random rand;
        public void updateShape(double SCALE) {
            double r = scale2 * (rand.nextDouble() + 1) / 2;
            super.xpoints[0] = (int) (_ship.getLoc()[0] + (Math.sqrt(4 + Math.pow(r, 2)) * SCALE * Math.cos(_ship.getRot() + Math.PI - (r * Math.atan(.5)))));
            super.xpoints[1] = (int) (_ship.getLoc()[0] + (Math.sqrt(4 + Math.pow(r, 2)) * SCALE * Math.cos(_ship.getRot() + Math.PI + (r * Math.atan(.5)))));
            super.xpoints[2] = (int) (_ship.getLoc()[0] + (Math.sqrt(4 + Math.pow(4 * r, 2)) * SCALE * Math.cos(_ship.getRot() + Math.PI)));

            super.ypoints[0] = (int) (_ship.getLoc()[1] - (Math.sqrt(4 + Math.pow(r, 2)) * SCALE * Math.sin(_ship.getRot() + Math.PI - (r * Math.atan(.5)))));
            super.ypoints[1] = (int) (_ship.getLoc()[1] - (Math.sqrt(4 + Math.pow(r, 2)) * SCALE * Math.sin(_ship.getRot() + Math.PI + (r * Math.atan(.5)))));
            super.ypoints[2] = (int) (_ship.getLoc()[1] - (Math.sqrt(4 + Math.pow(4 * r, 2)) * SCALE * Math.sin(_ship.getRot() + Math.PI)));
        }
    }

    public class TopStab extends Polygon {

        public TopStab( int[] xArr, int[] yArr, int n) {
            super(xArr, yArr, n);
            rand = new Random();
        }

        private Random rand;
        public void updateShape(double SCALE) {
            double r = (rand.nextDouble() + 1) / 8;
            super.xpoints[0] = (int) (_ship.getLoc()[0] + (Math.sqrt(2) * SCALE * Math.cos(_ship.getRot() + (Math.PI * .25))));
            super.xpoints[1] = (int) (_ship.getLoc()[0] + ((Math.sqrt(2) + 1.5* r) * SCALE * Math.cos(_ship.getRot() + (Math.PI * .25))));
            super.xpoints[2] = (int) (_ship.getLoc()[0] + ((Math.sqrt(2) + r) * SCALE * Math.cos(_ship.getRot() + (Math.PI * .25) + r)));

            super.ypoints[0] = (int) (_ship.getLoc()[1] - (Math.sqrt(2) * SCALE * Math.sin(_ship.getRot() + (Math.PI * .25))));
            super.ypoints[1] = (int) (_ship.getLoc()[1] - ((Math.sqrt(2) + 1.5* r) * SCALE * Math.sin(_ship.getRot() + (Math.PI * .25))));
            super.ypoints[2] = (int) (_ship.getLoc()[1] - ((Math.sqrt(2) + r) * SCALE * Math.sin(_ship.getRot() + (Math.PI * .25) + r)));
        }
    }

    public class BotStab extends Polygon {

        public BotStab( int[] xArr, int[] yArr, int n) {
            super(xArr, yArr, n);
            rand = new Random();
        }

        private Random rand;
        public void updateShape(double SCALE) {
            double r = (rand.nextDouble() + 1) / 8;
            super.xpoints[0] = (int) (_ship.getLoc()[0] + (Math.sqrt(2) * SCALE * Math.cos(_ship.getRot() - (Math.PI * .25))));
            super.xpoints[1] = (int) (_ship.getLoc()[0] + ((Math.sqrt(2) + 1.5* r) * SCALE * Math.cos(_ship.getRot() - (Math.PI * .25))));
            super.xpoints[2] = (int) (_ship.getLoc()[0] + ((Math.sqrt(2) + r) * SCALE * Math.cos(_ship.getRot() - (Math.PI * .25) - r)));

            super.ypoints[0] = (int) (_ship.getLoc()[1] - (Math.sqrt(2) * SCALE * Math.sin(_ship.getRot() - (Math.PI * .25))));
            super.ypoints[1] = (int) (_ship.getLoc()[1] - ((Math.sqrt(2) + 1.5 * r) * SCALE * Math.sin(_ship.getRot() - (Math.PI * .25))));
            super.ypoints[2] = (int) (_ship.getLoc()[1] - ((Math.sqrt(2) + r) * SCALE * Math.sin(_ship.getRot() - (Math.PI * .25) - r)));
        }
    }

    public class Model extends Polygon {

        public Model(int[] xArr, int[] yArr, int n) {
            super(xArr, yArr, n);
        }

        public void updateShape(double SCALE) {

            super.xpoints[0] = (int) (_ship.getLoc()[0] + (2 * Math.sqrt(2) * SCALE * Math.cos(_ship.getRot() + (Math.PI * .75))));
            super.xpoints[1] = (int) (_ship.getLoc()[0] + (Math.sqrt(5) * SCALE * Math.cos(_ship.getRot() + Math.PI + Math.atan(.5))));
            super.xpoints[2] = (int) (_ship.getLoc()[0] + (Math.sqrt(5) * SCALE * Math.cos(_ship.getRot() - Math.atan(.5))));
            super.xpoints[3] = (int) (_ship.getLoc()[0] + (Math.sqrt(2) * SCALE * Math.cos(_ship.getRot() + (Math.PI * .25))));
            super.xpoints[4] = (int) (_ship.getLoc()[0] + (Math.sqrt(2) * SCALE * Math.cos(_ship.getRot() + (Math.PI * .75))));

            super.ypoints[0] = (int) (_ship.getLoc()[1] - (2 * Math.sqrt(2) * SCALE * Math.sin(_ship.getRot() + (Math.PI * .75))));
            super.ypoints[1] = (int) (_ship.getLoc()[1] - (Math.sqrt(5) * SCALE * Math.sin(_ship.getRot() + Math.PI + Math.atan(.5))));
            super.ypoints[2] = (int) (_ship.getLoc()[1] - (Math.sqrt(5) * SCALE * Math.sin(_ship.getRot() - Math.atan(.5))));
            super.ypoints[3] = (int) (_ship.getLoc()[1] - (Math.sqrt(2) * SCALE * Math.sin(_ship.getRot() + (Math.PI * .25))));
            super.ypoints[4] = (int) (_ship.getLoc()[1] - (Math.sqrt(2) * SCALE * Math.sin(_ship.getRot() + (Math.PI * .75))));
        }

    }
}
