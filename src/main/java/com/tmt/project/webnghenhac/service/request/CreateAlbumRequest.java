package com.tmt.project.webnghenhac.service.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateAlbumRequest {
    private String title;
    private String description;
    private Integer releaseYear;
    private String genre;
    private String publisher;
}
