package com.tsilva.persistenceTask.contract.client;

import java.io.Serializable;

/**
 * Created by Telmo Silva on 28.08.2020.
 */

public class ClientSnapshot implements Serializable
{
    private static final long serialVersionUID = 42865132230031L;

    private Long id;
    private String name;
    private String description;
    private Long updatedTimestamp;

    public ClientSnapshot(Long id, String name, String description, Long updatedTimestamp)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.updatedTimestamp = updatedTimestamp;
    }

    public Long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public Long getUpdatedTimestamp()
    {
        return updatedTimestamp;
    }
}
