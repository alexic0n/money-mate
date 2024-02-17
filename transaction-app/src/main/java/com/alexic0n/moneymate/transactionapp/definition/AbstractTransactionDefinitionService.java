package com.alexic0n.moneymate.transactionapp.definition;


import com.alexic0n.moneymate.transactionapp.AbstractTransactionService;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

import static java.lang.String.format;

public abstract class AbstractTransactionDefinitionService<S extends MongoRepository<T, ObjectId>, T extends AbstractTransactionDefinitionEntity> extends AbstractTransactionService {

    protected final S repository;


    public AbstractTransactionDefinitionService(S repository, String entityName) {
        super(entityName);
        this.repository = repository;
    }
    
    public void validateEntityExistsById(String id) {
        if(!repository.existsById(parseObjectId(id))) {
            throw notFoundException(id);
        }
    }

    public T createEntity(T entity) {
        validateCreateEntity(entity);
        return repository.save(entity);
    }

    public T getEntityById(String id) {
        return repository
                .findById(parseObjectId(id))
                .orElseThrow(() -> notFoundException(id));
    }

    public List<T> getListOfAllEntities() {
        return repository.findAll();
    }

    public Page<T> getPageOfAllEntities(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public void updateEntity(String id, T entity) {
        validateUpdateEntity(entity);
        T existingEntity = getEntityById(id);
        entity.setId(existingEntity.getId());
        repository.save(entity);
    }

    public void deleteEntity(String id) {
        repository.deleteById(parseObjectId(id));
    }

    //Overridable validation method.
    protected void validateCreateEntity(T entity) {
    }

    protected void validateUpdateEntity(T entity) {
    }

    protected ObjectId parseObjectId(String id) {
        try {
            return new ObjectId(id);
        } catch (IllegalArgumentException e) {
            throw badRequestException(format("Invalid ObjectId: %s", id));
        }
    }
}
