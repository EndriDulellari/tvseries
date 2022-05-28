package org.ats.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Sort;
import org.ats.model.TvSerial;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class SerialRepository implements PanacheMongoRepository<TvSerial> {

    public List<TvSerial> findByName(String name){
        return find("name", name).list();
    }

    public List<TvSerial> findOrderedName(){
        return listAll(Sort.by("name"));
    }
}
