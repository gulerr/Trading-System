package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Currency;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Wallet implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private Double amount;
    @Column
    private Double balance;
    @Column
    private Currency currency;


    @ManyToOne
    private Customer customer;

    @ManyToOne
    @JoinColumn(name= "coin_id")
    private Coin coin;
}
