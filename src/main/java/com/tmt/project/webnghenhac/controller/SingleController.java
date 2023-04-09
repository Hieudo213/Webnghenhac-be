package com.tmt.project.webnghenhac.controller;

import com.tmt.project.webnghenhac.domain.Single;
import com.tmt.project.webnghenhac.service.SingleService;
import com.tmt.project.webnghenhac.service.request.CreateSingleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/public/singles")
public class SingleController {
    private final SingleService singleService;

    public SingleController(SingleService singleService) {
        this.singleService = singleService;
    }

    @GetMapping("")
    public ResponseEntity<List<Single>> getAllActivatedSingle(){
        var singles = this.singleService.getAllActivatedSingles();
        return ResponseEntity.ok(singles);
    }

    @GetMapping("admin/all")
    public ResponseEntity<List<Single>> getAllSingle(){
        var singles = this.singleService.getAllSingles();
        return ResponseEntity.ok(singles);
    }

    @GetMapping("admin/in-process/all")
    public ResponseEntity<List<Single>> getAllInProcessSingle(){
        var singles = this.singleService.getAllInProcessSingles();
        return ResponseEntity.ok(singles);
    }

    @GetMapping("admin/done/all")
    public ResponseEntity<List<Single>> getAllDoneSingle(){
        var singles = this.singleService.getAllDoneSingles();
        return ResponseEntity.ok(singles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Single> getSingleById(@PathVariable("id") Integer id){
        var single = this.singleService.getSingleById(id);
        return ResponseEntity.ok(single);
    }

    @PostMapping("/create")
    public ResponseEntity<Single> createNewSingle(@RequestBody CreateSingleResponse singleResponse){
        var newSingle = this.singleService.createNewSingle(singleResponse);
        return ResponseEntity.ok(newSingle);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Single> updateSingle(@PathVariable("id") Integer id,
                                               @RequestBody CreateSingleResponse singleResponse){
        var updateSingle = this.singleService.updateSingleById(id, singleResponse);
        return ResponseEntity.ok(updateSingle);
    }


    @PutMapping("update/picture/{id}")
    public ResponseEntity<Single> updatePictureForSingleById(@RequestParam("newfile") MultipartFile file,
                                                             @PathVariable("id") Integer id) throws IOException {
     var single = this.singleService.updatePictureForSingleById(id, file);
     return ResponseEntity.ok(single);
    }

    @PutMapping("admin/activate/{id}")
    public ResponseEntity<Single> activateSingleById(@PathVariable("id") Integer id){
        var single = this.singleService.activateSingleById(id);
        return ResponseEntity.ok(single);
    }
}
