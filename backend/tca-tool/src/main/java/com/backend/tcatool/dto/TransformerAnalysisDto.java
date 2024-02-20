package com.backend.tcatool.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransformerAnalysisDto {

    private long id;
    @Min(value = 0, message = "C2H2 value must be greater or equal to zero")
    private Integer c2h2;
    @Min(value = 0, message = "C2H4 value must be greater or equal to zero")
    private Integer c2h4;
    @Min(value = 0, message = "C2H6 value must be greater or equal to zero")
    private Integer c2h6;
    @Min(value = 0, message = "CH4 value must be greater or equal to zero")
    private Integer ch4;
    @Min(value = 0, message = "CO value must be greater or equal to zero")
    private Integer co;
    @Min(value = 0, message = "CO2 value must be greater or equal to zero")
    private Integer co2;
    @Min(value = 0, message = "H2 value must be greater or equal to zero")
    private Integer h2;
    @Min(value = 0, message = "O2 value must be greater or equal to zero")
    private Integer o2;
    @Min(value = 0, message = "N2 value must be greater or equal to zero")
    private Integer n2;
    private Double oilTemperature;
    @NotBlank(message = "The Sample Date is required")
    private String sampleDate;
    @Valid
    private TransformerDataDto transformerDataDto;
}
