package com.backend.tcatool.dto.transformeranalysis;

import com.backend.tcatool.dto.transformerdata.TransformerDataGetDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TransformerAnalysisPostDto {

    private Integer c2h2;
    private Integer c2h4;
    private Integer c2h6;
    private Integer ch4;
    private Integer co;
    private Integer co2;
    private Integer h2;
    private Integer o2;
    private Integer n2;
    private Double oilTemperature;
    private Date sampleDate;
    private TransformerDataGetDto transformerDataGetDto;
}
