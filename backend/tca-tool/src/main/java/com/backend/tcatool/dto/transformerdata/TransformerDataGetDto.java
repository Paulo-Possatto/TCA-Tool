package com.backend.tcatool.dto.transformerdata;

import com.backend.tcatool.domain.enums.InstallationType;
import com.backend.tcatool.domain.enums.TransformerType;
import com.backend.tcatool.domain.enums.VoltageType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TransformerDataGetDto {
    private long transformerId;
    private String city;
    private String country;
    private InstallationType installationType;
    private Boolean isOperating;
    private String lastAnalysis;
    private Double power;
    private String region;
    private String substationName;
    private String transformerCode;
    private TransformerType transformerType;
    private Double voltage;
    private VoltageType voltageType;
    private String lastUpdate;
}
