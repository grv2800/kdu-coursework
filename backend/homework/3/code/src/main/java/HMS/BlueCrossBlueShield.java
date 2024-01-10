package HMS;

public class BlueCrossBlueShield implements InsuranceBrand{
    /**
     * Computes the montly premium to be paid by the staff , taking into consideration age and smoking parameters
     * @param insurancePlan
     * @param age
     * @param smoking
     * @return
     */
    public double computeMonthlyPremium(HealthInsurancePlan insurancePlan, int age, boolean smoking) {
        double result=0.0;
        if(insurancePlan instanceof PlatinumPlan){
            if(age >55) result+=200.0;
            if(smoking) result+=100.0;
        }
        else if(insurancePlan instanceof GoldPlan){
            if(age > 55) result+=150.0;
            if(smoking)result+=90.0;
        }
        else if (insurancePlan instanceof SilverPlan){
            if(age > 55) result+=100.0;
            if(smoking) result+=90.0;
        }
        else if(insurancePlan instanceof BronzePlan){
            if(age > 55)result+=50.0;
            if(smoking) result+=70.0;
        }
        return result;
    }
}
