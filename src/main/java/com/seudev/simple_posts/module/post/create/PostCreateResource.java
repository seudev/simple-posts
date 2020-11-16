package com.seudev.simple_posts.module.post.create;

import static com.seudev.simple_posts.module.api.ApiParameters.USER_ID_REF;
import static com.seudev.simple_posts.module.api.ApiResponses.CREATED_CODE;
import static com.seudev.simple_posts.module.api.ApiResponses.INTERNAL_SERVER_ERROR_REF;
import static com.seudev.simple_posts.module.api.ApiResponses.NOT_FOUND_ERROR_REF;
import static com.seudev.simple_posts.module.api.ApiResponses.UNAVAILABLE_SERVICE_ERROR_REF;
import static com.seudev.simple_posts.module.api.ApiResponses.VALIDATION_ERROR_REF;
import static com.seudev.simple_posts.module.api.ApiResponses.created;
import static com.seudev.simple_posts.module.api.ApiTags.BLOG_POST;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.seudev.simple_posts.module.post.PostResponse;

import org.eclipse.microprofile.metrics.annotation.ConcurrentGauge;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@ApplicationScoped
@Path("/user/{userId}/post")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class PostCreateResource {

    @Context
    UriInfo uriInfo;

    @Inject
    PostCreateService service;

    @POST
    // OpenAPI
    @Tag(ref = BLOG_POST)
    @Operation(summary = "Create a given blog post for a given user.")
    @Parameter(ref = USER_ID_REF)
    @APIResponse(
        responseCode = CREATED_CODE, description = "The resource was successfully created.",
        content = @Content(schema = @Schema(implementation = PostResponse.class)))
    @APIResponse(ref = NOT_FOUND_ERROR_REF, description = "user not found")
    @APIResponse(ref = VALIDATION_ERROR_REF)
    @APIResponse(ref = INTERNAL_SERVER_ERROR_REF)
    @APIResponse(ref = UNAVAILABLE_SERVICE_ERROR_REF)

    // Metrics
    @Timed(name = "time")
    @Metered(name = "frequency")
    @ConcurrentGauge(name = "concurrent_count")
    public Response create(@PathParam("userId") UUID userId, PostCreateRequest request) {
        PostResponse response = service.create(userId, request);
        return created(uriInfo, response);
    }

}
