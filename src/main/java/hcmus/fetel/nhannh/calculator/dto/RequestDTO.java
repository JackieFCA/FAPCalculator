package hcmus.fetel.nhannh.calculator.dto;

public class RequestDTO {
    private int numAdpack;
    private int duration;
    private boolean isReinvest;

    public RequestDTO(int numAdpack, int duration, boolean isReinvest) {
        super();
        this.numAdpack = numAdpack;
        this.duration = duration;
        this.isReinvest = isReinvest;
    }

    public int getNumAdpack() {
        return numAdpack;
    }

    public void setNumAdpack(int numAdpack) {
        this.numAdpack = numAdpack;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isReinvest() {
        return isReinvest;
    }

    public void setReinvest(boolean isReinvest) {
        this.isReinvest = isReinvest;
    }

}
