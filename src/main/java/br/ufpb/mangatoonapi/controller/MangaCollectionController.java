package br.ufpb.mangatoonapi.controller;

import br.ufpb.mangatoonapi.dto.mangaCollection.MangaCollectionDTO;
import br.ufpb.mangatoonapi.service.MangaCollectionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/mangaCollection")
@Validated
public class MangaCollectionController {

    private final MangaCollectionService mangaCollectionService;

    public MangaCollectionController(MangaCollectionService mangaCollectionService) {
        this.mangaCollectionService = mangaCollectionService;
    }

    @GetMapping()
    List<MangaCollectionDTO> listMangaCollections() {
        return mangaCollectionService.listMangaCollections();
    }

    @GetMapping("/{mangaCollectionId}")
    public MangaCollectionDTO getMangaCollection(@PathVariable Long mangaCollectionId) {
        return mangaCollectionService.getMangaCollection(mangaCollectionId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    MangaCollectionDTO createMangaCollection(@Valid @RequestBody MangaCollectionDTO mangaCollectionDTO) {
        return mangaCollectionService.createMangaCollection(mangaCollectionDTO);
    }

    @PatchMapping("/{mangaCollectionId}")
    public MangaCollectionDTO updateMangaCollection(@PathVariable Long mangaCollectionId, @Valid @RequestBody MangaCollectionDTO mangaCollectionDTO) {
        return mangaCollectionService.updateMangaCollection(mangaCollectionId, mangaCollectionDTO);
    }

    @DeleteMapping("/{mangaCollectionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMangaCollection(@PathVariable Long mangaCollectionId) {
        mangaCollectionService.deleteMangaCollection(mangaCollectionId);
    }
}
