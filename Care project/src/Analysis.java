import javax.swing.*;
import java.awt.*;

public class Analysis {

    private patientData pdResults;
    private final int datanumber=11;
    private Scale[] arrScale;
    private boolean[] IrregularValue;

    //table\
    private final int linesnumber=11;
    private static final String[] columnNames = { "Result name", "proper", "Sick", "recomend Care" };
    private JTable infoTable;
    private JScrollPane scrollPane;
    private String Summary;
    private static final String[] sick={"בריא","אנמיה","דיאטה","דימום"};
    private static final String[] advice={" ",
    "שני כדורי 10 מ\"ג של B12 ביום למשך חודש  ",
    "לתאם פגישה עם תזונאי ",
    "להתפנות בדחיפות לבית החולים "};
    //
    public Analysis(patientData pd){
        pdResults=pd;
        arrScale=new Scale[datanumber];
        for(Scale s:arrScale){
            s=Scale.NORMAL;
        }
        IrregularValue=new boolean[datanumber];
        for(boolean b:IrregularValue){
            b=false;
        }
        diagnosis();
        tableConstructor();
        scrollPane = new JScrollPane(infoTable);

    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    private void tableConstructor() {
        infoTable = new JTable(rowConstructor(), columnNames);
    }
    private Object[][] rowConstructor() {
        Object[][] data = new Object[linesnumber][columnNames.length];
        String[] paramterNames=pdResults.getParamtername();
        for(int i=0;i<linesnumber;i++){
            int j=0;
            data[i][j++]=paramterNames[i];
            data[i][j++]=printScale(arrScale[i]);
            if(IrregularValue[i]) {
                data[i][j++] = SickAnalysis(i);
                String temp = Adviser(i);
                data[i][j++] = temp;
                Summary += " " + temp + "\n";
            }
        }
        //JOptionPane.showMessageDialog(new JFrame(), Summary, ":)", JOptionPane.NO_OPTION);

        return data;
    }

    public void diagnosis(){

        //4
        arrScale[4]=HTCscale4(pdResults.getHCT());
        for (int i=0;i<arrScale.length;i++){
            if(arrScale[i]!=Scale.NORMAL){
                IrregularValue[i]=true;
            }
//            if(arrScale[i]==Scale.HIGH){
//               if(pdResults.getYesnoanswer().isSmoking()==true){
//                   System.out.println("you need to stop smoking");
//               }
//            }
//            else if(arrScale[i]==Scale.LOW){
//                System.out.println("probebly דימום או אנמיה");
//            }
        }

    }

    public String SickAnalysis(int number){
        switch (number){
            case 0:
                break;
            case 4:
                if(arrScale[4]==Scale.LOW)
                    return sick[1]+" "+sick[3];
                break;
        }
        return "";
    }
    public String Adviser(int num){
        switch (num){
            case 0:
                break;

            case 4:
                if(arrScale[4]==Scale.HIGH){
                    if(pdResults.getYesnoanswer().isSmoking()==true) {
                        return  "stop smoking!!!";
                    }
                }
                else {
                    return advice[1]+advice[3];
                }


            default:
                return "";

        }
        return "";

    }

    //4 HTC
    public Scale HTCscale4(int blood){
        if(!pdResults.getYesnoanswer().isFemale()) {//man
            if (37 <= blood && blood <= 54) {
                return Scale.NORMAL;
            }
            else if(54<blood)
                return Scale.HIGH;
            else
                return Scale.LOW;
        }
        else {//woman
            if (33 <= blood && blood <= 47) {
                return Scale.NORMAL;
            }
            else if(54<blood)
                return Scale.HIGH;
            else
                return Scale.LOW;

        }
    }

    public String printScale(Scale s){
        if(s==Scale.LOW){
            return "LOW";
        }
        else if(s==Scale.HIGH){
            return "HIgh";
        }
        return "NORMAL";
    }


}
