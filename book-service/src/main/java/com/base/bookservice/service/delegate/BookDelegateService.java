package com.base.bookservice.service.delegate;

import com.base.bookservice.model.dto.BookDto;
import com.base.bookservice.model.request.BookCreateRequest;
import com.base.bookservice.model.request.BookGetRequest;
import com.base.bookservice.model.request.delegate.BookUpdateDelegateRequest;
import com.base.model.dto.ListDto;
import com.base.model.response.ServiceResponse;

public interface BookDelegateService {

    ServiceResponse<ListDto<BookDto>> getBooks(BookGetRequest request);

    ServiceResponse<BookDto> getBook(String id);

    ServiceResponse<BookDto> saveBook(BookCreateRequest bookCreateRequest);

    ServiceResponse<BookDto> updateBook(BookUpdateDelegateRequest bookUpdateRequest);
}
