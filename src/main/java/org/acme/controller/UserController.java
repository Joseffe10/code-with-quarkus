package org.acme.controller;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entity.UserEntity;
import org.acme.service.UserService;


@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GET
    public Response findAll(@QueryParam("page") @DefaultValue("0") Integer page,
                            @QueryParam("pageSize") @DefaultValue("10") Integer pageSize) {
        var users = userService.findAll(page, pageSize);

        return Response.ok(users).build();
    }

    @POST
    @Transactional
    public Response createUser(UserEntity userEntity) {
        return Response.ok(userService.createUser(userEntity)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateUser(@PathParam("id") Integer userId, UserEntity userEntity) {
        return Response.ok(userService.updateUser(userId, userEntity)).build();
    }


    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Integer userId) {
        return Response.ok(userService.findById(userId)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteById(@PathParam("id") Integer userId) {
        userService.deleteById(userId);
        return Response.noContent().build();
    }
}