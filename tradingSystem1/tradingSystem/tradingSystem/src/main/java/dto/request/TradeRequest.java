package dto.request;

import lombok.Data;


@Data
public class TradeRequest {
    private Long coinId;
    private double price;
    private double amount;
}

