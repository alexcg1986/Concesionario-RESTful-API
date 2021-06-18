package com.teknei.concesionario.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@Configuration
@PropertySource("classpath:filenet.properties")

public class FileNetConfig {

    @Value("${uri}")
    private String uri;
    @Value("${jaas_stanza}")
    private String jaas_stanza;
    @Value("${doc_class}")
    private String doc_class;
    @Value("${folder_path}")
    private String path;
}
