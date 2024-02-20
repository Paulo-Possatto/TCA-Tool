package com.backend.tcatool.application;

import com.backend.tcatool.dto.TransformerAnalysisDto;

public interface TransformerAnalysisService {

    Object addNewTransformerAnalysis(TransformerAnalysisDto analysis);

    Object getTransformerAnalysis();

    Object getTransformerAnalysisById(long id);

    Object getTransformerAnalysisByTransformerCode(String transformerCode);

    Object updateTransformerAnalysis(long id, TransformerAnalysisDto analysis);

    Object deleteTransformerAnalysis(long id);
}
