package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private Double amount;
    @Column
    private Date date;


    @ManyToMany
    @JoinTable(name= "payment_card", joinColumns = @JoinColumn(name="payment_id"),
            inverseJoinColumns = @JoinColumn(name="card_id"))
    private List<Card> cards;


    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;
}
