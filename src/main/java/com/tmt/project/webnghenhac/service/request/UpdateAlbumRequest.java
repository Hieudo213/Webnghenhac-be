package com.tmt.project.webnghenhac.service.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateAlbumRequest {
    private String title;
    private String description;
    private Integer releaseYear;
    private String publisher;
    private String genre;
    private String length;
    private Integer categoryId;
}
