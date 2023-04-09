package com.tmt.project.webnghenhac.service.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateArtistResponse {
    private Integer id;
    private String name;
    private String description;
    private String dateOfBirth;
    private String placeOfBirth;
}
