package org.ats.controller;

import org.ats.model.TvSerial;
import org.ats.repository.SerialRepository;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Path("/series")
public class TvSeriesController {

    private List<TvSerial> tvSerialList = new ArrayList<>();

    @Inject
    SerialRepository repository;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSeries(){
        tvSerialList = repository.listAll();
        return Response.ok(tvSerialList).build();
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSerialById(@PathParam("id") String id){
        TvSerial tvSerial = repository.findById(new ObjectId(id));
        return Response.ok(tvSerial).build();
    }


    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSerialByName(@PathParam("name") String name){
        List<TvSerial> tvSerial = repository.findByName(name);
        return Response.ok(tvSerial).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTvSerial(TvSerial tvSerial) throws URISyntaxException {
        repository.persist(tvSerial);
        return Response.created(new URI("/" + tvSerial.getId())).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTvSerial(@PathParam("id") String id, TvSerial tvSerial){

//        tvSerial.setId(new ObjectId(id));
        repository.update(tvSerial);
        return Response.ok(tvSerial).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response delete(@PathParam("id") String id){
//        TvSerial tvSerial = repository.findById(new ObjectId(id));
//        repository.delete(tvSerial);
        repository.delete(id);
        return Response.noContent().build();
    }


}
