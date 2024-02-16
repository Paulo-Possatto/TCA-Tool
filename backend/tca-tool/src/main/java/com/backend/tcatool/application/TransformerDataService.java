package com.backend.tcatool.application;

import com.backend.tcatool.dto.transformerdata.TransformerDataPostDto;
import com.backend.tcatool.dto.transformerdata.TransformerDataPutDto;

public interface TransformerDataService {

    Object addNewTransformer(TransformerDataPostDto transformer);

    Object getTransformers();

    Object getTransformerById(Integer id);

    Object updateTransformer(Integer id, TransformerDataPutDto transformer);

    Object deleteTransformer(Integer id);
}
