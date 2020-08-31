package com.tsilva.persistenceTask.service.maper;

import com.tsilva.persistenceTask.persistence.entity.client.ClientSnapshot;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Telmo Silva on 31.08.2020.
 */

public class ClientSnapshotRequestToEntitiesMapper
{
    private Collection<com.tsilva.persistenceTask.contract.client.ClientSnapshot>
            clientSnapshotRequestCollection = null;

    public ClientSnapshotRequestToEntitiesMapper(
            Collection<com.tsilva.persistenceTask.contract.client.ClientSnapshot> clientSnapshotRequestCollection)
    {
        this.clientSnapshotRequestCollection = clientSnapshotRequestCollection;
    }

    public List<ClientSnapshot> map()
    {
        return clientSnapshotRequestCollection.stream().map(
                new Function<com.tsilva.persistenceTask.contract.client.ClientSnapshot, ClientSnapshot>()
                {
                    @Override
                    public ClientSnapshot apply(
                            com.tsilva.persistenceTask.contract.client.ClientSnapshot clientSnapshot)
                    {
                        return new ClientSnapshot(
                                clientSnapshot.getId(),
                                clientSnapshot.getName(),
                                clientSnapshot.getDescription(),
                                clientSnapshot.getUpdatedTimestamp());
                    }
                }).collect(Collectors.toList());
    }
}
