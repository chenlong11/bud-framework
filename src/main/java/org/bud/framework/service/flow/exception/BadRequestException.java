package org.bud.framework.service.flow.exception;

/**
 * Created by chenlong
 * Date：2017/9/12
 * time：11:33
 */
public class BadRequestException extends BaseModelerRestException  {
    private static final long serialVersionUID = 1L;

    public BadRequestException(String s) {
        super(s);
    }

    public BadRequestException(String message, String messageKey) {
        this(message);
        this.setMessageKey(messageKey);
    }

    public BadRequestException(String s, Throwable t) {
        super(s, t);
    }
}