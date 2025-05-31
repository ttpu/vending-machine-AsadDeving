import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
    Map<String, Bevrages> bevrages = new HashMap<>();
    Map<Integer, Card> cards = new HashMap<>();
    Map<Integer, Column> columns = new HashMap<>();

    public void addBeverage(String name, double price) {
        Bevrages bevrage = new Bevrages(name,price);
        bevrages.put(name,bevrage);
    }

    public double getPrice(String beverageName) {
        Bevrages bevrage = bevrages.get(beverageName);
        if (bevrage != null){
            return bevrage.getPrice();
        }
        return -1;
    }

    public void rechargeCard(int cardId, double credit) {
        Card card = cards.get(cardId);
        if (card == null){
            card=new Card(cardId,credit);
            cards.put(cardId,card);
        }
        else {
            card.addCredit(credit);
        }
    }

    public double getCredit(int cardId) {
        Card card = cards.get(cardId);
        if (card != null){
            return card.getCredit();
        }
        return -1;
    }

    public void refillColumn(int column, String beverageName, int cans) {
        Column col = columns.get(column);
        if (col == null) {
            col = new Column(beverageName, cans);
            columns.put(column, col);
        } else {
            if (col.getBeverage().equals(beverageName)) {
                col.setCans(col.getCans() + cans);
            } else {
                System.out.println("Column already has a different beverage.");
            }
        }
    }


    public int availableCans(String beverageName) {
        int total = 0;
        for (Column column : columns.values()) {
            if (column.getBeverage().equals(beverageName)) {
                total += column.getCans();
            }
        }
        return total;
    }


    public int sell(String beverageName, int cardId) {
        Bevrages beverage = bevrages.get(beverageName);
        if (beverage == null) {
            System.out.println("Beverage not found.");
            return 0;
        }

        Card card = cards.get(cardId);
        if (card == null) {
            System.out.println("Card not found.");
            return 0;
        }

        double price = beverage.getPrice();
        if (card.getCredit() < price) {
            System.out.println("Insufficient credit.");
            return 0;
        }

        for (Column column : columns.values()) {
            if (column.getBeverage().equals(beverageName) && column.getCans() > 0) {
                card.deductCredit(price);
                column.setCans(column.getCans() - 1);
                System.out.println("Sold 1 " + beverageName + ". Remaining cans in column: " + column.getCans());
                return 1;
            }
        }
        System.out.println("No cans available for " + beverageName);
        return 0;
    }
}
