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
@Table(name = "transformer_data", schema = "tca_tool")
public class TransformerDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "id_transformer", nullable = false)
    @OneToOne(mappedBy = "transformer_analysis", cascade = CascadeType.ALL)
    private String idTransformer;
    @Column(name = "location", nullable = false)
    private String location;
    @Column(name = "is_operating", nullable = false, columnDefinition = "TINYINT DEFAULT '1'")
    private Boolean isOperating;
    @Column(name = "last_analysis", nullable = false)
    private Date lastAnalysis;
    @Column(name = "pwr", nullable = false)
    private Double power;
}
