package com.base.bookservice.controller;

import com.base.bookservice.controller.endpoint.BookEndpoint;
import com.base.bookservice.model.dto.BookDto;
import com.base.bookservice.model.request.BookCreateRequest;
import com.base.bookservice.model.request.BookGetRequest;
import com.base.bookservice.model.request.BookUpdateRequest;
import com.base.bookservice.service.BookService;
import com.base.controller.GenericController;
import com.base.controller.Runner;
import com.base.model.dto.ListDto;
import com.base.model.response.EndpointResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController extends GenericController<BookService> {

    public BookController(Runner<BookService, BookDto> runner,
                          BookService service) {
        setRunner(runner);
        setServiceProvider(service);
    }

    @PostMapping(value = BookEndpoint.BOOK)
    public ResponseEntity<EndpointResponse<BookDto>> saveBook(@RequestBody BookCreateRequest request) {
        return run(service -> service.saveBook(request));
    }

    @GetMapping(value = BookEndpoint.BOOK)
    public ResponseEntity<EndpointResponse<ListDto<BookDto>>> getBook(@ModelAttribute BookGetRequest request) {
        return run(service -> service.getBook(request));
    }

    @PutMapping(value = BookEndpoint.UPDATE_BOOK)
    public ResponseEntity<EndpointResponse<BookDto>> updateBook(@PathVariable String id,
                                                                @RequestBody BookUpdateRequest request) {
        return run(service -> service.updateBook(id, request));
    }
}
