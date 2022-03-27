package com.base.bookservice.model.converter;

import com.base.bookservice.model.document.BookDocument;
import com.base.bookservice.model.dto.BookDto;
import com.base.converter.GenericConverter;
import org.springframework.stereotype.Component;

@Component
public class BookDtoConverter implements GenericConverter<BookDocument, BookDto> {

    @Override
    public BookDto convert(BookDocument source) {
        BookDto target = new BookDto();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setSeller(source.getSeller());
        target.setRemainingStock(source.getStock());
        return target;
    }
}
