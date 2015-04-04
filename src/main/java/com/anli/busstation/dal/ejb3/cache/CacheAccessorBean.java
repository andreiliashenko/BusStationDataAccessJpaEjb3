package com.anli.busstation.dal.ejb3.cache;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@Deprecated
@Remote(CacheAccessor.class)
@Stateless
public class CacheAccessorBean implements CacheAccessor {

    @PersistenceContext(unitName = "busstation")
    protected EntityManager em;

    @Override
    @TransactionAttribute(REQUIRED)
    public void resetCaches() {
        em.getEntityManagerFactory().getCache().evictAll();
    }
}
