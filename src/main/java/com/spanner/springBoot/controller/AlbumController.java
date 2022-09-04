package com.spanner.springBoot.controller;

import com.spanner.springBoot.dto.AlbumsDto;
import com.spanner.springBoot.dto.SingersDto;
import com.spanner.springBoot.model.spanner.Albums;
import com.spanner.springBoot.service.AlbumService;
import com.spanner.springBoot.service.SingerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@Slf4j
@RequiredArgsConstructor
public class AlbumController {
    @Autowired
    AlbumService albumService;

    @PostMapping(path = "/albums",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AlbumsDto>> getAlbumsBysingers(@RequestBody Map<String, List<String>> body) {
        List<String> singers = body.get("singers");
        log.info("Getting list of singers", body.toString());
        return ResponseEntity.ok(albumService.getAlbumsBySingerId(singers));
    }
}
