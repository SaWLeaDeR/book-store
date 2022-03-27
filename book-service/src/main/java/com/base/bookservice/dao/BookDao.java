package com.base.bookservice.dao;

import com.base.bookservice.model.document.BookDocument;
import com.base.bookservice.model.request.BookGetRequest;
import com.base.bookservice.model.request.delegate.BookUpdateDelegateRequest;
import com.base.model.response.ServiceResponse;

import java.util.List;

public interface BookDao {

    List<BookDocument> getBooks(BookGetRequest request);

    BookDocument getBook(String id);

    BookDocument save(BookDocument book);

    ServiceResponse<BookDocument> update(BookUpdateDelegateRequest request);
}
