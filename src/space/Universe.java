package space;


import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by john on 12/26/16.
 */

public class Universe extends JPanel {

    public Universe() {
        setSize(Main.boardWidth * 100, Main.boardHeight * 100);
        setBackground(Color.BLACK);
        setVisible(true);
    }

    public void createShip() {
        ship = new Ship(new double[]{Main.boardWidth / 2, Main.boardHeight / 2});
        shipM = new ShipModel(ship);
        _objects = new MassiveObject[1];
        _objects[0] = ship;
        add(shipM, BorderLayout.CENTER);
    }

    public MassiveObject[] getObjects() {
        return _objects;
    }
    public void printShip() {
        ship.print();
    }

    public Ship getShip() {
        return ship;
    }

    public ShipModel getShipModel() {
        return shipM;
    }

    private Ship ship;

    private ShipModel shipM;

    private MassiveObject[] _objects;


}
