package com.base.bookservice.service;

import com.base.bookservice.model.dto.BookDto;
import com.base.bookservice.model.request.BookCreateRequest;
import com.base.bookservice.model.request.BookGetRequest;
import com.base.bookservice.model.request.BookUpdateRequest;
import com.base.model.dto.ListDto;
import com.base.model.response.ServiceResponse;

public interface BookService {

    ServiceResponse<ListDto<BookDto>> getBook(BookGetRequest request);

    ServiceResponse<BookDto> saveBook(BookCreateRequest request);

    ServiceResponse<BookDto> updateBook(String id, BookUpdateRequest request);
}
