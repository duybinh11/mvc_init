package DTO.Response;


import lombok.Data;

import java.time.LocalDate;

@Data
public class ItemResponse {
    private int id;
    private String name;
    private  double price;
    private LocalDate createDate;
}
