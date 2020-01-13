package ch.heigvd.amt.project2.api.exceptions;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.List;

public class ApiError {

    private List<String> errors;

    public ApiError(List<String> errors) {
        super();
        this.errors = errors;
    }

    public ApiError(String error) {
        super();
        errors = Arrays.asList(error);
    }
}
