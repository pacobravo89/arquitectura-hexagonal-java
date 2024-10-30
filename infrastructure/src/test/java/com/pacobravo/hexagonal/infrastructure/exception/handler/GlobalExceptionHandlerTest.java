package com.pacobravo.hexagonal.infrastructure.exception.handler;

import com.pacobravo.hexagonal.domain.price.model.exception.ResourceNotFoundException;
import com.pacobravo.hexagonal.infrastructure.exception.model.ErrorDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler exceptionHandler;

    @Test
    void handleResourceNotFoundException_ShouldReturnNotFoundStatusAndErrorDetails_WhenResourceNotFoundExceptionIsThrown() {
        // given
        ResourceNotFoundException exception = new ResourceNotFoundException("Resource not found");
        WebRequest mockRequest = mock(WebRequest.class);
        when(mockRequest.getDescription(false)).thenReturn("Request description");

        // when
        ResponseEntity<Object> response = exceptionHandler.handleResourceNotFoundException(exception, mockRequest);

        // then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ErrorDetails errorDetails = (ErrorDetails) response.getBody();
        assertEquals("Resource not found", errorDetails.getMessage());
        assertEquals("Request description", errorDetails.getDetails());
    }

    @Test
    void handleGlobalException_ShouldReturnInternalServerErrorStatusAndErrorDetails_WhenUnexpectedExceptionIsThrown() {
        // given
        Exception exception = new Exception("Unexpected error");
        WebRequest mockRequest = mock(WebRequest.class);
        when(mockRequest.getDescription(false)).thenReturn("Request description");

        // when
        ResponseEntity<Object> response = exceptionHandler.handleGlobalException(exception, mockRequest);

        // then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        ErrorDetails errorDetails = (ErrorDetails) response.getBody();
        assertEquals("Internal server error", errorDetails.getMessage());
        assertEquals("Request description", errorDetails.getDetails());
    }
}
