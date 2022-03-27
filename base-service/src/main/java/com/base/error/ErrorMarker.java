package com.base.error;

import com.base.error.model.ServiceErrorDetail;
import com.base.error.model.ErrorDomain;

public interface ErrorMarker {

    ServiceErrorDetail getErrorToken(String... domains);

    ServiceErrorDetail getErrorToken(ErrorDomain domain);
}
