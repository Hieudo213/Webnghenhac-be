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
@Table(name = "single")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Single {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "description",nullable = false, length = 1000)
    private String description;

    @Column(name = "publisher", length = 100)
    private String publisher;

    @Column(name = "genre",length = 10)
    private String genre;

    @Column(name = "release_year",nullable = false,length = 4)
    private Integer releaseYear;

    @Column(name = "length_of_single", nullable = false,length = 4)
    private String length;

    @Column(name = "is_valid")
    private Boolean isValid;

    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "single",cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"single","playlist"},allowSetters = true)
    private List<Song> songs = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "picture_id")
    private Picture picture;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties(value = {"singles","albums","playlists","browses"},allowSetters = true)
    private Category category;

}
