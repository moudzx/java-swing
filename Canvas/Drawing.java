package canvas;

//import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class Drawing extends JFrame {

    private JButton undo = new JButton("Undo");
    private JButton delete = new JButton("Delete");
    private JButton exit = new JButton("Exit");

    private JLabel mesg = new JLabel("Press & Drag to Draw");
    private JLabel coord = new JLabel("x: 0, y: 0");

    private Color[] colors = {Color.red, Color.blue, Color.green, Color.orange};
    private String[] colorsName = {"Red", "Blue", "Green", "Orange"};
    private JComboBox<String> box = new JComboBox(colorsName);

    private Color currentColor = Color.red;

    private List<Line> ls = new ArrayList();

    private BasicStroke stroke = new BasicStroke(3.5f);

    private JPanel canvas = new JPanel() {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D gd = (Graphics2D) g;
            gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            gd.setStroke(stroke);
            for (Line line : ls) {
                line.draw(gd);
            }
        }
    };

    private Line currentLine;

    public Drawing() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Drawing");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel south = new JPanel(new GridLayout(1, 2));

        canvas.setBackground(Color.white);

        north.setBorder(BorderFactory.createEtchedBorder());
        canvas.setBorder(BorderFactory.createEtchedBorder());
        south.setBorder(BorderFactory.createEtchedBorder());

        north.add(box);
        north.add(undo);
        north.add(delete);
        north.add(exit);

        south.add(mesg);
        south.add(coord);

        this.add(north, BorderLayout.NORTH);
        this.add(canvas, BorderLayout.CENTER);
        this.add(south, BorderLayout.SOUTH);

        canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undoPerformed(e);
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletePerformed(e);
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitPerformed(e);
            }
        });

        box.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectionPerformed(e);
            }
        });

        canvas.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                pressPerformed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                releasePerformed(e);
            }
        });

        canvas.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                dragPerformed(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                movePerformed(e);
            }
        });

    }

    private void undoPerformed(ActionEvent e) {
        if (!ls.isEmpty()) {
            ls.remove(ls.size() - 1);
            canvas.repaint();
        }
    }

    private void deletePerformed(ActionEvent e) {
        ls.clear();
        canvas.repaint();
    }

    private void exitPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void selectionPerformed(ActionEvent e) {
        currentColor = colors[box.getSelectedIndex()];
        //System.out.println(box.getSelectedItem());
    }

    private void pressPerformed(MouseEvent e) {
        mesg.setText("Release to Finish Drawing");
        ls.add(currentLine = new Line(e.getX(), e.getY(), currentColor));
        canvas.repaint();
    }

    private void releasePerformed(MouseEvent e) {
        mesg.setText("Press & Drag to Draw");
    }

    private void movePerformed(MouseEvent e) {
        coord.setText(String.format("x: %d, y: %d", e.getX(), e.getY()));
    }

    private void dragPerformed(MouseEvent e) {
        currentLine.setX2(e.getX());
        currentLine.setY2(e.getY());
        canvas.repaint();
        coord.setText(String.format("x: %d, y: %d", e.getX(), e.getY()));
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            Logger.getLogger(Drawing.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Event Dispatching Thread EDT
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Drawing().setVisible(true);
            }
        });
    }
}
