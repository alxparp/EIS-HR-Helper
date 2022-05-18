package com.od.eisgroup.ui.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * An util class for setting faces messages
 *
 * @author Osipov Dmitriy
 * @since 1.2
 */
public final class MessageUtils {

    private MessageUtils() {}

    /**
     * Shows message with specified text and severity
     *
     * @param messageText message text what contains some information or error text
     * @param severity message severity
     */
    public static void printMessage(String messageText, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(messageText);
        message.setSeverity(severity);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
