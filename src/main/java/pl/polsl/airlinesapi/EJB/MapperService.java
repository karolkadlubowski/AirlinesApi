package pl.polsl.airlinesapi.EJB;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ejb.Singleton;

@Singleton
public class MapperService {
    public ObjectMapper objectMapper;

    public MapperService(){
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
}
