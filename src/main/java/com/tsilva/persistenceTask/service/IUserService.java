package com.tsilva.persistenceTask.service;

import com.tsilva.persistenceTask.contract.client.ClientSnapshot;
import com.tsilva.persistenceTask.contract.client.JsonResponse;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * Created by Telmo Silva on 28.08.2020.
 */

public interface IUserService
{
    public JsonResponse<ClientSnapshot> getSnapshotById(long id);

    public JsonResponse<List<ClientSnapshot>> getSnapShots(
            long durationSeconds,
            @Nullable Integer page);

    public JsonResponse<Void> deleteSnapshotById(long id);

    public JsonResponse<Void> saveSnapshots(List<ClientSnapshot> clientSnapshotList);
}
