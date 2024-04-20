package com.pluralsight.project.dtos.requests;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class PageActionRequest {

    private String username;
    private String be;
    private String application;
    private Long traceId;
    private String param;

    private String paramTypeEn;

    private Integer pageNo;

    private Sort.Direction sort;

    private String sortByColumn;
}
