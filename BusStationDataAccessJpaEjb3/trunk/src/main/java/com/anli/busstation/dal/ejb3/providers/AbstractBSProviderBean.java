package com.anli.busstation.dal.ejb3.providers;

import com.anli.busstation.dal.ejb3.exceptions.ManageableConsistencyException;
import com.anli.busstation.dal.interfaces.entities.BSEntity;
import com.anli.busstation.dal.interfaces.providers.BSEntityProvider;
import com.anli.busstation.dal.jpa.entities.BSEntityImpl;
import com.anli.busstation.dal.jpa.queries.EntityQueryHolder;
import com.anli.busstation.dal.jpa.queries.FieldQueryHolder;
import com.anli.busstation.dal.jpa.queries.GlobalQueryHolder;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TransactionAttribute(TransactionAttributeType.REQUIRED)
public abstract class AbstractBSProviderBean<I extends BSEntity, E extends BSEntityImpl>
        implements BSEntityProvider<I> {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractBSProviderBean.class);

    @PersistenceContext(unitName = "busstation")
    protected EntityManager manager;

    @Inject
    protected GlobalQueryHolder queryHolder;

    protected abstract E getEntityInstance();

    protected abstract Class<E> getEntityClass();

    protected EntityManager getManager() {
        return manager;
    }

    protected EntityQueryHolder getQueryHolder() {
        String entityName = getEntityClass().getAnnotation(Entity.class).name();
        return queryHolder.getHolder(entityName);
    }

    protected boolean isEntityConsistent(BSEntity entity, Class<? extends BSEntity> entityClass) {
        try {
            getManager().getReference(entityClass, entity.getId()).getId();
            return true;
        } catch (EntityNotFoundException enfException) {
            LOG.error("Entity could not be found", enfException);
            return false;
        }
    }

    protected boolean isMainEntityConsistent(E entity) {
        return isEntityConsistent(entity, getEntityClass());
    }

    protected Collection<BSEntity> getInconsistentCollectionElements(Collection<BSEntity> collection,
            Class<? extends BSEntity> entityClass) {
        LinkedList<BSEntity> inconsistentEntities = new LinkedList<>();
        if (collection == null) {
            return inconsistentEntities;
        }
        for (BSEntity entity : collection) {
            if (!isEntityConsistent(entity, entityClass)) {
                inconsistentEntities.add(entity);
            }
        }
        return inconsistentEntities;
    }

    protected Collection<BSEntity> getInconsistentReferences(E entity) {
        return new LinkedList<>();
    }

    protected void checkEntityConsistency(E entity) {
        if (!isMainEntityConsistent(entity)) {
            throw new ManageableConsistencyException(Arrays.asList((BSEntity) entity), null);
        }
        Collection<BSEntity> inconsistent = getInconsistentReferences(entity);
        if (!inconsistent.isEmpty()) {
            throw new ManageableConsistencyException(inconsistent, null);
        }
    }

    protected void setQueryParameters(Query query, Collection parameters) {
        if (parameters == null) {
            return;
        }
        int index = 0;
        for (Object param : parameters) {
            index++;
            query.setParameter(index, param);
        }
    }

    protected <T> List<T> selectByQuery(String query, Collection parameters, Class<T> type) {
        return selectByQuery(query, parameters, type, false);
    }

    protected <T> List<T> selectByQuery(String query, Collection parameters, Class<T> type, boolean named) {
        TypedQuery<T> typedQuery;
        if (named) {
            typedQuery = getManager().createNamedQuery(query, type);
        } else {
            typedQuery = getManager().createQuery(query, type);
        }
        setQueryParameters(typedQuery, parameters);
        return typedQuery.getResultList();
    }

    protected List<I> findByQuery(String query, Collection parameters) {
        return findByQuery(query, parameters, false);
    }

    protected List<I> findByQuery(String query, Collection parameters, boolean named) {
        return (List) selectByQuery(query, parameters, getEntityClass(), named);
    }

    protected List<BigInteger> collectIdsByQuery(String query, Collection parameters) {
        return collectIdsByQuery(query, parameters, false);
    }

    protected List<BigInteger> collectIdsByQuery(String query, Collection parameters, boolean named) {
        return selectByQuery(query, parameters, BigInteger.class, named);
    }

    protected List<I> findByEquals(String field, Object value) {
        FieldQueryHolder fieldHolder = getQueryHolder().getFieldHolder(field);
        if (value != null) {
            return findByQuery(fieldHolder.getSelectByEquals(), Arrays.asList(value));
        } else {
            return findByQuery(fieldHolder.getSelectByNull(), null);
        }

    }

    protected List<BigInteger> collectIdsByEquals(String field, Object value) {
        FieldQueryHolder fieldHolder = getQueryHolder().getFieldHolder(field);
        if (value != null) {
            return collectIdsByQuery(fieldHolder.getCollectByEquals(), Arrays.asList(value));
        } else {
            return collectIdsByQuery(fieldHolder.getCollectByNull(), null);
        }
    }

    protected List<I> findByAny(String field, Collection values) {
        if (values == null || values.isEmpty()) {
            return new LinkedList<>();
        }
        String query = getQueryHolder().getFieldHolder(field).getSelectByAny();
        return findByQuery(query, values);
    }

    protected List<BigInteger> collectIdsByAny(String field, Collection values) {
        if (values == null || values.isEmpty()) {
            return new LinkedList<>();
        }
        String query = getQueryHolder().getFieldHolder(field).getCollectByAny();
        return collectIdsByQuery(query, values);
    }

    protected List<I> findByContains(String field, Object value) {
        if (value == null) {
            return new LinkedList<>();
        }
        String query = getQueryHolder().getFieldHolder(field).getSelectByContains();
        return findByQuery(query, Arrays.asList(value));
    }

    protected List<BigInteger> collectIdsByContains(String field, Object value) {
        if (value == null) {
            return new LinkedList<>();
        }
        String query = getQueryHolder().getFieldHolder(field).getCollectByContains();
        return collectIdsByQuery(query, Arrays.asList(value));
    }

    protected List<I> findByRegexp(String field, String regexp) {
        String query = getQueryHolder().getFieldHolder(field).getSelectByRegexp();
        return findByQuery(query, Arrays.asList(regexp));
    }

    protected List<BigInteger> collectIdsByRegexp(String field, String regexp) {
        String query = getQueryHolder().getFieldHolder(field).getCollectByRegexp();
        return collectIdsByQuery(query, Arrays.asList(regexp));
    }

    protected List<I> findByRange(String field, Object leftValue, boolean leftStrict,
            Object rightValue, boolean rightStrict) {
        if (leftValue == null && rightValue == null) {
            return findAll();
        }
        FieldQueryHolder holder = getQueryHolder().getFieldHolder(field);
        String query;
        ArrayList parameters = new ArrayList(2);
        boolean isLeft = leftValue != null;
        boolean isRight = rightValue != null;
        if (!isLeft || !isRight) {
            query = holder.getSelectByOpenRange(isLeft, isLeft ? leftStrict : rightStrict);
        } else {
            query = holder.getSelectByClosedRange(leftStrict, rightStrict);
        }
        if (isLeft) {
            parameters.add(leftValue);
        }
        if (isRight) {
            parameters.add(rightValue);
        }
        return findByQuery(query, parameters);
    }

    protected List<BigInteger> collectIdsByRange(String field, Object leftValue, boolean leftStrict,
            Object rightValue, boolean rightStrict) {
        if (leftValue == null && rightValue == null) {
            return collectIdsAll();
        }
        FieldQueryHolder holder = getQueryHolder().getFieldHolder(field);
        String query;
        ArrayList parameters = new ArrayList(2);
        boolean isLeft = leftValue != null;
        boolean isRight = rightValue != null;
        if (!isLeft || !isRight) {
            query = holder.getCollectByOpenRange(isLeft, isLeft ? leftStrict : rightStrict);
        } else {
            query = holder.getCollectByClosedRange(leftStrict, rightStrict);
        }
        if (isLeft) {
            parameters.add(leftValue);
        }
        if (isRight) {
            parameters.add(rightValue);
        }
        return collectIdsByQuery(query, parameters);
    }

    @Override
    public I create() {
        E entity = getEntityInstance();
        getManager().persist(entity);
        return (I) entity;
    }

    @Override
    public I save(I entity) {
        E concreteEntity = (E) entity;
        checkEntityConsistency(concreteEntity);
        getManager().merge(concreteEntity);
        return entity;
    }

    @Override
    public void remove(I entity) {
        E concreteEntity = (E) entity;
        checkEntityConsistency(concreteEntity);
        E toRemove = getManager().find(getEntityClass(), concreteEntity.getId());
        getManager().remove(toRemove);
    }

    @Override
    public I findById(BigInteger id) {
        return (I) getManager().find(getEntityClass(), id);
    }

    @Override
    public List<I> findAll() {
        return findByQuery(getQueryHolder().getSelectAll(), null);
    }

    @Override
    public List<BigInteger> collectIdsAll() {
        return collectIdsByQuery(getQueryHolder().getCollectKeysAll(), null);
    }
}
