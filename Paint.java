import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * Amusement Park
 *
 * The program is the generating the array for the game.
 *
 * @author Tanmay Singla singlat L-15
 *
 * @version 2022-02-28
 *
 */

public class Paint extends JComponent implements Runnable {

    Image image; // the canvas
    Graphics2D graphics2D;  // this will enable drawing
    int curX; // current mouse x coordinate
    int curY; // current mouse y coordinate
    int oldX; // previous mouse x coordinate
    int oldY; // previous mouse y coordinate
    JButton clrButton; // a button to change paint color
    JButton fillButton; // a button to change paint color
    JButton eraseButton; // a button to change paint color
    JButton randomButton; // a button to change paint color
    JButton hexButton;
    JButton rgbButton;
    JTextField hexText1;
    JTextField rText1;
    JTextField gText1;
    JTextField bText1;
    String hexText;
    String rText = "";
    String gText = "";
    String bText = "";

    Color randomColor = Color.black;
    Color backColor = Color.WHITE;


    JTextField strTextField; // text field for input
    Paint paint; // variable of the type SimplePaint

    public Paint() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // set oldX and oldY coordinates to beginning mouse press
                oldX = e.getX();
                oldY = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // set current coordinates to where mouse is being dragged
                curX = e.getX();
                curY = e.getY();
                // draw the line between old coordinates and new ones
                graphics2D.drawLine(oldX, oldY, curX, curY);
                    // refresh frame and reset old coordinates
                repaint();
                oldX = curX;
                oldY = curY;

            }
        });
    }

    public void run() {

        JFrame frame = new JFrame("Simple Paint");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        paint = new Paint();
        content.add(paint, BorderLayout.CENTER);
        content.add(paint);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        JPanel panel = new JPanel();
        clrButton = new JButton("Clear");
        panel.add(clrButton);
        clrButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                paint.clear();
            }
        });

        fillButton = new JButton("Fill");
        panel.add(fillButton);
        fillButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                paint.fill();
            }
        });
        eraseButton = new JButton("Erase");
        panel.add(eraseButton);
        eraseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                paint.eraseButton();
            }
        });

        randomButton = new JButton("Random");
        panel.add(randomButton);
        randomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                paint.randomButton();
            }
        });


        content.add(panel, BorderLayout.NORTH);
        panel = new JPanel();

        hexText1 = new JTextField(10);
        hexButton = new JButton("HEX Button");
        panel.add(hexText1);
        panel.add(hexButton);
        hexButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hexText = hexText1.getText();
                paint.hexButton(hexText);
            }
        });

        rText1 = new JTextField(5);
        gText1 = new JTextField(5);
        bText1 = new JTextField(5);
        rgbButton = new JButton("rgbButton");
        panel.add(rText1);
        panel.add(gText1);
        panel.add(bText1);
        panel.add(rgbButton);

        rgbButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int rText2 = 0;
                int gText2 = 0;
                int bText2 = 0;
                System.out.println("R:" + rText1);

                rText = rText1.getText();
                gText = gText1.getText();
                bText = bText1.getText();

                if (rText == null) {
                    rText = "0";
                }
                if (gText == null) {
                    gText = "0";
                }
                if (bText == null) {
                    bText = "0";
                }

                try {
                    rText2 = Integer.parseInt(rText);
                } catch (NumberFormatException t) {
                    rText2 = 0;
                }

                try {
                    gText2 = Integer.parseInt(gText);
                } catch (NumberFormatException t) {
                    gText2 = 0;
                }

                try {
                    bText2 = Integer.parseInt(bText);
                } catch (NumberFormatException t) {
                    bText2 = 0;
                }


                if (rText2 > 255 || rText2 < 0 || gText2 > 255 || gText2 < 0 || bText2 > 255 || bText2 < 0) {
                    JOptionPane.showMessageDialog(null, "Not a valid RGB Value", "Error", 0);
                } else {
                    System.out.println(rText2);
                    System.out.println(gText2);
                    System.out.println(bText2);
                    paint.rgbButton(rText2, gText2, bText2);
                }
            }
        });

        content.add(panel, BorderLayout.SOUTH);

    }

    public void clear() {
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, getSize().width, getSize().height);
        graphics2D.setPaint(Color.black);
        graphics2D.setStroke(new BasicStroke(5));
        repaint();
        hexText = "#";
        rText = "";
        gText = "";
        bText = "";
        backColor = Color.WHITE;
    }

    public void fill() {
        graphics2D.setPaint(randomColor);
        graphics2D.fillRect(0, 0, getSize().width, getSize().height);
        repaint();
        backColor = randomColor;
        hexText = "#";
        rText = "";
        gText = "";
        bText = "";
    }

    public void eraseButton() {
        graphics2D.setPaint(backColor);
        graphics2D.setStroke(new BasicStroke(5));
    }

    public void randomButton() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        randomColor = new Color(r, g, b);
        graphics2D.setColor(randomColor);

        hexText = String.format("#%02x%02x%02x", r, g, b);
        rText = String.valueOf(r);
        gText = String.valueOf(g);
        bText = String.valueOf(b);

    }

    public void hexButton(String hexText4) {
        try {
            randomColor = Color.decode(hexText4);
            graphics2D.setColor(randomColor);
            rText = String.valueOf(randomColor.getRed());
            gText = String.valueOf(randomColor.getGreen());
            bText = String.valueOf(randomColor.getBlue());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Not a valid RGB Value", "Error", 0);

        }
    }

    public void rgbButton(int r, int g, int b) {
        randomColor = new Color(r, g, b);
        graphics2D.setColor(randomColor);
        hexText = String.format("#%02x%02x%02x", r, g, b);
    }

    protected void paintComponent(Graphics g) {
        if (image == null) {
            image = createImage(getSize().width, getSize().height);
                // this lets us draw on the image (ie. the canvas)
            graphics2D = (Graphics2D) image.getGraphics();


            // gives us better rendering quality for the drawing lines
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
                // set canvas to white with default paint color
            graphics2D.setPaint(Color.white);
            graphics2D.fillRect(0, 0, getSize().width, getSize().height);
            graphics2D.setPaint(Color.black);
            graphics2D.setStroke(new BasicStroke(5));

            repaint();
        }
        g.drawImage(image, 0, 0, null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Paint());

    }

}