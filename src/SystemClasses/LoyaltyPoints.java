package SystemClasses;

public class LoyaltyPoints {
    private int pointsEarnedperEgp;
    private int maximumpoint;

    public LoyaltyPoints(int pointsEarnedperEgp, int maximumpoint) {
        this.pointsEarnedperEgp = pointsEarnedperEgp;
        this.maximumpoint = maximumpoint;
    }

    public int getMaximumpoint() {
        return maximumpoint;
    }

    public void setMaximumpoint(int maximumpoint) {
        this.maximumpoint = maximumpoint;
    }

    public int getPointsEarnedperEgp() {
        return pointsEarnedperEgp;
    }

    public void setPointsEarnedperEgp(int pointsEarnedperEgp) {
        this.pointsEarnedperEgp = pointsEarnedperEgp;
    }

    public boolean checkLoyaltyPoints() {
        // Add logic to check if loyalty points are available for redemption
        // Return true if points can be redeemed, false otherwise
        // Add your specific implementation here
        return true;
    }
}
