import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class patientData extends JFrame {
    //values
    private int age;
    private String firstname,lastname,id;
    private final int VALUES=11;
    private int WBC,Neut,Lymph,HCT,Urea,HB,Iron,HDL,Alkaline;//importent to update the number of VALUEs
    private double RBC,Keratin;
    public String[] paramtername={"WBC","Neut","Lymph","RBC","HCT","Urea","HB","Keratin","Iron","HDL","Alkaline"};
    public int[] HealthResults={WBC,Neut,Lymph,(int)RBC,HCT,Urea,HB,(int)Keratin,Iron,HDL,Alkaline};
    public YesNo Yesnoanswer;
    public Analysis analysis;
    //panels
    private JPanel[] JPanelist;
    private JPanel insidePanel;
    private JPanel agePanel;

    private JPanel FirstNamepnl,LastNamepnl,IDpnl;

    private JLabel[] JLabelist;
    private String [] jLableString={"WBC","Neut %","Lymph %","RBC","HCT 37-54% ","Urea 17-43","HB 12-16","Keratin 3-17","Iron 60-160","HDL 29-62","Alkaline 60"};
    private JLabel ageLable;

    private JLabel FirstNameLable,LastNameLable,IDLable;

    private JTextField[] Jtextlist;
    private String[] Jtextstring={"5000","33","40","5.3","38","22","14","1","140","30","61"};
    private JTextField textage;

    private JTextField FirstNameText,LastNameText,IDText;

    private JButton okButton;

    public JSlider bloodSlider;

    private void SetHealthVal(){
        HealthResults[0]=WBC;
        HealthResults[1]=Neut;
        HealthResults[2]=Lymph;
        HealthResults[3]=(int)RBC;
        HealthResults[4]=HCT;
        HealthResults[5]=Urea;
        HealthResults[6]=HB;
        HealthResults[7]=(int)Keratin;
        HealthResults[8]=Iron;
        HealthResults[9]=HDL;
        HealthResults[10]=Alkaline;

    }

    public Analysis getAnalysis() {
        return analysis;
    }

    public void setAnalysis() {
        this.analysis = new Analysis(this);
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
        agePanel=CreateAgeP();
        JPanelist=new JPanel[VALUES];
        Jtextlist=new JTextField[VALUES];
        JLabelist=new JLabel[VALUES];
        insidePanel.add(agePanel);
        insidePanel.add(CreatePresonaldata());

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
                int a=Integer.parseInt(textage.getText());
                if(a<0||a>120) {
                    throw new Exception("wrong age");
                }
                age = Integer.parseInt(textage.getText());
                firstname= String.valueOf(FirstNameText.getText());
                lastname= String.valueOf(LastNameText.getText());
                id= String.valueOf(IDText.getText());
                WBC=(Integer.parseInt(Jtextlist[0].getText()));
                Neut=Integer.parseInt(Jtextlist[1].getText());
                Lymph=Integer.parseInt(Jtextlist[2].getText());
                RBC=Double.parseDouble(Jtextlist[3].getText());
                HCT=Integer.parseInt(Jtextlist[4].getText());
                Urea=Integer.parseInt(Jtextlist[5].getText());
                HB=Integer.parseInt(Jtextlist[6].getText());
                Keratin=Double.parseDouble(Jtextlist[7].getText());
                Iron=Integer.parseInt(Jtextlist[8].getText());
                HDL=Integer.parseInt(Jtextlist[9].getText());
                Alkaline=Integer.parseInt(Jtextlist[10].getText());
                SetHealthVal();
                //TODO remove notes from here!!!

                if(!isINRange(Neut)||!isINRange(Lymph)||!isINRange(HCT)){
                    throw new Exception();
                }
                JOptionPane.showMessageDialog(patientData.this, "Success", ":)", JOptionPane.NO_OPTION);
                setVisible(false);

            }

            catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(patientData.this, "Wrong age, try again", "Wrong Input",JOptionPane.WARNING_MESSAGE);
            }
            catch (Exception es){
                JOptionPane.showMessageDialog(patientData.this, "\"the % values must me between 0-100", "Wrong Input",JOptionPane.WARNING_MESSAGE);

            }


        }
    }

    public boolean isINRange(int val){
        if(0<=val && val<=100)
            return true;
        else
            return false;
    }
    public void correctage(int a)throws Exception{
        if(a<0||a>120)
            throw new Exception("wrong age");
        age=a;
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
    private JPanel CreatePresonaldata(){
        JPanel Personal=new JPanel();
        Personal.setLayout(new BoxLayout(insidePanel,BoxLayout.Y_AXIS));
        FirstNamepnl=new JPanel();
        LastNamepnl=new JPanel();
        IDpnl=new JPanel();
        Personal.setLayout(new FlowLayout());
        FirstNameLable=new JLabel("First name:");
        FirstNameText=new JTextField("Israel",6);
        FirstNamepnl.add(FirstNameLable);
        FirstNamepnl.add(FirstNameText);
        Personal.add(FirstNamepnl);
        LastNameLable=new JLabel("Last name:");
        LastNameText=new JTextField("Israely",6);
        LastNamepnl.add(LastNameLable);
        LastNamepnl.add(LastNameText);
        Personal.add(LastNamepnl);
        IDLable=new JLabel("ID number:");
        IDText=new JTextField("123456789",6);
        IDpnl.add(IDLable);
        IDpnl.add(IDText);
        Personal.add(IDpnl);


        return Personal;

    }


    public int[] getHealthResults() {
        return HealthResults;
    }

    public String[] getParamtername() {
        return paramtername;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public double getRBC() {
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

    public double getKeratin() {
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
