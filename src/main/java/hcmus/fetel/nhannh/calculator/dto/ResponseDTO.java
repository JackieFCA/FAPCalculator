package hcmus.fetel.nhannh.calculator.dto;

public class ResponseDTO {
    private int day;
    private double totalCapital;
    private int adpackNum;
    private double dailyProfit;
    private double totalProfit;
    private double accountBalance;
    private int newAdpack;
    private int reduceAdpack;

    public ResponseDTO() {
        super();
    }

    public ResponseDTO(int day, double totalCapital, int adpackNum, double dailyProfit, double totalProfit,
            double accountBalance, int newAdpack, int reduceAdpack) {
        this.day = day;
        this.totalCapital = totalCapital;
        this.adpackNum = adpackNum;
        this.dailyProfit = dailyProfit;
        this.totalProfit = totalProfit;
        this.accountBalance = accountBalance;
        this.newAdpack = newAdpack;
        this.reduceAdpack = reduceAdpack;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getTotalCapital() {
        return totalCapital;
    }

    public void setTotalCapital(double totalCapital) {
        this.totalCapital = totalCapital;
    }

    public int getAdpackNum() {
        return adpackNum;
    }

    public void setAdpackNum(int adpackNum) {
        this.adpackNum = adpackNum;
    }

    public double getDailyProfit() {
        return dailyProfit;
    }

    public void setDailyProfit(double dailyProfit) {
        this.dailyProfit = dailyProfit;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public int getNewAdpack() {
        return newAdpack;
    }

    public void setNewAdpack(int newAdpack) {
        this.newAdpack = newAdpack;
    }

    public int getReduceAdpack() {
        return reduceAdpack;
    }

    public void setReduceAdpack(int reduceAdpack) {
        this.reduceAdpack = reduceAdpack;
    }

}
