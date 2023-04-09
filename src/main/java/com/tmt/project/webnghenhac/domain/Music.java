package com.tmt.project.webnghenhac.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "music")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 40)
    @Column(name = "music_name", length = 40)
    private String musicName;

    @Size(max = 200)
    @Column(name = "music_url", length = 200)
    private String musicURL;

    @Size(max = 500)
    @Column(name = "music_type", length = 500)
    private String musicType;

    @Size(max = 20)
    @Column(name = "upload_date", length = 20)
    private LocalDate uploadDate;
}
