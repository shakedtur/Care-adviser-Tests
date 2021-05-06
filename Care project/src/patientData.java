import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class patientData extends JFrame {
    //values
    private int age;
    
    
    //panels
    private JPanel insidePanel;
    private JPanel agePanel;
    private JPanel WBCPanel;
    private  JPanel NeutPanel;
    private JPanel LymphPanel;
    private  JPanel RBCPanel;

    private JLabel ageLable;
    private JLabel WBCLable;
    private  JLabel NeutLable;
    private JLabel LymphLable;
    private JLabel RBCLable;

    private JTextField textage;
    private JTextField WBCtext;
    private JTextField Neuttext;
    private JTextField Lymphtext;
    private  JTextField RBCtext;

    private JButton okButton;

    public JSlider bloodSlider;


    public patientData(){
        setTitle("health qeustions ");
        //setSize(1200,700);
        insidePanel=new JPanel();

        insidePanel.setLayout(new BoxLayout(insidePanel,BoxLayout.Y_AXIS));
        //insidePanel.setLayout(new FlowLayout());
        agePanel=CreateAgeP();
        WBCPanel=CreateWBCP();
        CreateNeut();
        LymphPanel=CreateLymph();
        CreateRBC();


        insidePanel.add(agePanel);
        insidePanel.add(WBCPanel);
        insidePanel.add(NeutPanel);
        insidePanel.add(LymphPanel);
        insidePanel.add(RBCPanel);

//        bloodSlider =new JSlider(JSlider.HORIZONTAL,4500,17500,10000);
//        bloodSlider.setMajorTickSpacing(1);
//        bloodSlider.setPaintTicks(true);
//        bloodSlider.setPaintLabels(true);
//
//        add(new JLabel("number of white blood cells"), BorderLayout.CENTER);
//        add(bloodSlider);
        okButton=new JButton("Ok");
        okButton.addActionListener(new OkListener() );
        insidePanel.add(okButton);
        add(insidePanel);
        insidePanel.setVisible(true);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();

    }

    private class OkListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                age = Integer.parseInt(textage.getText());

                JOptionPane.showMessageDialog(patientData.this, "Success", ":)", JOptionPane.NO_OPTION);
                System.out.println("ok button worked");

            }
            catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(patientData.this, "Wrong input, try again", "Wrong Input",JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private JPanel CreateAgeP(){
        JPanel agepanle=new JPanel();
        agepanle.setLayout(new FlowLayout());
        ageLable=new JLabel("Enter your age 0-120");
        textage=new JTextField("18",3);
        agepanle.add(ageLable);
        agepanle.add(textage);
        return agepanle;

    }
    private JPanel CreateWBCP(){
        JPanel WBCpanle=new JPanel();
        WBCpanle.setLayout(new FlowLayout());
        WBCLable=new JLabel("WBC white blod Cells:");
        WBCtext=new JTextField("5500",3);
        WBCpanle.add(WBCLable);
        WBCpanle.add(WBCtext);
        return WBCpanle;
//        JPanel WBCpanle=new JPanel();
//        WBCpanle.setLayout(new FlowLayout());
//        WBCLable=new JLabel("WBC white blod Cells:");
//        WBCtext=new JTextField("5500",3);
//        WBCpanle.add(ageLable);
//        WBCpanle.add(textage);
//        WBCpanle.setVisible(true);
//        return WBCpanle;
    }
    private  JPanel CreateNeut(){
        NeutPanel=new JPanel();
        NeutPanel.setLayout(new FlowLayout());
        NeutLable=new JLabel("Neut neutrophil present 0-100 %");
        Neuttext=new JTextField("30",3);
        NeutPanel.add(NeutLable);
        NeutPanel.add(Neuttext);
        return NeutPanel;
    }
    private  JPanel CreateLymph(){
        LymphPanel=new JPanel();
        LymphPanel.setLayout(new FlowLayout());
        LymphLable=new JLabel("Enter your present 0-100%");
        Lymphtext=new JTextField("50",3);
        LymphPanel.add(LymphLable);
        LymphPanel.add(Lymphtext);
        return LymphPanel;
    }
    private  JPanel CreateRBC(){
        RBCPanel=new JPanel();
        RBCPanel.setLayout((new FlowLayout()));
        RBCLable=new JLabel("Enter RBC red blood cells ");
        RBCtext=new JTextField("5",3);
        RBCPanel.add(RBCLable);
        RBCPanel.add(RBCtext);
        return RBCPanel;

    }


    


}
