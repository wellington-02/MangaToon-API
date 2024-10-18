package br.ufpb.mangatoonapi.controller;

import br.ufpb.mangatoonapi.dto.chapter.ChapterDTO;
import br.ufpb.mangatoonapi.service.ChapterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/chapter")
@Validated
public class ChapterController {

    private final ChapterService chapterService;

    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @GetMapping()
    List<ChapterDTO> listChapters() {
        return chapterService.listChapters();
    }

    @GetMapping("/{chapterId}")
    public ChapterDTO getChapter(@PathVariable Long chapterId) {
        return chapterService.getChapter(chapterId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    ChapterDTO createChapter(@Valid @RequestBody ChapterDTO chapterDTO) {
        return chapterService.createChapter(chapterDTO);
    }

    @PutMapping("/{chapterId}")
    public ChapterDTO updateChapter(@PathVariable Long chapterId, @RequestBody ChapterDTO chapterDTO) {
        return chapterService.updateChapter(chapterId, chapterDTO);
    }

    @DeleteMapping("/{chapterId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteChapter(@PathVariable Long chapterId) {
        chapterService.deleteChapter(chapterId);
    }
}
