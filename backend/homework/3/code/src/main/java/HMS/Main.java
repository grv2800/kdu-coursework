package HMS;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        final Logger logger=Logger.getLogger(Main.class.getName());
        User staff=new User();
        InsuranceBrand insuranceBrand=new BlueCrossBlueShield();
        HealthInsurancePlan insurancePlan=new PlatinumPlan();
        insurancePlan.setOfferedBy(insuranceBrand);
        staff.setInsurancePlan(insurancePlan);
        logger.info(String.valueOf(insurancePlan.computeMonthlyPremium(10000,57,true)));
    }
}