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
        if(pointsEarnedperEgp < 0 || maximumpoint < 0){
            return false;
        }
        return true;
    }
}
