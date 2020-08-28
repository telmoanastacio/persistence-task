package com.tsilva.persistenceTask.contract.client;

import java.io.Serializable;

/**
 * Created by Telmo Silva on 28.08.2020.
 */

public class JsonResponse <T> implements Serializable
{
    private static final long serialVersionUID = 42865132230030L;

    private int httpStatusCode;
    private String httpStatus;
    private T body;

    public JsonResponse(int httpStatusCode, String httpStatus, T body)
    {
        this.httpStatusCode = httpStatusCode;
        this.httpStatus = httpStatus;
        this.body = body;
    }

    public int getHttpStatusCode()
    {
        return httpStatusCode;
    }

    public String getHttpStatus()
    {
        return httpStatus;
    }

    public T getBody()
    {
        return body;
    }
}
