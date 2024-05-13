package com.hrassociation.webApp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data // génère getter et setter
@Configuration // déclare la classe en tant que bean de configuration
@ConfigurationProperties(prefix = "com.hrassociation.webapp") // on récupère toutes les properties avec cette
                                                              // nomenclature
public class CustomProperties {

  private String apiUrl;
}
