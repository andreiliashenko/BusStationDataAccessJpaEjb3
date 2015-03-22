package com.anli.busstation.dal.ejb3.factories;

import com.anli.busstation.dal.interfaces.providers.BSEntityProvider;

public class ProviderFactory extends CdiFactory
        implements com.anli.busstation.dal.interfaces.factories.ProviderFactory {

    @Override
    public <I extends BSEntityProvider> I getProvider(Class<I> abstraction) {
        return getBean(abstraction);
    }
}
