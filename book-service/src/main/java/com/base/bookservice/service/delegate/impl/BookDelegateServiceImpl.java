package com.base.bookservice.service.delegate.impl;

import com.base.aspect.annotation.InternalLogger;
import com.base.bookservice.dao.BookDao;
import com.base.bookservice.model.document.BookDocument;
import com.base.bookservice.model.dto.BookDto;
import com.base.bookservice.model.request.BookCreateRequest;
import com.base.bookservice.model.request.BookGetRequest;
import com.base.bookservice.model.request.delegate.BookUpdateDelegateRequest;
import com.base.bookservice.service.delegate.BookDelegateService;
import com.base.converter.GenericConverter;
import com.base.model.dto.ListDto;
import com.base.model.response.ServiceResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookDelegateServiceImpl implements BookDelegateService {

    private final BookDao bookDao;
    private final GenericConverter<BookDocument, BookDto> bookDtoConverter;
    private final GenericConverter<BookCreateRequest, BookDocument> bookCreateRequestConverter;

    public BookDelegateServiceImpl(BookDao bookDao,
                                   GenericConverter<BookDocument, BookDto> bookDtoConverter,
                                   GenericConverter<BookCreateRequest, BookDocument> bookCreateRequestConverter) {
        this.bookDao = bookDao;
        this.bookDtoConverter = bookDtoConverter;
        this.bookCreateRequestConverter = bookCreateRequestConverter;
    }

    @Override
    @InternalLogger(name = "getBook")
    public ServiceResponse<ListDto<BookDto>> getBooks(BookGetRequest request) {
        final List<BookDocument> bookDocuments = bookDao.getBooks(request);
        final List<BookDto> customers = bookDtoConverter.convert(bookDocuments);
        return ServiceResponse.of(new ListDto<>(bookDocuments.size(), customers));
    }

    @Override
    @InternalLogger(name = "getSingleBook")
    public ServiceResponse<BookDto> getBook(String id) {
        final BookDocument bookDocument = bookDao.getBook(id);
        final BookDto book = bookDtoConverter.convert(bookDocument);
        return ServiceResponse.of(book);
    }

    @Override
    @InternalLogger(name = "saveBook")
    public ServiceResponse<BookDto> saveBook(BookCreateRequest bookCreateRequest) {
        final BookDocument document = bookCreateRequestConverter.convert(bookCreateRequest);
        final BookDocument bookDocument = bookDao.save(document);
        final BookDto customer = bookDtoConverter.convert(bookDocument);
        return ServiceResponse.of(customer);
    }

    @Override
    @InternalLogger(name = "updateBook")
    public ServiceResponse<BookDto> updateBook(BookUpdateDelegateRequest bookUpdateRequest) {
        return bookDao.update(bookUpdateRequest)
            .ifSuccessfullyPresent(success -> ServiceResponse.of(bookDtoConverter.convert(success.getData().get())));
    }
}
