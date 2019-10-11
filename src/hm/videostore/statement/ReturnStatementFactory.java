package hm.videostore.statement;

import hm.videostore.renting.api.Renter;
import hm.videostore.statement.api.Statement;
import hm.videostore.statement.api.StatementFactory;
import hm.videostore.statement.api.StatementItem;
import java.time.LocalDate;
import java.util.List;

public class ReturnStatementFactory implements StatementFactory {
    public Statement makeStatement(Renter renter, LocalDate returnDate, List<StatementItem> statementItems) {
        return new ReturnStatement(renter, returnDate, statementItems);
    }
}
