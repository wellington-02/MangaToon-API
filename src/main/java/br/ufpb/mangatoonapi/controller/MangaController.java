package br.ufpb.mangatoonapi.controller;

import br.ufpb.mangatoonapi.dto.manga.MangaDTO;
import br.ufpb.mangatoonapi.service.MangaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/manga")
@Validated
public class MangaController {

    private final MangaService mangaService;

    public MangaController(MangaService mangaService) {
        this.mangaService = mangaService;
    }

    @GetMapping()
    List<MangaDTO> listMangas() {
        return mangaService.listMangas();
    }

    @GetMapping("/{mangaId}")
    public MangaDTO getManga(@PathVariable Long mangaId) {
        return mangaService.getManga(mangaId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    MangaDTO createManga(@Valid @RequestBody MangaDTO mangaDTO) {
        return mangaService.createManga(mangaDTO);
    }

    @PatchMapping("/{mangaId}")
    public MangaDTO updateManga(@PathVariable Long mangaId, @Valid @RequestBody MangaDTO mangaDTO) {
        return mangaService.updateManga(mangaId, mangaDTO);
    }

    @DeleteMapping("/{mangaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteManga(@PathVariable Long mangaId) {
        mangaService.deleteManga(mangaId);
    }
}
