package org.ats.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntityBase;
import io.smallrye.mutiny.Uni;

import java.net.URL;
import java.util.Set;

@MongoEntity
public class TvSerial extends ReactivePanacheMongoEntity {

    private String id;
    private String name;
    private URL url;
    private String summary;
    private String language;
    private Set<String> generes;
    private URL officialSite;


    public Uni<TvSerial> getSerial(String id){
        return ReactivePanacheMongoEntityBase.findById(id);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Set<String> getGeneres() {
        return generes;
    }

    public void setGeneres(Set<String> generes) {
        this.generes = generes;
    }

    public URL getOfficialSite() {
        return officialSite;
    }

    public void setOfficialSite(URL officialSite) {
        this.officialSite = officialSite;
    }
}
