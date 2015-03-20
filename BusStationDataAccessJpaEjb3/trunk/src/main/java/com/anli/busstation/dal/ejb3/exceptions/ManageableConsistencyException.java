package com.anli.busstation.dal.ejb3.exceptions;

import com.anli.busstation.dal.exceptions.ConsistencyException;
import com.anli.busstation.dal.interfaces.entities.BSEntity;
import java.util.Collection;
import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class ManageableConsistencyException extends ConsistencyException {

    public ManageableConsistencyException(Collection<BSEntity> inconsistentEntities, Throwable cause) {
        super(inconsistentEntities, cause);
    }
}
