package com.base.bookservice.service.impl;

import com.base.bookservice.model.dto.BookDto;
import com.base.bookservice.model.request.BookCreateRequest;
import com.base.bookservice.model.request.BookGetRequest;
import com.base.bookservice.model.request.BookUpdateRequest;
import com.base.bookservice.model.request.delegate.BookUpdateDelegateRequest;
import com.base.bookservice.service.BookService;
import com.base.bookservice.service.delegate.BookDelegateService;
import com.base.bookservice.validation.model.enumeration.BookRequestType;
import com.base.bookservice.validation.service.BookValidationService;
import com.base.converter.GenericConverter;
import com.base.model.dto.ListDto;
import com.base.model.response.ServiceResponse;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private final BookValidationService bookValidationService;
    private final BookDelegateService customerDelegateService;
    private final GenericConverter<BookUpdateRequest, BookUpdateDelegateRequest> bookUpdateDelegateRequestConverter;

    public BookServiceImpl(BookValidationService bookValidationService,
                           BookDelegateService customerDelegateService,
                           GenericConverter<BookUpdateRequest, BookUpdateDelegateRequest> bookUpdateDelegateRequestConverter) {
        this.bookValidationService = bookValidationService;
        this.customerDelegateService = customerDelegateService;
        this.bookUpdateDelegateRequestConverter = bookUpdateDelegateRequestConverter;
    }

    @Override
    public ServiceResponse<ListDto<BookDto>> getBook(BookGetRequest request) {
        return bookValidationService.validateOperation(BookRequestType.GET, request)
            .ifSuccessfullyPresent(success -> customerDelegateService.getBooks(success.getData().get()));
    }

    @Override
    public ServiceResponse<BookDto> saveBook(BookCreateRequest request) {
        return bookValidationService.validateOperation(BookRequestType.CREATE, request)
            .ifSuccessfullyPresent(success -> customerDelegateService.saveBook(success.getData().get()));
    }

    @Override
    public ServiceResponse<BookDto> updateBook(String id, BookUpdateRequest request) {
        final BookUpdateDelegateRequest delegateRequest = bookUpdateDelegateRequestConverter.convert(request);
        delegateRequest.setId(id);
        return bookValidationService.validateOperation(BookRequestType.UPDATE, delegateRequest)
            .ifSuccessfullyPresent(success -> customerDelegateService.updateBook(success.getData().get()));
    }

}
