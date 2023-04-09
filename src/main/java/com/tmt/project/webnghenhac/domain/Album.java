package com.tmt.project.webnghenhac.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "album")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description",length = 1000)
    private String description;

    @Column(name = "release_year",length = 4,nullable = false)
    private Integer releaseYear;

    @Column(name = "publisher", length = 100)
    private String publisher;

    @Column(name = "genre",length = 10)
    private String genre;

    @Column(name = "length_of_album", length = 5)
    private String length;

    @Column(name = "is_valid")
    private Boolean isValid;

    @Column(name = "status")
    private Boolean status;

    @OneToOne
    @JoinColumn(name = "picture_id")
    private Picture picture;

    @ManyToMany(mappedBy = "albums")
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"albums","songs","playlist","description","dateOfBirth","isValid","isStatus","songs"},allowSetters = true)
    private List<Artist> artists = new ArrayList<>();

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"playlist","isValid","status"},allowSetters = true)
    private List<Song> songs = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties(value = {"playlists","albums","singles"}, allowSetters = true)
    private Category category;
}
