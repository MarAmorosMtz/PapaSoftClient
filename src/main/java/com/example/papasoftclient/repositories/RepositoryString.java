package com.example.papasoftclient.repositories;

import com.example.papasoftclient.models.Page;

public interface RepositoryString<B,T> {
    public Page search(int page);
    public T search(String nombre);
    public String save(B item);
    public boolean update(String nombre, B item);
    public boolean remove(String nombre);
}
