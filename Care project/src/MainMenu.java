import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainMenu extends JFrame implements ActionListener {
    private JMenuItem[] mi;
    private JMenuBar mb;
    private JMenu m1, m2, m3;
    private String[] names = { "Home", "File", "data", "None", "About" };
    //image
    private final String pathfile="src/doctor.jpg";
    public BufferedImage image;
    private patientData firstpatient;
    private JPanel panel;
    public  ArrayList <patientData> patientlist;
    private JScrollPane tableshow;
    //Buttons
    private JPanel bottomRow;
    private JButton[] btmButtons;
    private String[] btmNameStrings = { "login","Patient Data","Yes No q","diagnosis"};
    public MainMenu(){
        super("Care Adviser");
        setSize(600,700);
        bottomRowConstructor();
        add(bottomRow,BorderLayout.SOUTH);
        CrateMenubar();
        setImage();

    }

    private void CrateMenubar(){
        mb = new JMenuBar();
        m1 = new JMenu("Home");
        m2 = new JMenu("Info");
        m3 = new JMenu("Help");
        mi = new JMenuItem[names.length];
        for (int i = 0; i < names.length; i++) {
            mi[i] = new JMenuItem(names[i]);
            mi[i].addActionListener(this);
        }

        m1.add(mi[0]);

        m2.add(mi[1]);
        m2.addSeparator();
        m2.add(mi[2]);
        m2.addSeparator();
        m2.add(mi[3]);

        m3.add(mi[4]);

        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
        setJMenuBar(mb);
    }
    private void setImage() {

        try {
            image = ImageIO.read(new File(pathfile));
            panel=new imagepanel();
            panel.setBackground(Color.GREEN);
            panel.setVisible(true);
            add(panel,BorderLayout.CENTER);
            repaint();
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(this, "Cannot load image");
        }

    }
    public class imagepanel extends JPanel{
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==btmButtons[0]){//login
            login log=new login();
            if(log.loginsuccess==true){}
            //TODO complete button just than login
        }
        if(e.getSource()==btmButtons[1]){//Patient data
            firstpatient=new patientData();
            //patientlist.add(qframe);
        }
        else if(e.getSource()==btmButtons[2]){//Yesno
            if(firstpatient!=null) {
                firstpatient.setYesnoanswer(new YesNo());
            }
            else
                JOptionPane.showMessageDialog(this, "fill in patient data before Yes No", "Error",JOptionPane.WARNING_MESSAGE);
        }
        else if(e.getSource()==btmButtons[3]&&firstpatient!=null&&firstpatient.Yesnoanswer!=null){//diagnosis show table
            panel.setVisible(false);
            if(firstpatient!=null) {
//                    Analysis ana = new Analysis(firstpatient);
                    firstpatient.setAnalysis();
                    //ana.diagnosis();
                    if(tableshow!=null){
                        remove(tableshow);
                    }
                    JTable t=new JTable();

                    tableshow=firstpatient.getAnalysis().getScrollPane();
                    add(tableshow);
                    repaint();
                    revalidate();
                }
            else
                JOptionPane.showMessageDialog(this, "fill in Yes No ", "Error",JOptionPane.WARNING_MESSAGE);
            }
        //menu bar buttons
        else if(e.getSource()==mi[0]){
            panel.setVisible(true);
        }
        else if (e.getSource() == mi[4]) {
            printHelp();
        }

        }


    private void bottomRowConstructor() {
        bottomRow = new JPanel();
        bottomRow.setLayout(new FlowLayout());
        bottomRow.setBackground(Color.CYAN);

        // Create 7 buttons as required.
        btmButtons = new JButton[btmNameStrings.length];
        for (int i = 0; i < btmNameStrings.length; i++) {
            btmButtons[i] = new JButton(btmNameStrings[i]);
            // if (i == 5)
            // btmButtons[i].addMouseListener(new mouseClick());
            // else
            btmButtons[i].addActionListener(this);
            // Add the buttons to the buttomRow panel.
            bottomRow.add(btmButtons[i]);
        }
    }
    public void printHelp() {
        JOptionPane.showMessageDialog(this, "Created by Shaked Turgergeman \n Sagi");
    }

    public static void main(String[] args) {

        //login log=new login();
        MainMenu Mainscreen=new MainMenu();
        Mainscreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Mainscreen.setVisible(true);
        //login log=new login();
//        patientData qframe=new patientData();
//
//        YesNo test=new YesNo();
    }
}
