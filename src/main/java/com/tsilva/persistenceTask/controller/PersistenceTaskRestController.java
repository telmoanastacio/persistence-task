package com.tsilva.persistenceTask.controller;

import com.tsilva.persistenceTask.contract.client.JsonResponse;
import com.tsilva.persistenceTask.persistence.dao.client.IClientSnapshotJpaRepository;
import com.tsilva.persistenceTask.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Telmo Silva on 28.08.2020.
 */

@RestController
public class PersistenceTaskRestController
{
    @Autowired
    private IUserService iUserService;

    @Autowired
    private IClientSnapshotJpaRepository iClientSnapshotJpaRepository;

    @RequestMapping(value = "/snapshot/{id}", method = RequestMethod.GET)
    public JsonResponse<com.tsilva.persistenceTask.contract.client.ClientSnapshot> getSnapshot(
            @PathVariable(name = "id") Long id)
    {
        return iUserService.getSnapshotById(id);
    }

    @RequestMapping(value = "/snapshots/{durationSeconds}", method = RequestMethod.GET)
    public JsonResponse<List<com.tsilva.persistenceTask.contract.client.ClientSnapshot>> getSnapShots(
            @PathVariable(name = "durationSeconds") Long durationSeconds,
            @Nullable @RequestParam(name = "page", required = false) Integer page)
    {
        return iUserService.getSnapShots(durationSeconds, page);
    }

    @RequestMapping(value = "/snapshot", method = RequestMethod.DELETE)
    public JsonResponse<Void> deleteSnapshot(
            @RequestParam(name = "id", required = true) Long id)
    {
        return iUserService.deleteSnapshotById(id);
    }
}
