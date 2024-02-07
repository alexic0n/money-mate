package com.alexic0n.moneymate.ledgerapp.definition;


import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import java.util.List;

import static java.lang.String.format;

public abstract class AbstractLedgerApplicationService<S extends MongoRepository<T, ObjectId>, T extends AbstractLedgerApplicationEntity> {

    protected final S repository;

    protected final String entityName;

    public AbstractLedgerApplicationService(S repository, String entityName) {
        this.repository = repository;
        this.entityName = entityName;
    }
    
    public void validateEntityExistsById(String id) {
        if(!repository.existsById(parseObjectId(id))) {
            throw notFoundException(id, null);
        }
    }

    public T createEntity(T entity) {
        validateCreateEntity(entity);
        return repository.save(entity);
    }

    public T getEntityById(String id) {
        return repository
                .findById(parseObjectId(id))
                .orElseThrow(() -> notFoundException(id, null));
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
            throw badRequestException(format("Invalid ObjectId: %s", id), e);
        }
    }

    protected ErrorResponseException notFoundException(String id, Throwable cause) {
        return new ErrorResponseException(
                HttpStatus.NOT_FOUND,
                ProblemDetail.forStatusAndDetail(
                        HttpStatus.NOT_FOUND,
                        format("%s with id %s not found", entityName, id)
                ),
                cause
        );
    }

    protected ErrorResponseException badRequestException(String message, Throwable cause) {
        return new ErrorResponseException(
                HttpStatus.BAD_REQUEST,
                ProblemDetail.forStatusAndDetail(
                        HttpStatus.BAD_REQUEST,
                        message
                ),
                cause
        );
    }
}
