package com.tmt.project.webnghenhac.controller;

import com.tmt.project.webnghenhac.domain.Browse;
import com.tmt.project.webnghenhac.service.BrowseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/public/browses")
public class BrowseController {
    private final BrowseService browseService;

    public BrowseController(BrowseService browseService) {
        this.browseService = browseService;
    }

    @GetMapping("")
    public ResponseEntity<List<Browse>> getAllBrowse(){
        List<Browse> browses = this.browseService.getAllBrowse();
        return ResponseEntity.ok(browses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Browse> getBrowseById(@PathVariable("id") Integer id){
        Browse browse = this.browseService.getBrowseById(id);
        return ResponseEntity.ok(browse);
    }

    @PostMapping("/create")
    public ResponseEntity<Browse> createNewBrowse(@RequestBody Browse browse){
        var newBrowse = this.browseService.createNewBrowse(browse);
        return ResponseEntity.ok(newBrowse);
    }
}
