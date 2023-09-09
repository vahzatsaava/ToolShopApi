package com.example.toolshopapi.dto.general;

import lombok.Getter;

@Getter
public class ResponseDto<T> extends GlobalDto{

    private final T content;

    public ResponseDto(final int code, final T content) {
        super(code);
        this.content = content;
    }
}
