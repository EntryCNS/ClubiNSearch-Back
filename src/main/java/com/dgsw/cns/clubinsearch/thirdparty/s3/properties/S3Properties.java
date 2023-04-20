package com.dgsw.cns.clubinsearch.thirdparty.s3.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties("cloud.aws.s3")
@Configuration
public class S3Properties {
    private String bucket;
}
