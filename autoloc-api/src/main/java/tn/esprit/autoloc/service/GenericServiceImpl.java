package tn.esprit.autoloc.service;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.exception.ResourceNotFoundException;

public abstract class GenericServiceImpl<T, I> extends ReadServiceImpl<T, I>
        implements IGenericService<T, I> {

    protected GenericServiceImpl(JpaRepository<T, I> repository) {
        super(repository);
    }

    protected abstract I getId(T entity);

    protected abstract void setFields(T source, T target);

    @Override
    public boolean check(T entity, boolean isNew) {
        return entity != null;
    }

    @Override
    public T create(T entity) {
        if (getId(entity) != null) {
            throw new IllegalArgumentException("L'identifiant doit être vide lors de la création");
        }
        if (!check(entity, true)) {
            throw new IllegalArgumentException(getEntityName() + " invalide");
        }
        return repository.save(entity);
    }

    @Override
    public T update(I id, T entity) {
        T existing = findById(id);
        if (!check(entity, false)) {
            throw new IllegalArgumentException(getEntityName() + " invalide");
        }
        setFields(entity, existing);
        return repository.save(existing);
    }

    @Override
    public void deleteById(I id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(getEntityName(), String.valueOf(id));
        }
        repository.deleteById(id);
    }
}