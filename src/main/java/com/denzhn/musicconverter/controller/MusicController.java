package com.denzhn.musicconverter.controller;

import com.denzhn.musicconverter.dto.ConvertResponseDto;
import com.denzhn.musicconverter.dto.FileDto;
import com.denzhn.musicconverter.helper.ValidationHelper;
import com.denzhn.musicconverter.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MusicController {

    private final MusicService musicService;

    @Autowired
    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @GetMapping
    public String getHomepage(Model model){
        model.addAttribute("fileDto", new FileDto());
        return "home";
    }

    @PostMapping("/convert")
    public ResponseEntity<byte[]> convert(@ModelAttribute FileDto fileDto){
        ValidationHelper.validate(fileDto);
        ConvertResponseDto convertResponse = musicService.convert(fileDto);
        MultiValueMap<String, String> httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, convertResponse.getMimeType());
        return new ResponseEntity<>(convertResponse.getData(), httpHeaders, HttpStatus.OK);
    }
}
