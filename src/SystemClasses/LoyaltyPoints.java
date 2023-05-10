package SystemClasses;

public class LoyaltyPoints {
    private Double pointsEarnedperEgp;
    private int maximumpoint;

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



}
