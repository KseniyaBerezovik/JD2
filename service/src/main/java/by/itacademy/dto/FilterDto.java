package by.itacademy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FilterDto {
    private List<Integer> years;
    private Integer yearFrom;
    private Integer yearTo;
    private Integer priceFrom;
    private String priceTo;
    private List<String> os;
}
