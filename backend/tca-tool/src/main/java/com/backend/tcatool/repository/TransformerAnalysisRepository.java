package com.backend.tcatool.repository;

import com.backend.tcatool.domain.TransformerAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransformerAnalysisRepository extends JpaRepository<TransformerAnalysis, Long> {

    @Query("SELECT COUNT(t)>0 FROM TransformerData t WHERE t.transformerCode = :transformerCode")
    public boolean isTransformerExisting(
            @Param("transformerCode") String transformerCode
    );

    @Query("SELECT t FROM TransformerAnalysis t WHERE t.transformerData.transformerCode = :transformerCode")
    public TransformerAnalysis findByCode(
            @Param("transformerCode") String transformerCode
    );
}
