package com.base.bookservice.model.converter;

import com.base.bookservice.model.request.BookUpdateRequest;
import com.base.bookservice.model.request.delegate.BookUpdateDelegateRequest;
import com.base.converter.GenericConverter;
import org.springframework.stereotype.Component;

@Component
public class BookUpdateDelegateRequestConverter implements GenericConverter<BookUpdateRequest, BookUpdateDelegateRequest> {

    @Override
    public BookUpdateDelegateRequest convert(BookUpdateRequest source) {
        BookUpdateDelegateRequest target = new BookUpdateDelegateRequest();
        target.setName(source.getName());
        target.setSeller(source.getSeller());
        target.setStock(source.getStock());
        return target;
    }
}
