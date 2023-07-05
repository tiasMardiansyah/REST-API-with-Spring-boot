package tiasmardiansyah.springrest.exception;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidHandler(MethodArgumentNotValidException exception, HttpServletRequest request) {


        //get the data that not valid
        List<FieldError> InvalidData = exception.getFieldErrors();

        //and also get the the path that trigger it
        String path = request.getRequestURI();

        //then make a map to store all the data that not valid
        Map<String, String> errorList = new HashMap<>();
        InvalidData.forEach(notValidField -> {
            errorList.put(notValidField.getField(), notValidField.getDefaultMessage());
        });

        //this is a hack, i am sorry for my future self
        ErrorResponse error = new ErrorResponse(
            LocalDateTime.now(Clock.systemUTC()),
            400,
            "Bad Request",
            errorList, 
            path
        );

        return ResponseEntity.badRequest().body(error);

    }

}
