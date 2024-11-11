package com.ra.base_project_md4.model.dto.error;

import lombok.*;

@AllArgsConstructor
@NonNull
@Getter
@Setter
@Builder
public class DataError<T>{
    private T message;

    private int status;



}
