package request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BonificoRequest {

    private Long accountId;
    private String receiverName;
    private String description;
    private String currency;
    private String amount;
    private String executionDate;
}
