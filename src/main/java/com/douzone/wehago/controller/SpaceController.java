package com.douzone.wehago.controller;

import com.douzone.wehago.common.Response;
import com.douzone.wehago.dto.space.SpaceDTO;
import com.douzone.wehago.dto.space.SpacePageResponseDTO;
import com.douzone.wehago.dto.space.SpaceResponseDTO;
import com.douzone.wehago.service.SpaceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/space")
public class SpaceController {

    private final SpaceService spaceService;

    @PostMapping
    public ResponseEntity<Object> saceSapce(@RequestPart(value = "data") SpaceDTO spaceDTO,
                                            @RequestPart(value = "image", required = false) MultipartFile image)
        throws IOException {

        SpaceResponseDTO spaceResponseDTO = spaceService.saveSpace(spaceDTO, image);
        Response response = new Response(HttpStatus.CREATED, "등록 성공", spaceResponseDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> SpacefindAll() {

        SpacePageResponseDTO spacePageResponseDTO =  spaceService.findAllSpace();
        Response response = new Response(HttpStatus.OK, "공간자원 전체 조회 성공", spacePageResponseDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{spcSeq}")
    public ResponseEntity<Object> findOneSpace(@PathVariable Integer spcSeq) {
        SpaceResponseDTO spaceResponseDTO = spaceService.findOneSpace(spcSeq);

        return new ResponseEntity<>(spaceResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchSpace (@RequestParam(value = "columnName") String columnName,
                                               @RequestParam(value = "searchString") String searchString) {
        log.info(columnName + searchString);
        SpacePageResponseDTO spacePageResponseDTO = spaceService.searchSpace(columnName, searchString);
        Response response = new Response(HttpStatus.OK, "공간 검색 성공", spacePageResponseDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping ("/{spcSeq}")
    public ResponseEntity<Object> updateSpace (@RequestPart(value = "data") SpaceDTO spaceDTO,
                                               @RequestPart(value = "image", required = false) MultipartFile image,
                                               @PathVariable Integer spcSeq) throws IOException {
        SpaceResponseDTO spaceResponseDTO = spaceService.updateSpace(spaceDTO, spcSeq);

        return new ResponseEntity<>(spaceResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{spcSeq}")
    public ResponseEntity<Object> deleteSpace(@PathVariable Integer spcSeq) {
        spaceService.deleteSpace(spcSeq);
        String message = "삭제 성공";

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
