import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainMenu extends JFrame implements ActionListener {
    private JMenuItem[] mi;
    private JMenuBar mb;
    private patientData firstpatient;
    public  ArrayList <patientData> patientlist;

    //Buttons
    private JPanel bottomRow;
    private JButton[] btmButtons;
    private String[] btmNameStrings = { "login","Patient Data","Yes No q","diagnosis"};
    public MainMenu(){
        super("Care Adviser");
        setSize(600,700);
        mb = new JMenuBar();
        bottomRowConstructor();
        add(bottomRow,BorderLayout.SOUTH);
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
        }
        else if(e.getSource()==btmButtons[3]){//diagnosis
            if(firstpatient!=null) {
                Analysis ana = new Analysis(firstpatient);
                //ana.diagnosis();
                add(ana.getScrollPane(),BorderLayout.CENTER);
                repaint();
                revalidate();
            }
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
