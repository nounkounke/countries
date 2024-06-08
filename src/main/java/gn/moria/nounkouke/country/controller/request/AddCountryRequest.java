package gn.moria.nounkouke.country.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddCountryRequest {
    @NotBlank(message = "country sku is required")
    private String name;
    private String alias;

    @NotBlank(message = "country code is required")
    private int code;
}
