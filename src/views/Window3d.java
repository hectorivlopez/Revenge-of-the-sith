package views;


import utils.CustomThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window3d extends JFrame implements ActionListener, MouseListener, MouseMotionListener {
    private int width;
    private int height;
    private int titleBarHeight;
    private JPanel bgPanel;
    private CanvasPanel canvasPanel;
    private JPanel bottomBar;
    public JLabel directorLabel;
    public int xPressed, yPressed;
    public int xDirInit, yDirInit;
    boolean firstResize;
    public boolean isMacOS;

    public Window3d() {
        this.isMacOS = System.getProperty("os.name").toLowerCase().contains("mac");
        if (isMacOS) {
            final JRootPane rootPane = this.getRootPane();
            rootPane.putClientProperty("apple.awt.fullWindowContent", true);
            rootPane.putClientProperty("apple.awt.transparentTitleBar", true);
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        }

        initComponents();

        setVisible(true);
    }

    private void initComponents() {
        titleBarHeight = 28;
        if (isMacOS) titleBarHeight = 0;

        width = 1200;
        height = 600;

        setSize(width, height + titleBarHeight + 40);
        setLocationRelativeTo(null);
        setLayout(null);
        firstResize = true;
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateSize();

            }
        });

        bgPanel = new JPanel();
        bgPanel.setBounds(0, 0, width, height);
        bgPanel.setLayout(null);
        setContentPane(bgPanel);

        canvasPanel = new CanvasPanel(width, height);
        canvasPanel.setBounds(0, 0, width, height);
        canvasPanel.setLayout(null);
        canvasPanel.addMouseListener(this);
        canvasPanel.addMouseMotionListener(this);
        canvasPanel.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int notches = e.getWheelRotation();
                canvasPanel.director[2] += notches;
                canvasPanel.repaint();
                directorLabel.setText("P(" + canvasPanel.director[0] + ", " + canvasPanel.director[1] + ", " + canvasPanel.director[2] + ")");
            }
        });
        bgPanel.add(canvasPanel);


    }

    private void updateSize() {
        width = getWidth();
        height = getHeight() - titleBarHeight;
        setBackground(new Color(10, 10, 10));

        bgPanel.setBounds(0, 0, width, height);
        if (!firstResize) {
            bottomBar.remove(directorLabel);
            bgPanel.remove(bottomBar);
        }

        bottomBar = new JPanel();
        bottomBar.setLayout(null);
        bottomBar.setBounds(0, height - 40, this.width, 40);
        bottomBar.setBackground(new Color(30, 32, 47));
        bgPanel.add(bottomBar);

        directorLabel = new JLabel();
        directorLabel.setBounds(0, 0, this.width, 40);
        directorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        directorLabel.setVerticalAlignment(SwingConstants.CENTER);
        directorLabel.setForeground(Color.white);
        directorLabel.setText("P(" + canvasPanel.director[0] + ", " + canvasPanel.director[1] + ", " + canvasPanel.director[2] + ")");
        bottomBar.add(directorLabel);
        bgPanel.repaint();

        // Set new bounds for canvasPanel
        canvasPanel.setBounds(0, 0, width, height - 40);
        canvasPanel.resize(width, height - 40);
        canvasPanel.repaint();

        firstResize = false;
    }

    // ---------------------------------------- Listeners ----------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.xPressed = e.getX();
        this.yPressed = e.getY();

        this.xDirInit = canvasPanel.director[0];
        this.yDirInit = canvasPanel.director[1];
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

        int xDirector = -(e.getX() - xPressed) / 1;
        int yDirector = -(e.getY() - yPressed) / 1;

        canvasPanel.director[0] = xDirInit + xDirector;
        canvasPanel.director[1] = yDirInit + yDirector;
        canvasPanel.repaint();

        directorLabel.setText("P(" + canvasPanel.director[0] + ", " + canvasPanel.director[1] + ", " + canvasPanel.director[2] + ")");
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    // ---------------------------------------- Execution ----------------------------------------
    public static void main(String[] args) {
        Window3d window = new Window3d();

        CustomThread thread = new CustomThread(() -> {
            window.bgPanel.repaint();
        }, 25, () -> false);
        thread.start();

    }
}