package com.backend.tcatool.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "t_analysis", schema = "transformers")
public class TransformerAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transformer_code", nullable = false, referencedColumnName = "transformer_code")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private TransformerData transformerData;

    @Column(name = "sample_date")
    private Date sampleDate;

    @Column(name = "h2")
    private Integer h2;

    @Column(name = "o2")
    private Integer o2;

    @Column(name = "n2")
    private Integer n2;

    @Column(name = "co")
    private Integer co;

    @Column(name = "ch4")
    private Integer ch4;

    @Column(name = "co2")
    private Integer co2;

    @Column(name = "c2h6")
    private Integer c2h6;

    @Column(name = "c2h4")
    private Integer c2h4;

    @Column(name = "c2h2")
    private Integer c2h2;

    @Column(name = "oil_temperature")
    private Double oilTemperature;
}
