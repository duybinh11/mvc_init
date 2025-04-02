package DTO.Request;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ItemRequest {
    private String name;
    @NotNull(message = "price k null")
    private Double price;
}
