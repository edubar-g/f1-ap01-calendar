package es.ebde.ap01.calendar.shared.exception.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private List<ErrorDetail> errors;

    public ErrorResponse() {
        this.errors = new ArrayList<>();
    }

    public ErrorResponse(List<ErrorDetail> errors) {
        this.errors = errors;
    }

    public static ErrorResponse of(ErrorDetail error) {
        ErrorResponse response = new ErrorResponse();
        response.addError(error);
        return response;
    }

    public void addError(ErrorDetail error) {
        this.errors.add(error);
    }


    @Setter
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ErrorDetail {

        private String code;
        private String message;
        private String description;
        private String level;

        public ErrorDetail() {
        }

        public ErrorDetail(String code, String message, String description, String level) {
            this.code = code;
            this.message = message;
            this.description = description;
            this.level = level;
        }

    }
}

