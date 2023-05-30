package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long customerid;
    @Column
    private String customername;
    @Column
    private String customersurname;
    @Column
    private Integer customerage;
    @Column
    private String customernumber;
    @Column
    private String customeremail;
    @Column
    private String customerimage;


    @OneToMany(mappedBy ="customer")
    private List<Wallet> wallets=new ArrayList<>();


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private  List<Payment> payments=new ArrayList<>();
}
