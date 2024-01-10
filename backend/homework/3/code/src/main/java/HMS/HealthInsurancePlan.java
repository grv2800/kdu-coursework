package HMS;

public abstract class HealthInsurancePlan {
    private double coverage;
    private InsuranceBrand offeredBy;
    public HealthInsurancePlan(double coverage){
        this.coverage=coverage;
    }
    public void setCoverage(double coverage){
        this.coverage=coverage;
    }
    public double getCoverage(){
        return coverage;
    }
    public InsuranceBrand getOfferedBy(){
        return offeredBy;
    }
    public void setOfferedBy(InsuranceBrand offeredBy){
        this.offeredBy=offeredBy;
    }
    public abstract double computeMonthlyPremium(double salary, int age, boolean userSmokes);


}
