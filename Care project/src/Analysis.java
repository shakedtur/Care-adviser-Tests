public class Analysis {

    private patientData pdResults;
    //
    public Analysis(patientData pd){
        pdResults=pd;
    }
    public void diagnosis(){

        //4
        System.out.println("diagnosis results:");
        System.out.println("HTC: "+HTCscale(pdResults.getHCT()));


    }

    //4 HTC
    public Scale HTCscale(int blood){
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
}
