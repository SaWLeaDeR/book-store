package com.base.error;

import com.base.error.model.ServiceErrorDetail;
import com.base.error.model.ErrorDomain;
import com.base.exception.PropertyLoadingException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class GenericErrorMarker implements ErrorMarker {


    private Properties errorTokenProperties;

    public GenericErrorMarker(@Qualifier("errorTokenProperties") Properties errorTokenProperties) {
        this.errorTokenProperties = errorTokenProperties;
    }


    @Override
    public ServiceErrorDetail getErrorToken(String... domains) {
        ErrorDomain domain = ErrorDomain.fromDomains(domains);
        return this.getErrorToken(domain);
    }

    @Override
    public ServiceErrorDetail getErrorToken(ErrorDomain domain) {
        String code = this.getCode(domain.getCompositeDomain());
        String message = this.getMessage(domain.getCompositeDomain());
        return new ServiceErrorDetail(code, domain.getTopDomain(), message);
    }

    private String getCode(String domainName) {
        if (null != domainName && !domainName.isEmpty()) {
            String tokenCodePropertyName = this.getTokenCodePropertyName(domainName);
            if (this.errorTokenProperties.containsKey(tokenCodePropertyName)) {
                return this.errorTokenProperties.getProperty(tokenCodePropertyName);
            } else {
                throw new PropertyLoadingException(String.format("ErrorToken code cannot be found for: %s", tokenCodePropertyName));
            }
        } else {
            throw new PropertyLoadingException("Error namespaces cannot be empty");
        }
    }

    private String getMessage(String domainName) {
        String tokenMessagePropertyName = this.getTokenMessagePropertyName(domainName);
        if (this.errorTokenProperties.containsKey(tokenMessagePropertyName)) {
            return this.errorTokenProperties.getProperty(tokenMessagePropertyName);
        } else {
            throw new PropertyLoadingException(String.format("ErrorToken message cannot be found for: %s", tokenMessagePropertyName));
        }
    }

    private String getTokenCodePropertyName(String domainName) {
        return domainName + ".code";
    }

    private String getTokenMessagePropertyName(String domainName) {
        return domainName + ".message";
    }
}
