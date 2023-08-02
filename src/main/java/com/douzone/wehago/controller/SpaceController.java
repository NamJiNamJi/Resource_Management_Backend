package com.douzone.wehago.controller;

import com.douzone.wehago.common.Response;
import com.douzone.wehago.domain.Space;
import com.douzone.wehago.dto.CarDTO;
import com.douzone.wehago.dto.SpaceDTO;
import com.douzone.wehago.dto.SpacePageResponseDTO;
import com.douzone.wehago.dto.SpaceResponseDTO;
import com.douzone.wehago.service.SpaceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
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

        return new ResponseEntity<>(spacePageResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/{spcSeq}")
    public ResponseEntity<Object> findOneSpace(@PathVariable Integer spcSeq) {
        SpaceResponseDTO spaceResponseDTO = spaceService.findOneSpace(spcSeq);

        return new ResponseEntity<>(spaceResponseDTO, HttpStatus.OK);
    }

    @PutMapping("/{spcSeq}")
    public ResponseEntity<Object> updateSpace (@RequestBody SpaceDTO spaceDTO,
                                               @PathVariable Integer spcSeq) {
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
