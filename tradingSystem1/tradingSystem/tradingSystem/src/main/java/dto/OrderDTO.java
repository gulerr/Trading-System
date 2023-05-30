package dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDTO {
    private Long id;
    private String type;
    private Double amount;
    private Double price;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
