package com.pluralsight.project.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDto {

    private Integer pageNo = 0;

//    private Integer pageSize = 10;

    private Sort.Direction sort = Sort.Direction.ASC;

    private String sortByColumn = "id";

    public Pageable getPageable(PageRequestDto dto) {
        Integer page = Objects.nonNull(dto.getPageNo()) ? dto.getPageNo() : this.pageNo;
        Sort.Direction sort = Objects.nonNull(dto.getSort()) ? dto.getSort() : this.sort;
        String sortByColumn = Objects.nonNull(dto.getSortByColumn()) ? dto.getSortByColumn() : this.sortByColumn;

        return PageRequest.of(page, 10, sort, sortByColumn);
    }
}
