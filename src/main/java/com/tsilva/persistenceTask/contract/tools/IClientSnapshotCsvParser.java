package com.tsilva.persistenceTask.contract.tools;

import com.tsilva.persistenceTask.contract.client.ClientSnapshot;

import java.util.List;

/**
 * Created by Telmo Silva on 31.08.2020.
 */

public interface IClientSnapshotCsvParser
{
    public List<ClientSnapshot> parse(String clientSnapshotCsv);
}
