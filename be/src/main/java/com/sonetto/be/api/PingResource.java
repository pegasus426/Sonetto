package com.sonetto.be.api;

import com.sonetto.be.api.dto.PingResponse;
import com.sonetto.be.service.GreetingService;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/ping")
@Produces(MediaType.APPLICATION_JSON)
public class PingResource {

    private final GreetingService greetingService;

    public PingResource(final GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GET
    public Uni<PingResponse> ping() {
        return greetingService.getGreeting()
                .map(PingResponse::new);
    }
}
