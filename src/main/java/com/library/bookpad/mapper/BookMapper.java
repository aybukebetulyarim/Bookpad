package com.library.bookpad.mapper;

import com.library.bookpad.entity.Book;
import com.library.bookpad.resource.response.book.BookResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(source = "id", target = "id")
    BookResponse entityToResponse(Book book);

    List<BookResponse> entityListToResponseList(List<Book> bookList);

    @Mapping(source = "id", target = "id")
    Book responseEntity(BookResponse bookResponse);
}
