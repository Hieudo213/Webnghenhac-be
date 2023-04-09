package com.tmt.project.webnghenhac.service.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateSongRequest {
    private Integer id;
    private String title;
    private String length;
    private Integer albumId;
    private Integer singleId;
    private Integer playlistId;
    private Integer musicId;
}
