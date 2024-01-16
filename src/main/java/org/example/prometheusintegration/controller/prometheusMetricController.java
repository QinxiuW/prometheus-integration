package org.example.prometheusintegration.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * prometheusMetricController.
 *
 * @Description TODO
 * @Date 16/1/24 15:45
 * @Created by qinxiuwang
 */
@RestController
public class prometheusMetricController {

  private final Counter myCounter;

  public prometheusMetricController(MeterRegistry registry) {
    this.myCounter = Counter.builder("my_custom_counter").description("A custom counter metric")
      .register(registry);
  }

  @GetMapping("/increment")
  public ResponseEntity<String> incrementCounter() {
    myCounter.increment();
    return ResponseEntity.ok().body("OK");
  }
}
