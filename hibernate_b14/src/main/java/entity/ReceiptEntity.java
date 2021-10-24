package entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "receipt")

public class ReceiptEntity implements Serializable {
    @Id
    @ManyToOne(targetEntity = entity.Customer.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    Customer customer;

    @Id
    @ManyToOne(targetEntity = entity.Ticket.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id", nullable = false)
    Ticket ticket;

    @Column(name = "ticket_number", nullable = false)
    int amount;
}
