import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.*;
import java.io.*;

public class Analysis {
    private final static String PATHFILE="data.txt";
    private patientData pdResults;
    private final int datanumber=11;
    private Scale[] arrScale;
    private boolean[] IrregularValue;

    //table\
    private final int linesnumber=11;
    private static final String[] columnNames = { "Result name", "proper", "Sick", "recomend Care" };
    private JTable infoTable;
    private JScrollPane scrollPane;
    private String Summary="";
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
        try {
            tableConstructor();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scrollPane = new JScrollPane(infoTable);

    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    private void tableConstructor() throws FileNotFoundException {
        infoTable = new JTable(rowConstructor(), columnNames);
        //infoTable.setGridColor(Color.cyan);
        infoTable.setBackground(Color.WHITE);

    }
    private Object[][] rowConstructor() throws FileNotFoundException {
        Object[][] data = new Object[linesnumber][columnNames.length];
        String[] paramterNames=pdResults.getParamtername();
        int count=1;
        for(int i=0;i<linesnumber;i++){
            int j=0;
            data[i][j++]=paramterNames[i];
            data[i][j++]=printScale(arrScale[i]);
            if(IrregularValue[i]) {
                Summary += " " + count+".";
                String sicktemp=SickAnalysis(i);
                data[i][j++]=sicktemp;
                Summary += " " + sicktemp + "\n"+"Recommend Care: \n";
                String tempadvice = Adviser(i);
                data[i][j++] = tempadvice;
                Summary += " " + tempadvice + "\n"+"====================\n";
                count++;
            }
        }
        //TODO remove note!!
        JOptionPane.showMessageDialog(new JFrame(), Summary, ":)", JOptionPane.NO_OPTION);
        saveDataFile();
        return data;
    }

    public void saveDataFile() throws FileNotFoundException{

        Writer writer = null;
        File check = new File("data.txt");
        if(!check.exists()) {
            //Checks if the file exists. will not add anything if the file does exist.
            try {
                File texting = new File("data.txt");
                writer = new BufferedWriter(new FileWriter(texting));
                writer.write("message");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try {
            File filedata = new File(PATHFILE);
            Scanner scan = new Scanner(filedata);
            FileWriter filewriter = new FileWriter(filedata, true);
            filewriter.write("++++++++++++++++++++++++++++++++++\n"
                    +"ID: "+pdResults.getId()+"\n"
                    +"Name: "+ pdResults.getFirstname()
                    +" "+pdResults.getLastname()+"\n"
                    +Summary
                    +"++++++++++++++++++++++++++++++++++\n");
            filewriter.close();
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }


    }

    public void diagnosis(){
        //0
        arrScale[0]=WBCScale0(pdResults.getWBC());
        arrScale[1]=Neut1(pdResults.getNeut());
        arrScale[2]=Lymph2(pdResults.getLymph());
        arrScale[3]=RBCscale3(pdResults.getRBC());
        //4
        arrScale[4]=HTCscale4(pdResults.getHCT());
        arrScale[5]=UreaScale5(pdResults.getUrea());
        arrScale[6]=Hbscale6(pdResults.getHB());
        arrScale[7]=KeratinScale7(pdResults.getKeratin());
        arrScale[8]=Ironscale8(pdResults.getIron());
        arrScale[9]=HDLscale9(pdResults.getHDL());
        arrScale[10]=Alkalinescale10(pdResults.getAlkaline());
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
                if (arrScale[4]==Scale.HIGH)
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
            case 6:
                if (arrScale[6] == Scale.LOW)
                {
                    return spaceadder(new String[]{sick[1],sick[6],sick[17]});
                }

            case 7:
                if (arrScale[7] == Scale.LOW)
                {
                    return sick[26];
                }
                if (arrScale[7] == Scale.HIGH)
                {
                    return spaceadder(new String[]{sick[16],sick[18],sick[24]});
                }

            case 8:
                if (arrScale[8] == Scale.HIGH)
                {
                    return sick[7];
                }
                if (arrScale[8] == Scale.LOW)
                {
                    return sick[26];
                }

            case 9:
                if (arrScale[9] == Scale.LOW)
                {
                    return spaceadder(new String[]{sick[4],sick[13],sick[22]});
                }

            case 10:
                if (arrScale[10] == Scale.HIGH)
                {
                    return spaceadder(new String[]{sick[12],sick[15],sick[21],sick[25]});
                }
                if (arrScale[10] == Scale.LOW)
                {
                    return spaceadder(new String[]{sick[10],sick[26]});
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
            case 6:
                if (arrScale[6] == Scale.LOW)
                {
                    return spaceadder(new String[]{advice[1],advice[6],advice[17]});
                }

            case 7:
                if (arrScale[7] == Scale.LOW)
                {
                    return advice[26];
                }
                if (arrScale[7] == Scale.HIGH)
                {
                    return spaceadder(new String[]{advice[16],advice[18],advice[24]});
                }

            case 8:
                if (arrScale[8] == Scale.HIGH)
                {
                    return advice[7];
                }
                if (arrScale[8] == Scale.LOW)
                {
                    return advice[26];
                }

            case 9:
                if (arrScale[9] == Scale.LOW)
                {
                    return spaceadder(new String[]{advice[4],advice[13],advice[22]});
                }

            case 10:
                if (arrScale[10] == Scale.HIGH)
                {
                    return spaceadder(new String[]{advice[12],advice[15],advice[21],advice[25]});
                }
                if (arrScale[10] == Scale.LOW)
                {
                    return spaceadder(new String[]{advice[10],advice[26]});
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
            else if(47<blood)
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
    //6  hb
    public Scale Hbscale6(int hb)
    {
        int gil=pdResults.getAge();
        if(gil<= 17)
        {
            if(hb >= 11.5 && hb <= 15.5)
            {
                return Scale.NORMAL;
            }
            else if(hb < 11.5)
                return Scale.LOW;
            return Scale.HIGH;
        }

        else if(pdResults.getYesnoanswer().isFemale())
        {
            if(hb >= 12 && hb <= 16)
            {
                return Scale.NORMAL;
            }
            else if (hb < 12)
            {
                return Scale.LOW;
            }
        }
        else
        {
            if(hb >= 12 && hb <= 18)
            {
                return Scale.NORMAL;
            }
            else if (hb < 12)
            {
                return Scale.LOW;
            }
            return Scale.HIGH;
        }
        return Scale.HIGH;
    }
    //7 keratin
    public Scale KeratinScale7(int Keratin)
    {
        int gil=pdResults.getAge();
        if(gil <=2)
        {
            if (Keratin >= 0.2 && Keratin <= 0.5)
            {
                return Scale.NORMAL;
            }
            else if (Keratin>0.5)
            {
                return Scale.HIGH;
            }
            else return Scale.LOW;
        }
        else if (gil <= 17)
        {
            if (Keratin >= 0.5 && Keratin <= 1)
            {
                return Scale.NORMAL;
            }
            else if (Keratin>1)
            {
                return Scale.HIGH;
            }
            else return Scale.LOW;
        }
        else if (gil <= 59)
        {
            if (Keratin >= 0.6 && Keratin <= 1)
            {
                return Scale.NORMAL;
            }
            else if (Keratin>1)
            {
                return Scale.HIGH;
            }
            else return Scale.LOW;
        }
        else
        {
            if (Keratin >= 0.6 && Keratin <= 1.2)
            {
                return Scale.NORMAL;
            }
            else if (Keratin>1.2)
            {
                return Scale.HIGH;
            }
            else return Scale.LOW;
        }
    }

    //8 iron
    public Scale Ironscale8(int iron)
    {
        if(pdResults.getYesnoanswer().isFemale())
        {
            if(iron >= 0.8 * 60 &&	iron <= 0.8 * 160)
            {
                return Scale.NORMAL;
            }
            else if (iron > 0.8 * 160)
            {
                return Scale.HIGH;
            }
            else return Scale.LOW;
        }
        else
        {
            if(iron >= 60 && iron <= 160)
            {
                return Scale.NORMAL;
            }
            else if (iron > 160)
            {
                return Scale.HIGH;
            }
            else return Scale.LOW;
        }
    }

    //9
    public Scale HDLscale9(int hdl)
    {
        if(pdResults.getYesnoanswer().isEthiopian())
        {
            if(pdResults.getYesnoanswer().isFemale())
            {
                if(hdl <= 82*1.2 && hdl >= 34*1.2)
                {
                    return Scale.NORMAL	;
                }
                else if(hdl > 82*1.2)
                {
                    return Scale.HIGH;
                }
                else return Scale.LOW;
            }
            else
            {
                if(hdl <= 62*1.2 && hdl >= 29*1.2)
                {
                    return Scale.NORMAL	;
                }
                else if(hdl > 62*1.2)
                {
                    return Scale.HIGH;
                }
                else return Scale.LOW;
            }
        }
        else
        {
            if(pdResults.getYesnoanswer().isFemale())
            {
                if(hdl <= 82 && hdl >= 34)
                {
                    return Scale.NORMAL	;
                }
                else if(hdl > 82)
                {
                    return Scale.HIGH;
                }
                else return Scale.LOW;
            }
            else
            {
                if(hdl <= 62 && hdl >= 29)
                {
                    return Scale.NORMAL	;
                }
                else if(hdl > 62)
                {
                    return Scale.HIGH;
                }
                else return Scale.LOW;
            }
        }
    }

    //10
    //Alkalinescale10
    public Scale Alkalinescale10(int alkaline)
    {
        if(pdResults.getYesnoanswer().isEastPerson())
        {
            if(alkaline >= 60 && alkaline <= 120)
            {
                return Scale.NORMAL	;
            }
            else if(alkaline > 120)
            {
                return Scale.HIGH;
            }
            else return Scale.LOW;
        }
        else
        {
            if(alkaline >= 30 && alkaline <= 90)
            {
                return Scale.NORMAL	;
            }
            else if(alkaline > 90)
            {
                return Scale.HIGH;
            }
            else return Scale.LOW;
        }
    }

    public String spaceadder(String[] str){
        String temp=new String();
        for(int i=0;i<str.length;i++){
            temp+=str[i]+", ";
        }
        return temp+="\n";
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
