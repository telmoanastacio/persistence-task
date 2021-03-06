package com.tsilva.persistenceTask.service;

import com.tsilva.persistenceTask.contract.client.ClientSnapshot;
import com.tsilva.persistenceTask.contract.client.JsonResponse;
import com.tsilva.persistenceTask.persistence.dao.client.IClientSnapshotJpaRepository;
import com.tsilva.persistenceTask.service.maper.ClientSnapshotEntitiesToResponseMapper;
import com.tsilva.persistenceTask.service.maper.ClientSnapshotEntityToResponseMapper;
import com.tsilva.persistenceTask.service.maper.ClientSnapshotRequestToEntitiesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Telmo Silva on 28.08.2020.
 */

@Service
public class UserService implements IUserService
{
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private IClientSnapshotJpaRepository iClientSnapshotJpaRepository;

    @Override
    public JsonResponse<ClientSnapshot> getSnapshotById(long id)
    {
        HttpStatus httpStatus = null;
        JsonResponse<com.tsilva.persistenceTask.contract.client.ClientSnapshot> response = null;
        try
        {
            com.tsilva.persistenceTask.persistence.entity.client.ClientSnapshot clientSnapshotEntity =
                    iClientSnapshotJpaRepository.getOne(id);
            ClientSnapshot clientSnapshotContract =
                    new ClientSnapshotEntityToResponseMapper(clientSnapshotEntity).map();

            httpStatus = HttpStatus.OK;
            response = new JsonResponse<>(httpStatus.value(), httpStatus.toString(), clientSnapshotContract);
        }
        catch (EntityNotFoundException e)
        {
            httpStatus = HttpStatus.NOT_FOUND;
            response = new JsonResponse<>(httpStatus.value(), httpStatus.toString(), null);

            LOG.debug("No snapshot found for id: {}.", id);
        }

        return response;
    }

    @Override
    public JsonResponse<List<ClientSnapshot>> getSnapShots(long durationSeconds, @Nullable Integer page)
    {
        Date now = new Date();
        long startTs = now.getTime() - TimeUnit.SECONDS.toMillis(durationSeconds) / 2;
        long endTs = now.getTime() + TimeUnit.SECONDS.toMillis(durationSeconds) / 2;

        HttpStatus httpStatus = null;
        JsonResponse<List<ClientSnapshot>> response = null;
        Collection<com.tsilva.persistenceTask.persistence.entity.client.ClientSnapshot> clientSnapshotCollection =
                iClientSnapshotJpaRepository.findByUpdatedTimestampBetween(startTs, endTs);
        if(clientSnapshotCollection != null && !clientSnapshotCollection.isEmpty())
        {
            httpStatus = HttpStatus.OK;
            response = new JsonResponse<>(
                    httpStatus.value(),
                    httpStatus.toString(),
                    getClientSnapshotResponseListByPage(
                            new ClientSnapshotEntitiesToResponseMapper(clientSnapshotCollection).map(), page));
        }
        else
        {
            httpStatus = HttpStatus.NOT_FOUND;
            response = new JsonResponse<>(
                    httpStatus.value(),
                    httpStatus.toString(),
                    null);

            LOG.debug("No snapshots found for durationSeconds: {}.", durationSeconds);
        }

        return response;
    }

    @Override
    public JsonResponse<Void> deleteSnapshotById(long id)
    {
        HttpStatus httpStatus = null;
        JsonResponse<Void> response = null;
        try
        {
            iClientSnapshotJpaRepository.deleteById(id);

            httpStatus = HttpStatus.OK;
            response = new JsonResponse<>(httpStatus.value(), httpStatus.toString(), null);
        }
        catch (EmptyResultDataAccessException e)
        {
            httpStatus = HttpStatus.NOT_FOUND;
            response = new JsonResponse<>(httpStatus.value(), httpStatus.toString(), null);

            LOG.debug("Snapshot was not deleted. No snapshot found for id: {}.", id);
        }

        return response;
    }

    @Override
    public JsonResponse<Void> saveSnapshots(List<ClientSnapshot> clientSnapshotList)
    {
        HttpStatus httpStatus = null;
        JsonResponse<Void> response = null;
        if(clientSnapshotList != null)
        {
            iClientSnapshotJpaRepository.saveAll(new ClientSnapshotRequestToEntitiesMapper(clientSnapshotList).map());

            httpStatus = HttpStatus.OK;
        }
        else
        {
            httpStatus = HttpStatus.NOT_ACCEPTABLE;
            LOG.debug("Snapshots not saved. Request body not valid.");
        }
        response = new JsonResponse<>(httpStatus.value(), httpStatus.toString(), null);

        return response;
    }

    /**
     *
     * @param page greater than 0
     * @return up to 10 results if page is not null
     */
    private List<ClientSnapshot> getClientSnapshotResponseListByPage(
            List<ClientSnapshot> clientSnapshotResponseList,
            @Nullable Integer page)
    {
        if(page == null)
        {
            return clientSnapshotResponseList;
        }
        else
        {
            if(page <= 0)
            {
                LOG.debug("Page not valid.");

                return null;
            }

            int indexStart = (clientSnapshotResponseList.size() > (10 - 1) * page - (10 - 1))
                    ? (10 - 1) * page - (10 - 1)
                    : -1;
            if(indexStart == -1)
            {
                LOG.debug("Page not valid.");

                return null;
            }

            int indexEnd = Math.min(clientSnapshotResponseList.size() - 1, indexStart + 10);

            return clientSnapshotResponseList.subList(indexStart, indexEnd);
        }
    }
}
