package com.anli.busstation.dal.ejb3.providers;

import com.anli.busstation.dal.ejb3.exceptions.ManageableConsistencyException;
import com.anli.busstation.dal.interfaces.entities.BSEntity;
import com.anli.busstation.dal.interfaces.providers.BSEntityProvider;
import com.anli.busstation.dal.jpa.entities.BSEntityImpl;
import com.anli.busstation.dal.jpa.queries.EntityQueryHolder;
import com.anli.busstation.dal.jpa.queries.FieldQueryHolder;
import com.anli.busstation.dal.jpa.queries.QueryHolder;
import com.anli.busstation.dal.jpa.reflective.EntityCloner;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@TransactionAttribute(REQUIRED)
public abstract class AbstractBSProviderBean<I extends BSEntity, E extends BSEntityImpl>
        implements BSEntityProvider<I> {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractBSProviderBean.class);

    @PersistenceContext(unitName = "busstation")
    protected EntityManager manager;

    @Inject
    protected QueryHolder queryHolder;

    @Inject
    protected EntityCloner cloner;

    protected abstract E getEntityInstance();

    protected abstract Class<E> getEntityClass();

    protected EntityManager getManager() {
        return manager;
    }

    protected QueryHolder getQueryHolder() {
        return queryHolder;
    }

    protected EntityCloner getCloner() {
        return cloner;
    }

    protected EntityQueryHolder getEntityQueryHolder() {
        String entityName = getEntityClass().getAnnotation(Entity.class).name();
        return getQueryHolder().getHolder(entityName);
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
        return (List) getCloner().cloneCollection(selectByQuery(query, parameters,
                getEntityClass(), named));
    }

    protected List<BigInteger> collectIdsByQuery(String query, Collection parameters) {
        return collectIdsByQuery(query, parameters, false);
    }

    protected List<BigInteger> collectIdsByQuery(String query, Collection parameters, boolean named) {
        return selectByQuery(query, parameters, BigInteger.class, named);
    }

    protected List<I> findByEquals(String field, Object value) {
        FieldQueryHolder fieldHolder = getEntityQueryHolder().getFieldHolder(field);
        if (value != null) {
            return findByQuery(fieldHolder.getSelectByEquals(), Arrays.asList(value));
        } else {
            return findByQuery(fieldHolder.getSelectByNull(), null);
        }

    }

    protected List<BigInteger> collectIdsByEquals(String field, Object value) {
        FieldQueryHolder fieldHolder = getEntityQueryHolder().getFieldHolder(field);
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
        String query = getEntityQueryHolder().getFieldHolder(field).getSelectByAny();
        return findByQuery(query, Arrays.asList(values));
    }

    protected List<BigInteger> collectIdsByAny(String field, Collection values) {
        if (values == null || values.isEmpty()) {
            return new LinkedList<>();
        }
        String query = getEntityQueryHolder().getFieldHolder(field).getCollectByAny();
        return collectIdsByQuery(query, Arrays.asList(values));
    }

    protected List<I> findByContains(String field, Object value) {
        if (value == null) {
            return new LinkedList<>();
        }
        String query = getEntityQueryHolder().getFieldHolder(field).getSelectByContains();
        return findByQuery(query, Arrays.asList(value));
    }

    protected List<BigInteger> collectIdsByContains(String field, Object value) {
        if (value == null) {
            return new LinkedList<>();
        }
        String query = getEntityQueryHolder().getFieldHolder(field).getCollectByContains();
        return collectIdsByQuery(query, Arrays.asList(value));
    }

    protected List<I> findByRegexp(String field, String regexp) {
        String query = getEntityQueryHolder().getFieldHolder(field).getSelectByRegexp();
        return findByQuery(query, Arrays.asList(regexp));
    }

    protected List<BigInteger> collectIdsByRegexp(String field, String regexp) {
        String query = getEntityQueryHolder().getFieldHolder(field).getCollectByRegexp();
        return collectIdsByQuery(query, Arrays.asList(regexp));
    }

    protected List<I> findByRange(String field, Object leftValue, boolean leftStrict,
            Object rightValue, boolean rightStrict) {
        if (leftValue == null && rightValue == null) {
            return findAll();
        }
        FieldQueryHolder holder = getEntityQueryHolder().getFieldHolder(field);
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
        FieldQueryHolder holder = getEntityQueryHolder().getFieldHolder(field);
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

    protected E getEntityReference(E entity) {
        E reference = getManager().find(getEntityClass(), entity.getId());
        if (reference == null) {
            throw new ManageableConsistencyException(Collections
                    .<BSEntity>singletonList(entity), null);
        }
        return reference;
    }

    @Override
    public I create() {
        E entity = getEntityInstance();
        if (entity == null) {
            return null;
        }
        getManager().persist(entity);
        return (I) getCloner().clone(entity);
    }

    @Override
    public I save(I entity) {
        E concreteEntity = (E) entity;
        E reference = getEntityReference(concreteEntity);
        List inconsistent = getCloner().populateEntity(concreteEntity,
                reference, getManager());
        if (!inconsistent.isEmpty()) {
            LOG.error("Could not find entities : " + inconsistent);
            throw new ManageableConsistencyException((Collection) inconsistent, null);
        }
        return entity;
    }

    @Override
    public void remove(I entity) {
        E concreteEntity = (E) entity;
        E toRemove = getEntityReference(concreteEntity);
        getManager().remove(toRemove);
    }

    @Override
    public I findById(BigInteger id) {
        if (id == null) {
            return null;
        }
        return (I) getCloner().clone(getManager().find(getEntityClass(), id));
    }

    @Override
    public List<I> findAll() {
        return findByQuery(getEntityQueryHolder().getSelectAll(), null);
    }

    @Override
    public List<BigInteger> collectIdsAll() {
        return collectIdsByQuery(getEntityQueryHolder().getCollectKeysAll(), null);
    }
}
