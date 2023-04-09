package com.tmt.project.webnghenhac.service.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateSingleResponse {
    private String title;
    private String description;
    private String publisher;
    private String genre;
    private Integer releaseYear;
    private String length;
    private Integer categoryId;
}
