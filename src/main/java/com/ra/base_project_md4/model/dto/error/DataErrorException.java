package com.ra.base_project_md4.model.dto.error;

public class DataErrorException extends RuntimeException {
    private final DataError<?> dataError;

    public DataErrorException(DataError<?> dataError) {
        this.dataError = dataError;
    }

    public DataError<?> getDataError() {
        return dataError;
    }
}
