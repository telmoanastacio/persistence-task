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

    @RequestMapping(value = "/snapshots/{startTs}/{endTs}", method = RequestMethod.GET)
    public JsonResponse<List<com.tsilva.persistenceTask.contract.client.ClientSnapshot>> getSnapShots(
            @PathVariable(name = "startTs") Long startTs,
            @PathVariable(name = "endTs") Long endTs,
            @Nullable @RequestParam(name = "page", required = false) Integer page)
    {
        return iUserService.getSnapShots(startTs, endTs, page);
    }
}
