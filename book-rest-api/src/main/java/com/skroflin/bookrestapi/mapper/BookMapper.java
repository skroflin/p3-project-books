package com.skroflin.bookrestapi.mapper;

import com.skroflin.bookrestapi.dto.BookRequest;
import com.skroflin.bookrestapi.dto.BookResponse;
import com.skroflin.bookrestapi.model.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookResponse toResponse(BookEntity entity);

    List<BookResponse> toResponseList(List<BookEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "bookId", ignore = true)
    BookEntity toEntity(BookRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "bookId", ignore = true)
    void updateEntityFromRequest(@MappingTarget BookRequest request, @MappingTarget BookEntity entity);
}
