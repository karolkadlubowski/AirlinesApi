package pl.polsl.airlinesapi.dao;

import java.io.Serializable;

public class GenericJpaDao< T extends Serializable>
        extends AbstractJpaDao< T > implements GenericDao< T > {

}
