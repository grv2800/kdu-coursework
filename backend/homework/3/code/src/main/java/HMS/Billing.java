package HMS;

import java.util.logging.Logger;

public class Billing {

    /**
     * Used to compute the final payment amount to paid by the patient after discounting from the coverage given by the insurance company
     * @param patient
     * @param amount
     * @return
     */
    public static double[]computePaymentAmount(Patient patient,double amount){
        double[] payments=new double[2];
        double discount=0.0;
        double insurancePayment;
        double patientPayment;

        HealthInsurancePlan patientInsurancePlan=patient.getInsurancePlan();

        if(patientInsurancePlan != null){
            if(patientInsurancePlan instanceof PlatinumPlan){
                discount=50.0;
            }
            else if(patientInsurancePlan instanceof GoldPlan){
                discount=40.0;
            }
            else if(patientInsurancePlan instanceof SilverPlan){
                discount=30.0;
            }
            else if(patientInsurancePlan instanceof BronzePlan){
                discount=25.0;
            }
            insurancePayment = amount * patientInsurancePlan.getCoverage();
            patientPayment=amount-insurancePayment-discount;
        }
        else{
            insurancePayment=0;
            patientPayment=amount-20.0;
        }

        payments[0]=insurancePayment;
        payments[1]=patientPayment;

        return payments;
    }
    public static void main(String[] args){
        final Logger logger=Logger.getLogger(Billing.class.getName());
        HealthInsurancePlan insurancePlan = new BronzePlan();
        Patient patient = new Patient();
        patient.setInsurancePlan(insurancePlan);

        double[] payments = Billing.computePaymentAmount(patient, 1000.0);
        logger.info("Amount payed by insurance company, "+payments[0]);
        logger.info("Amount to be payed by patient, "+payments[1]);

    }
}
