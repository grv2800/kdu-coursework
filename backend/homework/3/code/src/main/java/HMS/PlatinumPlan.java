package HMS;

public class PlatinumPlan extends HealthInsurancePlan{
    public PlatinumPlan(){
        super(0.9);
    }
    public double computeMonthlyPremium(double salary, int age, boolean userSmokes) {
        return 0.08 * salary + getOfferedBy().computeMonthlyPremium(this, age, userSmokes);
    }
}
