package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String type;
    @Column
    private Double amount;
    @Column
    private Double price;
    @Column
    private String status;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    public Wallet getWallet() {
        return wallet;
    }
    public void setWallet(Wallet wallet) {
        this.wallet = wallet;

    }public Coin getCoin() {
        return coin;

    }public void setCoin(Coin coin) {
        this.coin = coin;
    }


    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @ManyToOne
    @JoinColumn(name = "coin_id")
    private Coin coin;



}

