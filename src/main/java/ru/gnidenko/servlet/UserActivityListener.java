package ru.gnidenko.servlet;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

@WebListener
public class UserActivityListener implements HttpSessionListener {

    private final String logPath = "../logs/user-activity-log.txt";
    private final FileOutputStream fos;

    public UserActivityListener() throws FileNotFoundException {
        fos = new FileOutputStream(logPath);
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        addLog(se, "created");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        addLog(se, "destroyed");
    }

    private void addLog(HttpSessionEvent se, String message) {
        HttpSession session = se.getSession();
        String log = session.getId() + " " + LocalDateTime.now() + " " + message;
        System.out.println(log);
        try {
            fos.write(log.getBytes());
            fos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
