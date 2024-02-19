package com.backend.tcatool.application;

import com.backend.tcatool.dto.transformeranalysis.TransformerAnalysisGetDto;
import com.backend.tcatool.dto.transformeranalysis.TransformerAnalysisPostDto;
import com.backend.tcatool.dto.transformeranalysis.TransformerAnalysisPutDto;

public interface TransformerAnalysisService {

    Object addNewTransformerAnalysis(TransformerAnalysisPostDto analysis);

    Object getTransformerAnalysis();

    Object getTransformerAnalysisById(long id);

    Object getTransformerAnalysisByTransformerCode(String transformerCode);

    Object updateTransformerAnalysis(long id, TransformerAnalysisPutDto analysis);

    Object deleteTransformerAnalysis(long id);
}
