package SystemClasses;

import java.util.Scanner;

public class LoyaltyPoints {
    private Double pointsEarnedperEgp;
    private int maximumpoint;

    private DataManager Data;
    public LoyaltyPoints(DataManager data) {
        this.Data=data;

    }
    public LoyaltyPoints(){

    }

    public LoyaltyPoints(Double pointsEarnedperEgp, int maximumpoint) {
        this.pointsEarnedperEgp = pointsEarnedperEgp;
        this.maximumpoint = maximumpoint;
    }


    public int getMaximumpoint() {
        return maximumpoint;
    }

    public void setMaximumpoint(int maximumpoint) {
        this.maximumpoint = maximumpoint;
    }

    public Double getPointsEarnedperEgp() {
        return pointsEarnedperEgp;
    }

    public void setPointsEarnedperEgp(Double pointsEarnedperEgp) {
        this.pointsEarnedperEgp = pointsEarnedperEgp;
    }

    public boolean checkLoyaltyPoints() {
        if(pointsEarnedperEgp < 0 || maximumpoint < 0){
            return false;
        }
        return true;
    }
    public void setLoyaltyPointsSystem(LoyaltyPoints loyalityPoints) {
        System.out.print("Enter the points per EGP: ");
        Double pointsEarned = new Scanner(System.in).nextDouble();
        System.out.print("Enter the maximum points Via one Order: ");
        int maximumPoint = new Scanner(System.in).nextInt();

        loyalityPoints.setPointsEarnedperEgp(pointsEarned);
        loyalityPoints.setMaximumpoint(maximumPoint);

        if (loyalityPoints.checkLoyaltyPoints()) {
            System.out.println("Loyalty Schema Added Successfully");
        }
        Data.setLoyaltyScheme(loyalityPoints);
    }

}
