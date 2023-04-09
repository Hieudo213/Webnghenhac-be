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
@Table(name = "song")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "length_of_song", nullable = false)
    private String length;

    @Column(name = "is_valid")
    private Boolean isValid;

    @Column(name = "status")
    private Boolean status;

    @OneToOne
    @JoinColumn(name = "music_id")
    private Music music;

    @ManyToMany(mappedBy = "songs")
    @JsonIgnoreProperties(value = {"songs","albums","status","isValid","description","dateOfBirth","placeOfBirth","picture"},allowSetters = true)
    private List<Artist> artists = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    @JsonIgnoreProperties(value = {"songs","artists","status","isValid","category","publisher","length"},allowSetters = true)
    private Album album;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id")
    @JsonIgnoreProperties(value = {"songs","category","playlist"},allowSetters = true)
    private Playlist playlist;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "single_id")
    @JsonIgnoreProperties(value = {"songs","category","isValid","status","publisher","length"},allowSetters = true)
    private Single single;


}
