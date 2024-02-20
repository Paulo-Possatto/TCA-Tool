package com.backend.tcatool.application;

import com.backend.tcatool.dto.TransformerDataDto;

public interface TransformerDataService {

    Object addNewTransformer(TransformerDataDto transformer);

    Object getTransformers();

    Object getTransformerById(long id);

    Object updateTransformer(long id, TransformerDataDto transformer);

    Object deleteTransformer(long id);
}
