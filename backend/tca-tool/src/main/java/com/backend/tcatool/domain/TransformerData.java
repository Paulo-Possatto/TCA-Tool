package com.backend.tcatool.domain;

import com.backend.tcatool.domain.enums.InstallationType;
import com.backend.tcatool.domain.enums.TransformerType;
import com.backend.tcatool.domain.enums.VoltageType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "t_data", schema = "transformers")
public class TransformerData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transformer_id")
    private Integer transformerId;

    @Column(name = "transformer_code")
    private String transformerCode;

    @Column(name = "transformer_type")
    @Enumerated(EnumType.ORDINAL)
    private TransformerType transformerType;

    @Column(name = "installation_type")
    @Enumerated(EnumType.ORDINAL)
    private InstallationType installationType;

    @Column(name = "voltage_type")
    @Enumerated(EnumType.ORDINAL)
    private VoltageType voltageType;

    @Column(name = "voltage")
    private Double voltage;

    @Column(name = "power")
    private Double power;

    @Column(name = "substation_name")
    private String substationName;

    @Column(name = "country")
    private String country;

    @Column(name = "region")
    private String region;

    @Column(name = "city")
    private String city;

    @Column(name = "last_analysis", nullable = true)
    private Date lastAnalysis;

    @Column(name = "is_operating")
    private Boolean isOperating;

    @Column(name = "last_update")
    private Date lastUpdate;
}
