package com.tsilva.persistenceTask.persistence.entity.client;

import javax.persistence.*;

/**
 * Created by Telmo Silva on 28.08.2020.
 */

@Entity
@Table(name = "client_snapshot")
public class ClientSnapshot
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "updated_timestamp")
    private Long updatedTimestamp;

    public ClientSnapshot() {}

    public ClientSnapshot(String name, String description, Long updatedTimestamp)
    {
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
