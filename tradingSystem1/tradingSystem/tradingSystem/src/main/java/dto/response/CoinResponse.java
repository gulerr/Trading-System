package dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CoinResponse {
    private Long id;
    private String name;
    private Double price;
    private Double amount;
    public CoinResponse(Long id, String name, Double price, Double amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount=amount;
    }
}
