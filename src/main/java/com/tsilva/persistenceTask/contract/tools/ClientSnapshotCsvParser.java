package com.tsilva.persistenceTask.contract.tools;

import com.tsilva.persistenceTask.contract.client.ClientSnapshot;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Telmo Silva on 31.08.2020.
 */

public class ClientSnapshotCsvParser implements IClientSnapshotCsvParser
{
    public static final String NEW_LINE = "\r\n";

    @Override
    public List<ClientSnapshot> parse(String clientSnapshotCsv)
    {
        if(clientSnapshotCsv == null || clientSnapshotCsv.isEmpty())
        {
            return null;
        }

        clientSnapshotCsv = normalizeNewLineCharacter(clientSnapshotCsv);

        if(clientSnapshotCsv.length() > 2
                && clientSnapshotCsv.substring(clientSnapshotCsv.length() - 2).equals(NEW_LINE))
        {
            String[] lines = clientSnapshotCsv.split(NEW_LINE);

            if(lines.length > 1)
            {
                String header = lines[0];
                String[] fields = header.split(",");

                List<ClientSnapshot> clientSnapshotList = new ArrayList<>(lines.length - 1);

                for(int i = 1; i < lines.length; i++)
                {
                    long primaryKey = -1;
                    String name = null;
                    String description = null;
                    long updatedTimestamp = -1;

                    String[] lineCells = lines[i].split(",");
                    if(fields.length == lineCells.length)
                    {
                        for(int j = 0; j < fields.length; j++)
                        {
                            String field = fields[j].toUpperCase();

                            switch (field)
                            {
                                case "PRIMARY_KEY":
                                {
                                    try
                                    {
                                        primaryKey = Long.parseLong(lineCells[j].trim());
                                    }
                                    catch (NumberFormatException e)
                                    {
                                        return null;
                                    }
                                    break;
                                }
                                case "NAME":
                                {
                                    String nameCsv = lineCells[j].trim();
                                    if(!nameCsv.isEmpty())
                                    {
                                        name = nameCsv;
                                    }
                                    break;
                                }
                                case "DESCRIPTION":
                                {
                                    String descriptionCsv = lineCells[j].trim();
                                    if(!descriptionCsv.isEmpty())
                                    {
                                        description = descriptionCsv;
                                    }
                                    break;
                                }
                                case "UPDATED_TIMESTAMP":
                                {
                                    try
                                    {
                                        updatedTimestamp = Long.parseLong(lineCells[j].trim());
                                    }
                                    catch (NumberFormatException e)
                                    {
                                        return null;
                                    }
                                    break;
                                }
                                default:
                                    break;
                            }
                        }
                    }

                    if(primaryKey != -1 && name != null && description != null && updatedTimestamp != -1)
                    {
                        clientSnapshotList.add(new ClientSnapshot(primaryKey, name, description, updatedTimestamp));
                    }
                    else
                    {
                        return null;
                    }
                }

                return clientSnapshotList;
            }
        }

        return null;
    }

    /**
     *
     * @param clientSnapshotCsv is using CR, LF, CRLF, or CRLF reversed
     * @return clientSnapshotCsv parameter normalized to CRLF
     */
    private String normalizeNewLineCharacter(@NonNull String clientSnapshotCsv)
    {
        int originalLength = clientSnapshotCsv.length();

        clientSnapshotCsv = clientSnapshotCsv.replace("\r", NEW_LINE);
        if(clientSnapshotCsv.length() == originalLength)
        {
            clientSnapshotCsv = clientSnapshotCsv.replace("\n", NEW_LINE);
        }
        if(clientSnapshotCsv.length() == originalLength)
        {
            clientSnapshotCsv = clientSnapshotCsv.replace("\n\r", NEW_LINE);
        }

        return clientSnapshotCsv;
    }
}
