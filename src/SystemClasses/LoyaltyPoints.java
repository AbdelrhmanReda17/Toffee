package SystemClasses;

import java.util.Scanner;


/**
 * The LoyaltyPoints class represents the loyalty points system in the Toffee shop.
 */
public class LoyaltyPoints {
    private Double pointsEarnedperEgp;
    private int maximumpoint;

    private DataManager Data;

    /**
     * Constructs a LoyaltyPoints object with the specified DataManager.
     * @param data The DataManager object to be associated with the LoyaltyPoints.
     */
    public LoyaltyPoints(DataManager data) {
        this.Data=data;
    }

    /**
     * Default constructor for the LoyaltyPoints class.
     */
    public LoyaltyPoints(){

    }

    /**
     * Constructs a LoyaltyPoints object with the specified points earned per EGP and maximum points.
     * @param pointsEarnedperEgp The points earned per EGP in the loyalty program.
     * @param maximumpoint The maximum points that can be earned via one order in the loyalty program.
     */
    public LoyaltyPoints(Double pointsEarnedperEgp, int maximumpoint) {
        this.pointsEarnedperEgp = pointsEarnedperEgp;
        this.maximumpoint = maximumpoint;
    }

    /**
     * Returns the maximum points that can be earned via one order in the loyalty program.
     * @return The maximum points that can be earned via one order.
     */
    public int getMaximumpoint() {
        return maximumpoint;
    }

    /**
     * Sets the maximum points that can be earned via one order in the loyalty program.
     * @param maximumpoint The maximum points to be set.
     */
    public void setMaximumpoint(int maximumpoint) {
        this.maximumpoint = maximumpoint;
    }

    /**
     * Returns the points earned per EGP in the loyalty program.
     * @return The points earned per EGP.
     */
    public Double getPointsEarnedperEgp() {
        return pointsEarnedperEgp;
    }

    /**
     * Sets the points earned per EGP in the loyalty program.
     * @param pointsEarnedperEgp The points earned per EGP to be set.
     */
    public void setPointsEarnedperEgp(Double pointsEarnedperEgp) {
        this.pointsEarnedperEgp = pointsEarnedperEgp;
    }

    /**
     * Checks if the loyalty points system is valid.
     * @return True if the loyalty points system is valid, false otherwise.
     */
    public boolean checkLoyaltyPoints() {
        if(pointsEarnedperEgp < 0 || maximumpoint < 0){
            return false;
        }
        return true;
    }

    /**
 * Sets the loyalty points system by taking user input for the points earned per EGP and maximum points.
 * @param loyaltyPoints The LoyaltyPoints object to be updated with the new values.
 */
public void setLoyaltyPointsSystem(LoyaltyPoints loyaltyPoints) {
    System.out.print("Enter the points per EGP: ");
    Double pointsEarned = new Scanner(System.in).nextDouble();
    System.out.print("Enter the maximum points Via one Order: ");
    int maximumPoint = new Scanner(System.in).nextInt();

    loyaltyPoints.setPointsEarnedperEgp(pointsEarned);
    loyaltyPoints.setMaximumpoint(maximumPoint);

    if (loyaltyPoints.checkLoyaltyPoints()) {
        System.out.println("Loyalty Schema Added Successfully");
    }
    Data.setLoyaltyScheme(loyaltyPoints);
}


}
