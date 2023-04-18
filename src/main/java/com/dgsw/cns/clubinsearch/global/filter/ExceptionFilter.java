package com.dgsw.cns.clubinsearch.global.filter;

import com.dgsw.cns.clubinsearch.global.exception.BusinessException;
import com.dgsw.cns.clubinsearch.global.exception.ExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Slf4j
public class ExceptionFilter extends OncePerRequestFilter {
    private final ObjectMapper mapper;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (BusinessException e) {
            sendErrorResponse(response, e);
        } catch (Exception e) {
            if (e.getCause() instanceof BusinessException) {
                sendErrorResponse(response, (BusinessException) e.getCause());
            } else {
                e.printStackTrace();
                sendErrorResponse(response, new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR"));
            }
        }
    }

    private void sendErrorResponse(
            HttpServletResponse response,
            BusinessException e
    ) throws IOException {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setStatus(e.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(mapper.writeValueAsString(new ExceptionResponse(e.getMessage())));
    }
}
