import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class patientData extends JFrame {
    //values
    private int age;
    private final int VALUES=7;
    private int WBC,Neut,Lymph,RBC,HCT,Urea,HB,Keratin,Iron,HDL,Alkaline;//importent to update the number of VALUEs
    public int[] HealthResults={WBC,Neut,Lymph,RBC,HCT,Urea,HB,Keratin,Iron,HDL,Alkaline};
    public YesNo Yesnoanswer;
    //panels
    private JPanel[] JPanelist;
    private JPanel insidePanel;
    private JPanel agePanel;
    private JPanel WBCPanel;
    private  JPanel NeutPanel;
    private JPanel LymphPanel;
    private  JPanel RBCPanel;

    private JLabel[] JLabelist;
    private String [] jLableString={"HCT 37-54% ","Urea 17-43","HB 12-16","Keratin 3-17","Iron 60-160","HDL 29-62","Alkaline 60"};
    private JLabel ageLable;
    private JLabel WBCLable;
    private  JLabel NeutLable;
    private JLabel LymphLable;
    private JLabel RBCLable;

    private JTextField[] Jtextlist;
    private String[] Jtextstring={"54","20","14","3","100","30","61"};
    private JTextField textage;
    private JTextField WBCtext;
    private JTextField Neuttext;
    private JTextField Lymphtext;
    private  JTextField RBCtext;

    private JButton okButton;

    public JSlider bloodSlider;

    private void SetHealthVal(){
//        HealthResults[0]=WBC;
//        HealthResults[1]=Neut;
//        HealthResults[2]=Lymph;
//        HealthResults[3]=RBC;
//        HealthResults[4]=HCT;
//        HealthResults[5]=Urea;
//        HealthResults[6]=HB;
//        HealthResults[7]=Keratin;
//        HealthResults[8]=Iron;
//        HealthResults[9]=HDL;
//        HealthResults[10]=Alkaline;

        HealthResults[0]=HCT;
        HealthResults[1]=Urea;
        HealthResults[2]=HB;
        HealthResults[3]=Keratin;
        HealthResults[4]=Iron;
        HealthResults[5]=HDL;
        HealthResults[6]=Alkaline;

    }


    public YesNo getYesnoanswer() {
        return Yesnoanswer;
    }

    public void setYesnoanswer(YesNo yesnoanswer) {
        Yesnoanswer = yesnoanswer;
    }

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

        JPanelist=new JPanel[VALUES];
        Jtextlist=new JTextField[VALUES];
        JLabelist=new JLabel[VALUES];



        insidePanel.add(agePanel);
        insidePanel.add(WBCPanel);
        insidePanel.add(NeutPanel);
        insidePanel.add(LymphPanel);
        insidePanel.add(RBCPanel);


        for(int i=0;i<VALUES;i++){
            JPanelist[i]=new JPanel();
            JPanelist[i].setLayout(new FlowLayout());
            JLabelist[i]=new JLabel();
            JLabelist[i].setText("Enter "+jLableString[i]);
            Jtextlist[i]=new JTextField(Jtextstring[i],3);
            JPanelist[i].add(JLabelist[i]);
            JPanelist[i].add(Jtextlist[i]);
            insidePanel.add(JPanelist[i]);

        }

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
                setWBC(Integer.parseInt(WBCtext.getText()));
                Neut=Integer.parseInt(Neuttext.getText());
                Lymph=Integer.parseInt(Lymphtext.getText());
                RBC=Integer.parseInt(RBCtext.getText());
                HCT=Integer.parseInt(Jtextlist[0].getText());
                Urea=Integer.parseInt(Jtextlist[1].getText());
                HB=Integer.parseInt(Jtextlist[2].getText());
                Keratin=Integer.parseInt(Jtextlist[3].getText());
                Iron=Integer.parseInt(Jtextlist[4].getText());
                HDL=Integer.parseInt(Jtextlist[5].getText());
                Alkaline=Integer.parseInt(Jtextlist[6].getText());
                SetHealthVal();
                JOptionPane.showMessageDialog(patientData.this, "Success", ":)", JOptionPane.NO_OPTION);
                System.out.println("ok button worked");
                for(int i:HealthResults){
                    System.out.println(i);
                }
                setVisible(false);

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

    public int[] getHealthResults() {
        return HealthResults;
    }
    public int getHealthResultsVal(int index) {
        return HealthResults[index];
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWBC() {
        return WBC;
    }

    public void setWBC(int WBC) {
        this.WBC = WBC;
    }

    public int getNeut() {
        return Neut;
    }

    public void setNeut(int neut) {
        Neut = neut;
    }

    public int getLymph() {
        return Lymph;
    }

    public void setLymph(int lymph) {
        Lymph = lymph;
    }

    public int getRBC() {
        return RBC;
    }

    public void setRBC(int RBC) {
        this.RBC = RBC;
    }

    public int getHCT() {
        return HCT;
    }

    public void setHCT(int HCT) {
        this.HCT = HCT;
    }

    public int getUrea() {
        return Urea;
    }

    public void setUrea(int urea) {
        Urea = urea;
    }

    public int getHB() {
        return HB;
    }

    public void setHB(int HB) {
        this.HB = HB;
    }

    public int getKeratin() {
        return Keratin;
    }

    public void setKeratin(int keratin) {
        Keratin = keratin;
    }

    public int getIron() {
        return Iron;
    }

    public void setIron(int iron) {
        Iron = iron;
    }

    public int getHDL() {
        return HDL;
    }

    public void setHDL(int HDL) {
        this.HDL = HDL;
    }

    public int getAlkaline() {
        return Alkaline;
    }

    public void setAlkaline(int alkaline) {
        Alkaline = alkaline;
    }
}
