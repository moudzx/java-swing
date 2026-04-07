//import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class MainFrame extends JFrame {

    private JMenuBar bar = new JMenuBar();
    private JMenu fileMn = new JMenu("File");
    private JMenu editMn = new JMenu("Edit");
    private JMenu aboutMn = new JMenu("About");

    //Menu Item for File
    private JMenuItem openTm = new JMenuItem("Open...");
    private JMenuItem saveTm = new JMenuItem("Save");
    private JMenuItem saveAsTm = new JMenuItem("Save...");
    private JMenuItem exitTm = new JMenuItem("Exit");

    //Menu Item for Edit
    private JMenuItem copyTm = new JMenuItem("Copy");
    private JMenu pasteMn = new JMenu("Paste");
    private JMenuItem pastePlainTm = new JMenuItem("Paste Plain");
    private JMenuItem pasteFormatTm = new JMenuItem("Paste Format");

    //Text
    private JTextArea area = new JTextArea();

    public MainFrame() {
        initComponents();
        initListener();
    }

    private void initComponents() {
        setTitle("Notepad");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        fileMn.add(openTm);
        fileMn.add(saveTm);
        fileMn.add(saveAsTm);
        fileMn.addSeparator();
        fileMn.add(exitTm);

        pasteMn.add(pastePlainTm);
        pasteMn.add(pasteFormatTm);
        editMn.add(copyTm);
        editMn.add(pasteMn);

        bar.add(fileMn);
        bar.add(editMn);
        bar.add(aboutMn);
        this.setJMenuBar(bar);

        //Some Settings for Text Area
        Font customFont = new Font("Arial", Font.PLAIN, 12);        
        area.setFont(customFont);        
        area.setLineWrap(true);

        JScrollPane areaPane = new JScrollPane(area);
        JPanel centerPane = new JPanel(new BorderLayout());
        centerPane.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        centerPane.add(areaPane, BorderLayout.CENTER);
        this.add(centerPane, BorderLayout.CENTER);
    }

    private void initListener() {
        exitTm.addActionListener((e) -> System.exit(0));
        openTm.addActionListener((e)-> openPerformed(e));
    }
    
    private void openPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser(new File("./"));
        //chooser.setCurrentDirectory(new File("./"));
        int option = chooser.showOpenDialog(this);//showSaveDialog
        if(option == JFileChooser.APPROVE_OPTION){
            File file = chooser.getSelectedFile();
            
            try(BufferedReader in = new BufferedReader(new FileReader(file))){
                StringBuilder builder = new StringBuilder();
                String str;
                while((str=in.readLine())!= null){
                    builder.append(str).append("\n");
                }
                area.setText(builder.toString());
            } catch (Exception ex) {
                System.getLogger(MainFrame.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            System.getLogger(MainFrame.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } 
//        FlatLightLaf.setup();
        EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
