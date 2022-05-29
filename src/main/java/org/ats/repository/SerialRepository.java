package org.ats.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.ats.entity.TvSerial;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SerialRepository implements PanacheMongoRepository<TvSerial> {

    public void addSerial (TvSerial tvSerial){
        persist(tvSerial);
    }
}
