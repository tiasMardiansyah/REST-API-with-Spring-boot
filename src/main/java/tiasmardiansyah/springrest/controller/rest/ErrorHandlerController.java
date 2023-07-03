package tiasmardiansyah.springrest.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValidException(
            MethodArgumentNotValidException exception) {

        List<FieldError> InvalidData = exception.getFieldErrors();

        Map<String, String> errorList = new HashMap<>();
        InvalidData.forEach(notValidField -> {
            errorList.put(notValidField.getField(), notValidField.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errorList);
    }

}
