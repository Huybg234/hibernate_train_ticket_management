package receiptTable;

import entity.Customer;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Receipt {
    Customer customer;
    List<TicketTable> ticketTables = new ArrayList<>();
    int sum;
    float priceTotal;

    public Receipt(Customer customer, List<TicketTable> ticketTables) {
        this.customer = customer;
        this.ticketTables = ticketTables;
    }
}
