package dto.response;

import lombok.Builder;
import lombok.Data;
import java.util.Currency;

@Builder
@Data
public class WalletResponse {
    private Long walletId;
    private Double balance;
    private Currency currency;

    public WalletResponse(Long walletId, Double balance, Currency currency) {
        this.walletId = walletId;
        this.balance = balance;
        this.currency = currency;
    }
}

