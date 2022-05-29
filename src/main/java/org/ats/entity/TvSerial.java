package org.ats.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@MongoEntity
public class TvSerial extends ReactivePanacheMongoEntity {

    private String name;
    private URL url;
    private String summary;
    private String language;
    private Set<String> generes;
    private URL officialSite;

    public static Uni<TvSerial> getSerialById(String id) {
        return findById(id);
    }

    public static Multi<TvSerial> getSerialByName(String name) {
        return find("name", name).stream();
    }

    public static Multi<TvSerial> streamAllTvSeries() {
        return streamAll();
    }
}
