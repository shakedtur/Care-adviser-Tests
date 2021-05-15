public enum Scale {
    LOW,NORMAL,HIGH;

    public String stam(){
        if(this==LOW){
            return "LOW";
        }
        else if(this==HIGH){
            return "HIgh";
        }
        return "NORMAL";
    }
    @Override
    public String toString() {
        if(this==LOW){
            return "LOW";
        }
        else if(this==HIGH){
        return "HIgh";
        }
        return "NORMAL";
    }
}
