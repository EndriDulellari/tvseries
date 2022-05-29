package org.ats.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.ats.entity.TvSerial;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class SerialRepository implements PanacheMongoRepository<TvSerial> {

    public List<TvSerial> findByName(String name){
        return find("name", name).list();
    }

    public void addSerial (TvSerial tvSerial){
        persist(tvSerial);
    }
}
