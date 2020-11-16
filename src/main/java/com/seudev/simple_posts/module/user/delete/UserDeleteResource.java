package com.seudev.simple_posts.module.user.delete;

import static com.seudev.simple_posts.module.api.ApiParameters.ID;
import static com.seudev.simple_posts.module.api.ApiParameters.ID_REF;
import static com.seudev.simple_posts.module.api.ApiResponses.DELETED_REF;
import static com.seudev.simple_posts.module.api.ApiResponses.INTERNAL_SERVER_ERROR_REF;
import static com.seudev.simple_posts.module.api.ApiResponses.NOT_FOUND_ERROR_REF;
import static com.seudev.simple_posts.module.api.ApiResponses.UNAVAILABLE_SERVICE_ERROR_REF;
import static com.seudev.simple_posts.module.api.ApiResponses.deleted;
import static com.seudev.simple_posts.module.api.ApiTags.USER;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.metrics.annotation.ConcurrentGauge;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@ApplicationScoped
@Path("/user/{id}")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class UserDeleteResource {

    @Inject
    UserDeleteService service;

    @DELETE
    // OpenAPI
    @Tag(ref = USER)
    @Operation(summary = "Delete a given user.")
    @Parameter(ref = ID_REF)
    @APIResponse(ref = DELETED_REF)
    @APIResponse(ref = NOT_FOUND_ERROR_REF)
    @APIResponse(ref = INTERNAL_SERVER_ERROR_REF)
    @APIResponse(ref = UNAVAILABLE_SERVICE_ERROR_REF)

    // Metrics
    @Timed(name = "time")
    @Metered(name = "frequency")
    @ConcurrentGauge(name = "concurrent_count")
    public Response delete(@PathParam(ID) UUID id) {
        service.delete(id);
        return deleted();
    }

}
