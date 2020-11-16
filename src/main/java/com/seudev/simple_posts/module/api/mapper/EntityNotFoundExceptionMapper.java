package com.seudev.simple_posts.module.api.mapper;

import static com.seudev.simple_posts.module.api.ApiResponses.notFoundError;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.seudev.simple_posts.module.api.EntityNotFoundException;

import org.eclipse.microprofile.metrics.Meter;
import org.jboss.logging.Logger;

import io.jaegertracing.internal.metrics.Metric;

@Provider
@ApplicationScoped
public class EntityNotFoundExceptionMapper implements ExceptionMapper<EntityNotFoundException> {

    private static final Logger logger = Logger.getLogger(EntityNotFoundExceptionMapper.class);

    @Inject
    @Metric(name = "frequency")
    Meter meter;

    @Override
    public Response toResponse(EntityNotFoundException ex) {
        meter.mark();
        logger.debugf(ex, ex.getMessage());
        return notFoundError();
    }

}
