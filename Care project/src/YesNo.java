import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class YesNo extends JFrame {
    private boolean smoking;
    private boolean eastPerson;
    private boolean female;
    private boolean lung;
    private boolean ethiopian;
    private boolean pregnancy;
    private int age;
    private String[] textarr={"Are you smoking?","Are you easy preson?","Are you female?","do you have lung ill in past","Ethiopian?","pregnancy now?"};
    private JPanel initpanel;
    private JPanel questionspanel;
    private JPanel answerpanel;
    private ArrayList <JRadioButton> anwRadiobuttons=new ArrayList<JRadioButton>();
    private JRadioButton smokRadio;
    private JRadioButton eastPersonRadio;
    private JRadioButton femaleRadio;
    private JRadioButton lungRadio;
    private JRadioButton Ethiopianradio;
    private JRadioButton pregnancyradio;


    private JRadioButton[] arradio={smokRadio,eastPersonRadio,femaleRadio,lungRadio,Ethiopianradio,pregnancyradio};

    private JButton okButton;
    public YesNo(){
        setTitle("Yes no questions to patient");
        initpanel=new JPanel();
        answerpanel=new JPanel();
        initpanel.setLayout(new BorderLayout());

        okButton=new JButton("Ok");
        okButton.addActionListener(new OkListener());
        initpanel.add(okButton,BorderLayout.SOUTH);
        questionspanel=new JPanel();
        questionspanel.setLayout(new BoxLayout(questionspanel,BoxLayout.Y_AXIS));
        answerpanel.setLayout(new BoxLayout(answerpanel,BoxLayout.Y_AXIS));
        for(int i=0;i<textarr.length;i++){
            JLabel qtext=new JLabel((i+1)+"."+textarr[i]);
            qtext.setSize(new Dimension());
            questionspanel.add(qtext);
        }
        for (int i=0;i< arradio.length;i++){
            arradio[i]=new JRadioButton();
            answerpanel.add(arradio[i]);
        }

        initpanel.add(questionspanel,BorderLayout.WEST);
        initpanel.add(answerpanel,BorderLayout.EAST);
        answerpanel.setVisible(true);
        initpanel.setVisible(true);
        add(initpanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private class OkListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

          setSmoking(arradio[0].isSelected());
            eastPerson=arradio[1].isSelected();
            female=arradio[2].isSelected();
            lung=arradio[3].isSelected();
            ethiopian=arradio[4].isSelected();
            pregnancy=arradio[5].isSelected();
            setVisible(false);
        }


    }

    public boolean isSmoking() {
        return smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

    public boolean isEastPerson() {
        return eastPerson;
    }

    public void setEastPerson(boolean eastPerson) {
        this.eastPerson = eastPerson;
    }

    public boolean isFemale() {
        return female;
    }

    public void setFemale(boolean female) {
        this.female = female;
    }

    public boolean isLung() {
        return lung;
    }

    public void setLung(boolean lung) {
        this.lung = lung;
    }

    public boolean isEthiopian() {
        return ethiopian;
    }

    public void setEthiopian(boolean ethiopian) {
        this.ethiopian = ethiopian;
    }

    public boolean isPregnancy() {
        return pregnancy;
    }

    public void setPregnancy(boolean pregnancy) {
        this.pregnancy = pregnancy;
    }
}
