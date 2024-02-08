package com.backend.tcatool.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.Date;

@Entity
@EnableAutoConfiguration
@Getter
@Setter
@Table(name = "transformer_analysis", schema = "tca_tool")
public class TransformerAnalysisEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "transformer_id")
    private TransformerDataEntity transformerData;
    @Column(name = "sample_date", nullable = false)
    private Date sampleDate;
    @Column(name = "hydrogen_gas", nullable = false)
    private Integer h2;
    @Column(name = "oxygen_gas", nullable = false)
    private Integer o2;
    @Column(name = "nitrogen_gas", nullable = false)
    private Integer n2;
    @Column(name = "carbon_monoxide_gas", nullable = false)
    private Integer co;
    @Column(name = "methane_gas", nullable = false)
    private Integer ch4;
    @Column(name = "carbon_dioxide_gas", nullable = false)
    private Integer co2;
    @Column(name = "ethylene_gas", nullable = false)
    private Integer c2h4;
    @Column(name = "ethane_gas", nullable = false)
    private Integer c2h6;
    @Column(name = "acetylene", nullable = false)
    private Integer c2h2;
    @Column(name = "oil_temperature", nullable = false)
    private Double oilTemperature;

}
