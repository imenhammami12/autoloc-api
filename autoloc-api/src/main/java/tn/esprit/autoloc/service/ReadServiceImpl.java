package tn.esprit.autoloc.service;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.exception.ResourceNotFoundException;

import java.util.List;

public abstract class ReadServiceImpl<T, I> implements IReadService<T, I> {

    protected final JpaRepository<T, I> repository;

    protected ReadServiceImpl(JpaRepository<T, I> repository) {
        this.repository = repository;
    }

    protected abstract String getEntityName();

    @Override
    public T findById(I id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(getEntityName(), String.valueOf(id)));
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }
}