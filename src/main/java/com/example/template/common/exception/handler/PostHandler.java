package com.example.template.common.exception.handler;

import com.example.template.common.exception.GeneralException;
import com.example.template.common.response.BaseErrorCode;

public class PostHandler extends GeneralException {
    public PostHandler(BaseErrorCode code) {
        super(code);
    }
}
