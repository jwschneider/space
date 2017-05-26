package space;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main extends JFrame {

    public static void main(String[] args) {
        M = new Main(1000, 1000);

    }

    public Main(int width, int height) {
        boardWidth = width;
        boardHeight = height;
        FRAMERATE = 60;
        setSize(boardWidth, boardHeight);
        setTitle("space");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        createUniverse();
        createShip();
        runThreads();
    }


    private void createUniverse() {
        U = new Universe();
        U.setLayout(new BorderLayout());
        JScrollPane view = new JScrollPane(U);
        view.setSize(boardWidth, boardHeight);
        //view.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        //view.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        getContentPane().add(view, BorderLayout.CENTER);
    }

    public void changeView(double[] loc) {
        Rectangle view = new Rectangle((int) loc[0] - boardWidth / 2,(int) loc[1] - boardHeight / 2,
                boardWidth, boardHeight);
        U.scrollRectToVisible(view);
    }

    public void centerView() {
        changeView(U.getShip().getLoc());
    }

    private void createShip() {
        U.createShip();
    }

    private void runThreads() {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
        long lengthOfFrame = (long) (1000 / FRAMERATE);
        executor.scheduleAtFixedRate(new Input(U.getShip(), FRAMERATE), 0L, lengthOfFrame, TimeUnit.MILLISECONDS);
        executor.scheduleAtFixedRate(new UpdatePhysics(U, U.getObjects(), FRAMERATE), 0L, lengthOfFrame, TimeUnit.MILLISECONDS);
        executor.scheduleAtFixedRate(new RepaintFrame(this), 0L, lengthOfFrame, TimeUnit.MILLISECONDS);
        this.addKeyListener(new UserInput());
    }



    static int boardWidth;
    static int boardHeight;
    static double FRAMERATE;
    static Main M;
    static Universe U;

    private class UserInput implements KeyListener {

        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                U.getShip().MAIN_ENGINES = true;
            }
            else if (e.getKeyCode() == KeyEvent.VK_W) {
                U.getShip().TOP_STABILIZERS = true;
            }
            else if (e.getKeyCode() == KeyEvent.VK_S) {
                U.getShip().BOTTOM_STABILIZERS = true;
            }
        }

        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                U.getShip().MAIN_ENGINES = false;
            }
            else if (e.getKeyCode() == KeyEvent.VK_W) {
                U.getShip().TOP_STABILIZERS = false;
            }
            else if (e.getKeyCode() == KeyEvent.VK_S) {
                U.getShip().BOTTOM_STABILIZERS = false;
            }
        }

        public void keyTyped(KeyEvent e) {

        }
    }
}
