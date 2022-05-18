package com.od.eisgroup.ui.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ManagedBean(name = "timeManagerBean")
@SessionScoped
public class TimeManagerBean {
    private static final long serialVersionUID = 1L;

    public String getCurrentTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM-yyyy HH:mm:ss"));
    }
}
