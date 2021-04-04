package com.serrodcal;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.quarkus.vertx.web.Body;
import io.quarkus.vertx.web.Param;
import io.quarkus.vertx.web.Route;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
//import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Objects;

@Singleton
public class PersonResource {

    //private static final Logger log = Logger.getLogger(PersonResource.class);

    @Inject
    PersonService service;


    @Route(path = "/person", methods = HttpMethod.GET, produces = "application/json")
    public void findAll(RoutingContext rc) {
        this.service.findAll().subscribe().with(
            result -> {
                if (Objects.nonNull(result) && result.size() > 0)
                    rc.response().end(Json.encode(result));
                else
                    rc.response().setStatusCode(HttpResponseStatus.NOT_FOUND.code()).end();
            }
            , failure -> {
                    rc.response()
                        .putHeader("Content-Type", "text/plain")
                        .setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code())
                        .end(failure.getMessage());
            }
        );
    }

    @Route(path = "/person/:name", methods = HttpMethod.GET, produces = "application/json")
    public void findByName(RoutingContext rc, @Param("name") String name) {
        this.service.findByName(name).subscribe().with(
            result -> {
                if (Objects.nonNull(result))
                    rc.response().end(Json.encode(result));
                else
                    rc.response().setStatusCode(HttpResponseStatus.NOT_FOUND.code()).end();
            }
            , failure -> {
                    rc.response()
                        .putHeader("Content-Type", "text/plain")
                        .setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code())
                        .end(failure.getMessage());
            }
        );
    }

    @Route(path = "/person/alive", methods = HttpMethod.GET, produces = "application/json")
    public void findAlive(RoutingContext rc) {
        this.service.findAlive().subscribe().with(
            result -> {
                if (Objects.nonNull(result))
                    rc.response().end(Json.encode(result));
                else
                    rc.response().setStatusCode(HttpResponseStatus.NOT_FOUND.code()).end();
            }
            , failure -> {
                rc.response()
                    .putHeader("Content-Type", "text/plain")
                    .setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code())
                    .end(failure.getMessage());
            }
        );
    }

    @Route(path = "/person", methods = HttpMethod.POST, consumes = "application/json")
    public void createPerson(RoutingContext rc, @Body PersonPayload personPayload) {
        this.service.createPerson(new PersonDTO(personPayload.name, personPayload.birth, personPayload.status))
        .subscribe().with(
                result -> rc.response().setStatusCode(HttpResponseStatus.CREATED.code()).end()
                , failure -> {
                    rc.response()
                        .putHeader("Content-Type", "text/plain")
                        .setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code())
                        .end(failure.getMessage());
                }
            );
    }

    @Route(path = "/person/:name", methods = HttpMethod.DELETE)
    public void deletePerson(RoutingContext rc, @Param("name") String name) {
        this.service.deletePerson(name).subscribe().with(
            result -> {
                if (Objects.nonNull(result))
                    rc.response().setStatusCode(HttpResponseStatus.NO_CONTENT.code()).end();
                else
                    rc.response().setStatusCode(HttpResponseStatus.NOT_FOUND.code()).end();
            },
            failure -> {
                rc.response()
                    .putHeader("Content-Type", "text/plain")
                    .setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code())
                    .end(failure.getMessage());
            }
        );
    }

}