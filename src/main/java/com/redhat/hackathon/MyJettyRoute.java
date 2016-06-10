package com.redhat.hackathon;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class MyJettyRoute extends RouteBuilder {

    private static final String HOST = "0.0.0.0";
    private static final String PORT = "8080";

    @Override public void configure() throws Exception {

        restConfiguration()
            .component("servlet")
            .host(HOST)
            .setPort(PORT);

        rest("/consultantlocations/").id("rest-consultant-locations-service").description("Blog for consultant locations").produces("application/json").consumes("application/json")

//        .get("search/id/{id}").description("Search for a blog article / id").id("rest-searchbyid")
//            .to("direct:searchById")
//
        .get("search").description("Search for consultant locations").id("rest-consultant-locations").outTypeList(ConsultantLocation.class)
             .to("direct:search")

        .put().id("rest-put-locations").description("Put consultant locations").type(ConsultantLocations.class)
             .to("direct:add");
        
        from("direct:search")
             .log("body is ${body}");
        
        from("direct:add")
             .log("body is ${body}");

//        .delete("{id}").id("rest-deletearticle").description("Delete a blog article").type(Blog.class)
//            .to("direct:remove");

    }
}
