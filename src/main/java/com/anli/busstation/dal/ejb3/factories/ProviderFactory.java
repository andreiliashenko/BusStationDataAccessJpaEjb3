package com.anli.busstation.dal.ejb3.factories;

import com.anli.busstation.dal.interfaces.providers.BSEntityProvider;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProviderFactory
        implements com.anli.busstation.dal.interfaces.factories.ProviderFactory {

    private static final Logger LOG = LoggerFactory.getLogger(ProviderFactory.class);

    @Override
    public <I extends BSEntityProvider> I getProvider(Class<I> abstraction) {
        try {
            return (I) InitialContext.doLookup(abstraction.getCanonicalName());
        } catch (NamingException namingException) {
            LOG.error("Could not lookup provider", namingException);
            throw new RuntimeException(namingException);
        }
    }
}
