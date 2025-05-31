public class Card {
    private int cardId;
    private double credit;

    public Card(int cardId, double credit) {
        this.cardId = cardId;
        this.credit = credit;
    }

    public double getCredit() {
        return credit;
    }

    public int getCardId() {
        return cardId;
    }
    public void addCredit(double amount){
        credit += amount;
    }
    public void deductCredit(double amount) {
        this.credit -= amount;
    }

}
