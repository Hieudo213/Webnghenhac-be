package com.tmt.project.webnghenhac.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "playlist")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false,length = 500)
    private String title;

    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @Column(name = "is_valid")
    private Boolean isValid;

    @Column(name = "status")
    private Boolean status;

    @OneToOne
    @JoinColumn(name = "picture_id")
    private  Picture picture;

    @OneToMany(mappedBy = "playlist",cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"playlist","single"}, allowSetters = true)
    private List<Song> songs = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties(value = {"playlists","albums","category","singles"},allowSetters = true)
    private Category category;

}
