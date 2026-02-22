package ru.gnidenko.servlet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

@WebFilter("/*")
public class LoggingFilter implements Filter {

    private final String logPath = "../logs/servlet-logs.txt";
    private FileOutputStream fos;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String log = LocalDateTime.now() + "\t"
            + request.getRequestURI() + "\t"
            + request.getRemoteAddr() + "\n";
        fos.write(log.getBytes());
        fos.flush();
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            fos = new FileOutputStream(logPath, true);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {
        try {
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
