package com.tsilva.persistenceTask.persistence.dao.client;

import com.tsilva.persistenceTask.persistence.entity.client.ClientSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by Telmo Silva on 28.08.2020.
 */

@Repository
public interface IClientSnapshotJpaRepository extends JpaRepository<ClientSnapshot, Long>
{
    public Collection<ClientSnapshot> findByUpdatedTimestampBetween(long startTimestamp, long endTimestamp);
    public void deleteById(long id);
}
