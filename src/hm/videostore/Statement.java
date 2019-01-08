package hm.videostore;

import java.time.LocalDate;
import java.util.List;

public class Statement {
    private Customer customer;
    private LocalDate date;
    private List<StatementItem> statementItems;

    public Statement(Customer customer, LocalDate date, List<StatementItem> statementItems) {
        this.customer = customer;
        this.date = date;
        this.statementItems = statementItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<StatementItem> getItems() {
        return statementItems;
    }

    public double getTotal() {
        return statementItems.stream()
                             .map(StatementItem::getPrice)
                             .reduce(Double::sum)
                             .orElse(0.0);
    }
}
