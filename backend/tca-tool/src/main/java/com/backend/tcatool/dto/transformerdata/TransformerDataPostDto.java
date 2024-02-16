package com.backend.tcatool.dto.transformerdata;

import com.backend.tcatool.domain.enums.InstallationType;
import com.backend.tcatool.domain.enums.TransformerType;
import com.backend.tcatool.domain.enums.VoltageType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TransformerDataPostDto {
    @NotNull(message = "Transformer Code is Mandatory")
    private String transformerCode;
    private TransformerType transformerType;
    private InstallationType installationType;
    @NotNull(message = "Voltage value is mandatory")
    private Double voltage;
    private Double power;
    private String substationName;
    private String country;
    private String region;
    private String city;
    private Date lastAnalysis;
    private Boolean isOperating;
}
