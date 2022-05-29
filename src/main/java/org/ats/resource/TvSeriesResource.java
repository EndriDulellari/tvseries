package org.ats.resource;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntityBase;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.ats.entity.TvSerial;
import org.ats.exceptions.RecordNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/series")
public class TvSeriesResource {




    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<TvSerial> getSeries(){
        return TvSerial.streamAllTvSeries();
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<TvSerial> getSerialById(@PathParam("id") String id){
        return TvSerial.getSerialById(id);
    }


    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<TvSerial> getSerialByName(@PathParam("name") String name){
        return TvSerial.getSerialByName(name);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTvSerial(TvSerial tvSerial){
        tvSerial.persist();
        return Response.created(URI.create("/persons/" + tvSerial.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<TvSerial>  updateTvSerial(@PathParam("id") String id, @RequestBody TvSerial tvSerial){
        return tvSerial.update();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Object> delete(@PathParam("id") String id){
        Uni<TvSerial> tvSerial = TvSerial.findById(new ObjectId(id));
        if(tvSerial == null){
            throw new RecordNotFoundException("Serial by id " + id + " not found!");
        }
        return tvSerial.map(ReactivePanacheMongoEntityBase::delete);
    }


}
