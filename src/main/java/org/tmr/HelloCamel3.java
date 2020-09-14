package org.tmr;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.Exchange;

public class HelloCamel3 extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    
    from("platform-http:/bye?httpMethodRestrict=GET")
      .setBody().constant("Bye")
      .marshal().json();
    
    rest("/hello")
      .get().to("direct:hello");

    from("direct:hello")
      .setHeader(Exchange.CONTENT_TYPE, constant("text/plain"))
      .transform().constant("Hello");

  }

}