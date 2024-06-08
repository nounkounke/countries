package gn.moria.nounkouke.country.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document("countries")
@Builder
public class Country {

    @Id
    @MongoId
    private String id;

    @Indexed(unique = true)
    @NotBlank(message = "country sku is required")
    private String sku;

    @Indexed(unique = true)
    @NotBlank(message = "country code is required")
    private Integer code;

    @Indexed(unique = true)
    @NotBlank(message = "country name is required")
    private String name;

    @Indexed(unique = true)
    @NotBlank(message = "country alias is required")
    private String alias;
}
