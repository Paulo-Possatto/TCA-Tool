package com.backend.tcatool.dto;

import com.backend.tcatool.domain.enums.InstallationType;
import com.backend.tcatool.domain.enums.TransformerType;
import com.backend.tcatool.domain.enums.VoltageType;
import com.backend.tcatool.validator.EnumPattern;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransformerDataDto {
    private long transformerId;
    @NotBlank(message = "The City is required")
    private String city;
    @NotBlank(message = "The Country is required")
    private String country;
    @EnumPattern(regexp = "EXTERNAL|COVERED|ARMORED")
    private InstallationType installationType;
    private Boolean isOperating;
    private String lastAnalysis;
    @Min(value = 0, message = "The power must be positive")
    private Double power;
    @NotBlank(message = "The Region is required")
    private String region;
    @NotBlank(message = "The Substation is required")
    private String substationName;
    @NotBlank(message = "The Transformer Code is required")
    @Size(min = 4, max = 10, message = "The code must be between 4 and 10 characters")
    private String transformerCode;
    @EnumPattern(regexp = "TRANSMISSION_EMITTER|TRANSMISSION_RECEIVER|DISTRIBUTION|CONSUMPTION")
    private TransformerType transformerType;
    @Min(value = 0, message = "The power must be positive")
    private Double voltage;
    private VoltageType voltageType;
    private String lastUpdate;
}
