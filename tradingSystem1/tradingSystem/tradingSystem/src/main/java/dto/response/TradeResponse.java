package dto.response;

import lombok.*;

@Data
@RequiredArgsConstructor
public class TradeResponse {
    private Long tradeId;
    private String status;
    private String message;

}

