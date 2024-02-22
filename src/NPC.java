public class NPC {

    private final boolean isFriendly;
    private int healthPoints;
    private int strengthPoints;

    public NPC(boolean isFriendly) {
        this.isFriendly = isFriendly;
    }

    public boolean isFriendly() {
        return isFriendly;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }


    public void setStrengthPoints(int strengthPoints) {
        this.strengthPoints = strengthPoints;
    }


    public int getHealthPoints() {
        return healthPoints;
    }

    public int getStrengthPoints() {
        return strengthPoints;
    }
}
