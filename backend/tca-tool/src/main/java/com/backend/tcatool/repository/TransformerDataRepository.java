package com.backend.tcatool.repository;

import com.backend.tcatool.domain.TransformerData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransformerDataRepository extends JpaRepository<TransformerData, Long> {

    @Query("SELECT COUNT(t)>0 FROM TransformerData t WHERE t.transformerCode = :tCode")
    public Boolean isTransformerExisting(
            @Param("tCode") String transformerCode
    );
}
