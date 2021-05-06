import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class YesNo extends JFrame {
    private boolean smoking;
    private boolean eastPerson;
    private boolean female;
    private int age;
    private String[] textarr={"Are you smoking?","Are you easy preson?","Are you female?"};
    private JPanel initpanel;
    private JPanel questionspanel;
    private JPanel answerpanel;
    private ArrayList <JRadioButton> anwRadiobuttons=new ArrayList<JRadioButton>();
    private JRadioButton smokRadio;
    private JRadioButton eastPersonRadio;
    private JRadioButton femaleRadio;
    private JRadioButton[] arradio={smokRadio,eastPersonRadio,femaleRadio};

    public YesNo(){
        setTitle("Yes no questions to patient");
        initpanel=new JPanel();
        answerpanel=new JPanel();
        initpanel.setLayout(new BorderLayout());

        questionspanel=new JPanel();
        questionspanel.setLayout(new BoxLayout(questionspanel,BoxLayout.Y_AXIS));
        answerpanel.setLayout(new BoxLayout(answerpanel,BoxLayout.Y_AXIS));
        for(int i=0;i<textarr.length;i++){
            JLabel qtext=new JLabel(textarr[i]);
            questionspanel.add(qtext);
        }
        for (int i=0;i< arradio.length;i++){
            arradio[i]=new JRadioButton();
            answerpanel.add(arradio[i]);
        }
//        for(JRadioButton r:anwRadiobuttons){
//            r=new JRadioButton();
//            this.answerpanel.add(r);
//            answerpanel.add(r);
//        }

//        smokRadio=new JRadioButton();
//        answerpanel.add(smokRadio);
        initpanel.add(questionspanel,BorderLayout.WEST);
        initpanel.add(answerpanel,BorderLayout.EAST);
        answerpanel.setVisible(true);
        initpanel.setVisible(true);
        add(initpanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
