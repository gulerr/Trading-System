package dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CoinDTO {
    private Long id;
    private String name;
    private Double price;
}
