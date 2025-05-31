public class Column {
    private int cans;
    private String beverage;
    public Column(String beverage, int cans){
        this.beverage=beverage;
        this.cans=cans;
    }

    public String getBeverage() {
        return beverage;
    }
    public int getCans() {
        return cans;
    }
    public void setCans(int cans) {
        this.cans = cans;
    }
}
