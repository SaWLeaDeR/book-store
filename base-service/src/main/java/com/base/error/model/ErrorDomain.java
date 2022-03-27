package com.base.error.model;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ErrorDomain {

    private final String topDomain;
    private final String compositeDomain;

    private ErrorDomain(String topDomain, String compositeDomain) {
        this.topDomain = topDomain;
        this.compositeDomain = compositeDomain;
    }

    public String getTopDomain() {
        return topDomain;
    }

    public String getCompositeDomain() {
        return compositeDomain;
    }

    public static ErrorDomain fromDomains(String... domains) {
        String compositeDomain = getCompositeDomain(domains);

        return new ErrorDomain(getTopDomainName(compositeDomain), compositeDomain);
    }

    private static String getCompositeDomain(String... domains) {
        return Stream.of(domains).collect(Collectors.joining("."));
    }

    private static String getTopDomainName(String domainName) {
        int dotIndex = domainName.indexOf('.');
        return dotIndex <= 0 ? domainName : domainName.substring(0, dotIndex);
    }
}
