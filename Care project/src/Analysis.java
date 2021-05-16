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
    private static final String[] sick={"בריא","אנמיה","דיאטה","דימום","היפרליפידמיה ","הפרעה ביצירת הדם / תאי דם ","הפרעה המטולוגית ","הרעלת ברזל ","התייבשות",
    "זיהום","חוסר בויטמנים","מחלה ויראלית","מחלות בדרכי המרה ","מחלות לב ","מחלת דם ","מחלת כבד ","מחלת כליה ","מחסור בברזל","מחלות שריר ","מעשנים",
    "מחלת ריאות ","פעילות יתר של בלוטת התריס ","סוכרת מבוגרים ","סרטן ","צריכה מוגברת של בשר ","שימוש בתרופות שונות ","תת תזונה"};
    private static final String[] advice={" ",
    "שני כדורי 10 מ\"ג של B12 ביום למשך חודש  ",
    "לתאם פגישה עם תזונאי ",
    "להתפנות בדחיפות לבית החולים ",
    "לתאם פגישה עם תזונאי, כדור 5 מ\"ג של סימוביל ביום למשך שבוע ",
    "כדור 10 מ\"ג של B12 ביום למשך חודש כדור 5 מ\"ג של חומצה פולית ביום למשך חודש ",

    "זריקה של הורמון לעידוד ייצור תאי הדם האדומים ",
    "להתפנות לבית החולים ",
    "מנוחה מוחלטת בשכיבה, החזרת נוזלים בשתייה ",
    "אנטיביוטיקה ייעודית ",
    "הפנייה לבדיקת דם לזיהוי הוויטמינים החסרים ",
    "לנוח בבית ",
    "הפנייה לטיפול כירורגי ",
    "לתאם פגישה עם תזונאי ",
    "שילוב של ציקלופוספאמיד וקורטיקוסרואידים ",
    "הפנייה לאבחנה ספציפית לצורך קביעת טיפול ",
    "איזון את רמות הסוכר בדם ",
    "שני כדורי 10 מ\"ג של B12 ביום למשך חודש ",
    "שני כדורי 5 מ\"ג של כורכום c3 של אלטמן ביום למשך חודש ",
    "להפסיק לעשן ",
    "להפסיק לעשן / הפנייה לצילום רנטגן של הריאות ",
    "Propylthiouracil להקטנת פעילות בלוטת התריס ",
    "התאמת אינסולין למטופל ",
    "Entrectinib - אנטרקטיניב",
    "לתאם פגישה עם תזונאי ",
    "הפנייה לרופא המשפחה לצורך בדיקת התאמה בין התרופות ",
    "לתאם פגישה עם תזונאי"};
    //
    public Analysis(patientData pd){
        pdResults=pd;
        arrScale=new Scale[datanumber];
        for(Scale s:arrScale){
            s=Scale.NOTMATCH;
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
        //TODO remove note!!
        //JOptionPane.showMessageDialog(new JFrame(), Summary, ":)", JOptionPane.NO_OPTION);

        return data;
    }
//    private void stam(){
//        diagnosis();
//        SickAnalysis(0);
//        Adviser(0);
//    }

    public void diagnosis(){
        //0
        arrScale[0]=WBCScale0(pdResults.getWBC());
        arrScale[1]=Neut1(pdResults.getNeut());
        arrScale[2]=Lymph2(pdResults.getLymph());
        arrScale[3]=RBCscale3(pdResults.getRBC());
        //4
        arrScale[4]=HTCscale4(pdResults.getHCT());
        arrScale[5]=UreaScale5(pdResults.getUrea());

        for (int i=0;i<arrScale.length;i++){
            if(arrScale[i]!=Scale.NORMAL){
                IrregularValue[i]=true;
            }
        }

    }

    public String SickAnalysis(int number){
        switch (number){
            case 0:
                if(arrScale[0]==Scale.HIGH)
                    return spaceadder(new String[]{sick[9],sick[8],sick[14],sick[23]});
                else if(arrScale[0]==Scale.LOW)
                    return spaceadder(new String[]{sick[11],"כשל במערכת החיסון",sick[23]});
                break;
            case 1:
                if (arrScale[1]==Scale.HIGH)
                    return sick[9];
                else
                    return spaceadder(new String[]{sick[5],sick[9],sick[23]});
            case 2:
                if (arrScale[2]==Scale.HIGH)
                    return sick[5];
                else
                    return spaceadder(new String[]{sick[9],sick[23]});
            case 3:
                if (arrScale[3]==Scale.HIGH){
                    if(pdResults.Yesnoanswer.isSmoking())
                        return spaceadder(new String[]{sick[5],sick[19],sick[20]});
                    else
                        return sick[5];
                }
                else
                    return spaceadder(new String[]{sick[1],sick[3]});
            case 4:
                if (arrScale[2]==Scale.HIGH)
                    return sick[19];
                else if(arrScale[4]==Scale.LOW)
                    return spaceadder(new String[]{sick[1],sick[3]});
                break;
            case 5:
                if (arrScale[5]==Scale.HIGH)
                    return spaceadder(new String[]{sick[2],sick[8],sick[16]});
                else{
                    if(pdResults.getYesnoanswer().isPregnancy())
                        return "בהריון כנראה";
                    return spaceadder(new String[]{sick[26],sick[2],sick[15]});
                }


        }
        return "";
    }
    public String Adviser(int num){
        switch (num){
            case 0:
                if(arrScale[0]==Scale.HIGH)
                    return spaceadder(new String[]{advice[9],advice[8],advice[14],advice[23]});
                else if(arrScale[0]==Scale.LOW)
                    return spaceadder(new String[]{advice[11],advice[23]});
                break;
            case 1:
                if (arrScale[1]==Scale.HIGH)
                    return advice[9];
                else
                    return spaceadder(new String[]{advice[5],advice[9],advice[23]});
            case 2:
                if (arrScale[2]==Scale.HIGH)
                    return advice[5];
                else
                    return spaceadder(new String[]{advice[9],advice[23]+"לימפומה"});
            case 3:
                if (arrScale[3]==Scale.HIGH){
                    if(pdResults.Yesnoanswer.isSmoking())
                        return spaceadder(new String[]{advice[5],advice[19],advice[20]});
                    else
                        return advice[5];
                }
                else
                    return spaceadder(new String[]{advice[1],advice[3]});
            case 4:
                if(arrScale[4]==Scale.HIGH){
                    if(pdResults.getYesnoanswer().isSmoking()==true) {
                        return  advice[19];
                    }
                }
                else {
                    return advice[1]+advice[3];
                }
                break;
            case 5:
                if (arrScale[5]==Scale.HIGH)
                    return spaceadder(new String[]{advice[2],advice[8],advice[16]});
                else{
                    if(pdResults.getYesnoanswer().isPregnancy())
                        return spaceadder(new String[]{advice[26],advice[2],advice[15]+"בהריון רמת השתנן יורדת"});
                    return spaceadder(new String[]{advice[26],advice[2],advice[15]});
                }

            default:
                break;


        }
        return "";

    }
    //0 WBC
    public Scale WBCScale0(int cell){
        int gil=pdResults.getAge();
        if(gil>=18){
            if(4500<=cell&&cell<=11000)
                return Scale.NORMAL;
            else if(11000<cell)
                return Scale.HIGH;
            else return Scale.LOW;
        }
        else if(4<=gil&& gil<=17){
            if(5500<=cell&&cell<=15500)
                return Scale.NORMAL;
            else if(15500<cell)
                return Scale.HIGH;
            else return Scale.LOW;
        }
        else if(0<=gil&&gil<=3){
            if(6000<=cell&&cell<=17500)
                return Scale.NORMAL;
            else if(17500<cell)
                return Scale.HIGH;
            else return Scale.LOW;
        }
        return Scale.NOTMATCH;
    }
    //1 Neut
    public Scale Neut1(int neutrophil){
        if(28<=neutrophil && neutrophil<=54){
            return Scale.NORMAL;
        }
        else if(54<neutrophil)
            return Scale.HIGH;
        else
            return Scale.LOW;
    }
    //2
    public Scale Lymph2(int lmp){
        if(36<=lmp && lmp<=52){
            return Scale.NORMAL;
        }
        else if(52<lmp)
            return Scale.HIGH;
        else
            return Scale.LOW;
    }
    //3 RBC
    public Scale RBCscale3(int rbc){
        if(4.5<=rbc && rbc<=6){
            return Scale.NORMAL;
        }
        else if(6<rbc)
            return Scale.HIGH;
        else
            return Scale.LOW;
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
    //5 Urea
    public Scale UreaScale5(int urea){
        float min=17,max=43;
        float temp=urea;
        if(pdResults.Yesnoanswer.isEastPerson()) {
            temp = (float) (urea * 1.1);
            min*=1.1;
            max*=1.1;
        }
        if(min<=temp && temp<=max){
            return Scale.NORMAL;
        }
        else if(max<temp)
            return Scale.HIGH;
        else
            return Scale.LOW;


    }

    public String spaceadder(String[] str){
        String temp=new String();
        for(int i=0;i<str.length;i++){
            temp+=str[i]+", ";
        }
        return temp;
    }
    public String printScale(Scale s){
        if(s==Scale.LOW){
            return "LOW";
        }
        else if(s==Scale.HIGH){
            return "HIgh";
        }
        else if(s==Scale.NORMAL)
            return "NORMAL";
        return "not match";
    }


}
