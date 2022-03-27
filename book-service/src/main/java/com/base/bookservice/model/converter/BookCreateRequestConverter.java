package com.base.bookservice.model.converter;

import com.base.bookservice.model.document.BookDocument;
import com.base.bookservice.model.request.BookCreateRequest;
import com.base.converter.GenericConverter;
import org.springframework.stereotype.Component;

@Component
public class BookCreateRequestConverter implements GenericConverter<BookCreateRequest, BookDocument> {

    @Override
    public BookDocument convert(BookCreateRequest source) {
        BookDocument target = new BookDocument();
        target.setName(source.getName());
        target.setStock(source.getStockCount());
        target.setSeller(source.getSeller());
        return target;
    }
}
