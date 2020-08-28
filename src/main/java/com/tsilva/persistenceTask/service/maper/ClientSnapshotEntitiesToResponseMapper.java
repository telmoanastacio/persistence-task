package com.tsilva.persistenceTask.service.maper;

import com.tsilva.persistenceTask.contract.client.ClientSnapshot;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Telmo Silva on 28.08.2020.
 */

public class ClientSnapshotEntitiesToResponseMapper
{
    private Collection<com.tsilva.persistenceTask.persistence.entity.client.ClientSnapshot>
            clientSnapshotEntityCollection = null;

    public ClientSnapshotEntitiesToResponseMapper(
            Collection<com.tsilva.persistenceTask.persistence.entity.client.ClientSnapshot>
                    clientSnapshotEntityCollection)
    {
        this.clientSnapshotEntityCollection = clientSnapshotEntityCollection;
    }

    public List<ClientSnapshot> map()
    {
        return clientSnapshotEntityCollection.stream().map(
                new Function<com.tsilva.persistenceTask.persistence.entity.client.ClientSnapshot, ClientSnapshot>()
                {
                    @Override
                    public ClientSnapshot apply(
                            com.tsilva.persistenceTask.persistence.entity.client.ClientSnapshot clientSnapshotEntity)
                    {
                        return new ClientSnapshotEntityToResponseMapper(clientSnapshotEntity).map();
                    }
                }).collect(Collectors.toList());
    }
}
