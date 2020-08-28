package com.tsilva.persistenceTask.service.maper;

import com.tsilva.persistenceTask.contract.client.ClientSnapshot;

/**
 * Created by Telmo Silva on 28.08.2020.
 */

public class ClientSnapshotEntityToResponseMapper
{
    private com.tsilva.persistenceTask.persistence.entity.client.ClientSnapshot clientSnapshotEntity = null;

    public ClientSnapshotEntityToResponseMapper(
            com.tsilva.persistenceTask.persistence.entity.client.ClientSnapshot clientSnapshot)
    {
        this.clientSnapshotEntity = clientSnapshot;
    }

    public ClientSnapshot map()
    {
        return new ClientSnapshot(
                clientSnapshotEntity.getId(),
                clientSnapshotEntity.getName(),
                clientSnapshotEntity.getDescription(),
                clientSnapshotEntity.getUpdatedTimestamp());
    }
}
