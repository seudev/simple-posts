package com.seudev.simple_posts.module.feed.tag;

import static com.seudev.simple_posts.module.api.ApiHeaders.X_TOTAL_COUNT_REF;
import static com.seudev.simple_posts.module.api.ApiResponses.INTERNAL_SERVER_ERROR_REF;
import static com.seudev.simple_posts.module.api.ApiResponses.NOT_FOUND_ERROR_REF;
import static com.seudev.simple_posts.module.api.ApiResponses.SEARCHED_CODE;
import static com.seudev.simple_posts.module.api.ApiResponses.UNAVAILABLE_SERVICE_ERROR_REF;
import static com.seudev.simple_posts.module.api.ApiResponses.searched;
import static com.seudev.simple_posts.module.api.ApiTags.FEED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.eclipse.microprofile.openapi.annotations.enums.SchemaType.ARRAY;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.seudev.simple_posts.module.api.model.SearchResult;
import com.seudev.simple_posts.module.post.PostResponse;

import org.eclipse.microprofile.metrics.annotation.ConcurrentGauge;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@ApplicationScoped
@Path("/tag/{tag}/feed")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class TagFeedSearchResource {

    @Context
    UriInfo uriInfo;

    @Inject
    TagFeedSearchService service;

    @GET
    // OpenAPI
    @Tag(ref = FEED)
    @Operation(summary = "Search the post feed of a given tag.")
    @APIResponse(
        responseCode = SEARCHED_CODE, description = "The search was successfully returned.",
        headers = @Header(ref = X_TOTAL_COUNT_REF),
        content = @Content(schema = @Schema(type = ARRAY, implementation = PostResponse.class)))
    @APIResponse(ref = NOT_FOUND_ERROR_REF)
    @APIResponse(ref = INTERNAL_SERVER_ERROR_REF)
    @APIResponse(ref = UNAVAILABLE_SERVICE_ERROR_REF)

    // Metrics
    @Timed(name = "time")
    @Metered(name = "frequency")
    @ConcurrentGauge(name = "concurrent_count")
    public Response create(@BeanParam TagFeedSearchParams params) {
        SearchResult<PostResponse> result = service.search(params);
        return searched(result);
    }

}
