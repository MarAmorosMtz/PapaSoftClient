package com.example.papasoftclient.repositories;



import com.example.papasoftclient.models.Page;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;

import java.util.UUID;

public interface Repository<B,T> {

    public Page search(int page);
    public T search(UUID id);
    public UUID save(B item);
    public boolean update(UUID id,B item);
    public boolean remove(UUID id);
}
