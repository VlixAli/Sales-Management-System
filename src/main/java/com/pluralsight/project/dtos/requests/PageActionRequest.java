package com.pluralsight.project.dtos.requests;

import com.pluralsight.project.validations.annotations.InValues;
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

    @InValues(value = {"ASC", "DESC"}, message = "the value must be either ASC or DESC")
    private String sort;

    @InValues(value = {"id", "descriptionAr", "descriptionEn", "actionTime", "traceId"},
            message = "the value must be in [id, descriptionAr, descriptionEn, actionTime, traceId]")
    private String sortByColumn;

    public Sort.Direction sortToDirection(String value) {
        if (value == "DESC") {
            return Sort.Direction.DESC;
        } else {
            return Sort.Direction.ASC;
        }
    }
}
