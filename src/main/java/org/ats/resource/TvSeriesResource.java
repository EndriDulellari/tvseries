package org.ats.resource;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.ats.entity.TvSerial;
import org.ats.exceptions.RecordNotFoundException;
import org.ats.repository.SerialRepository;
import org.bson.types.ObjectId;
import org.jboss.resteasy.reactive.RestStreamElementType;
import org.springframework.web.bind.annotation.RequestBody;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/series")
public class TvSeriesResource {

    @Inject
    SerialRepository repository;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @RestStreamElementType(MediaType.APPLICATION_JSON)
    public Multi<TvSerial> getSeries() {
        return TvSerial.streamAllTvSeries();
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Optional<TvSerial> getSerialById(@PathParam("id") String id) {
        return repository.findByIdOptional(new ObjectId(id));
    }


    @GET
    @Path("/name/{name}")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @RestStreamElementType(MediaType.APPLICATION_JSON)
    public Multi<TvSerial> getSerialByName(@PathParam("name") String name) {
        return TvSerial.getSerialByName(name);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> addTvSerial(TvSerial tvSerial) {
        return tvSerial.persist().map(r -> Response.accepted(tvSerial).build());
    }

    @POST
    @Path("/repository")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTvSerialRep(@RequestBody TvSerial tvSerial) {
        repository.addSerial(tvSerial);
        return Response.accepted(tvSerial).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<TvSerial> updateTvSerial(@PathParam("id") String id, @RequestBody TvSerial tvSerial) {
        tvSerial.id = new ObjectId(id);
        return tvSerial.update();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") String id) {
        TvSerial tvSerial = repository.findById(new ObjectId(id));
        if (tvSerial == null) {
            throw new RecordNotFoundException("Serial by id " + id + " not found!");
        }
        tvSerial.delete();
        return Response.status(Response.Status.ACCEPTED).build();
    }
}
