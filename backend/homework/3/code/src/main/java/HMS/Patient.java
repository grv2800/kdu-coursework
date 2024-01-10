package HMS;

public class Patient extends User{
    private long patientId;
    private boolean insured;
    public void setPatientId(long patientId){
        this.patientId=patientId;
    }

    /**
     *
     *
     * @return
     */
    public long getPatientId(){
        return patientId;
    }
    public void setInsured(boolean insured){
        this.insured=insured;
    }
    public boolean getInsured(){
        return insured;
    }


}
