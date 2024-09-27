package com.pluralsight.project.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

public class PageableMaker {

    private PageableMaker() {}

    private static final Integer PAGE_NO = 0;

    private static final Sort.Direction SORT_DIRECTION = Sort.Direction.ASC;

    private static final String SORT_COLUMN = "id";

    public static Pageable createPageable(Integer pageNumber, String sortDirection, String sortColumn) {
        Integer page = Objects.nonNull(pageNumber) ? pageNumber : PAGE_NO;
        Sort.Direction sort = Objects.nonNull(sortDirection) ? Sort.Direction.valueOf(sortDirection): SORT_DIRECTION;
        String sortByColumn = Objects.nonNull(sortColumn) ? sortColumn : SORT_COLUMN;

        return PageRequest.of(page, 10, sort, sortByColumn);
    }
}
