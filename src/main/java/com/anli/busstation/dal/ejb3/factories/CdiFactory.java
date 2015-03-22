package com.anli.busstation.dal.ejb3.factories;

import com.anli.configuration.Configurator;
import java.lang.annotation.Annotation;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CdiFactory {

    private static final Logger LOG = LoggerFactory.getLogger(CdiFactory.class);

    protected BeanManager beanManager;

    protected BeanManager getBeanManager() {
        if (beanManager == null) {
            try {
                this.beanManager = InitialContext.doLookup(Configurator.getCdiBeanManagerJndiName());
            } catch (NamingException namingException) {
                LOG.error("Could not lookup BeanManager", namingException);
                throw new RuntimeException(namingException);
            }
        }
        return beanManager;
    }

    public <T> T getBean(Class<T> beanClass, Annotation... qualifiers) {
        BeanManager manager = getBeanManager();
        Bean<T> beanObject = (Bean<T>) manager.resolve(manager.getBeans(beanClass, qualifiers));
        return (T) manager.getReference(beanObject, beanClass, manager.createCreationalContext(beanObject));
    }
}
