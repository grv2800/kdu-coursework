package HMS;

public class Doctor extends Staff{
    private long doctorId;
    private String specialization;
    public void setDoctorId(long doctorId){
        this.doctorId=doctorId;
    }
    public long getDoctorId(){
        return doctorId;
    }
    public void setSpecialization(String specialization){
        this.specialization=specialization;
    }
    public String getSpecialization(){
        return specialization;
    }
}
