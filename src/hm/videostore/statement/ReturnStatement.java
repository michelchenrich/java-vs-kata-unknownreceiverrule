package hm.videostore.statement;

import hm.videostore.renting.api.Renter;
import hm.videostore.statement.api.Statement;
import hm.videostore.statement.api.StatementItem;
import java.time.LocalDate;
import java.util.List;

public class ReturnStatement implements Statement {
    private Renter renter;
    private LocalDate date;
    private List<StatementItem> statementItems;

    public ReturnStatement(Renter renter, LocalDate date, List<StatementItem> statementItems) {
        this.renter = renter;
        this.date = date;
        this.statementItems = statementItems;
    }

    public Renter getRenter() {
        return renter;
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
