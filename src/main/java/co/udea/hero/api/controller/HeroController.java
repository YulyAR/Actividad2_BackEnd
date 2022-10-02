package co.udea.hero.api.controller;

import co.udea.hero.api.model.Hero;
import co.udea.hero.api.service.HeroService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    private final Logger log = LoggerFactory.getLogger(HeroController.class);

    private HeroService heroService;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Busca un hero por su id",  response = Hero.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Hero encontrado existosamente"),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<Hero> getHero(@PathVariable Integer id){
        log.info("Rest request buscar heroe por id: "+ id);
        return ResponseEntity.ok(heroService.getHero(id));
    }

    @GetMapping("")
    @ApiOperation(value = "Buscar todos los heroes",  response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroes encontrado existosamente"),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<List<Hero>> getHeroes(){
        log.info("Rest request buscar heroes");
        return ResponseEntity.ok(heroService.getHeroes());
    }
    @PostMapping ("/crear")
    @ApiOperation(value = "Crea un heroe")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Hero encontrado exitosamente"),
            @ApiResponse(code= 400, message = "La peticion es inválida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public void addHero(@RequestBody Hero hero){
        heroService.createHero(hero);
    }

    @PutMapping ("/actualizar")
    @ApiOperation(value = "Actualiza un heroe")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Hero encontrado exitosamente"),
            @ApiResponse(code= 400, message = "La peticion es inválida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public void updateHero(@RequestBody Hero hero){
        heroService.updateHero(hero);
    }

    @DeleteMapping("/borrar")
    @ApiOperation(value = "Elimina un heroe")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Hero encontrado exitosamente"),
            @ApiResponse(code= 400, message = "La peticion es inválida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public void deleteHero(@RequestBody Hero hero){
        heroService.deleteHero(hero);
    }
    @GetMapping("/buscar/{name}")
    @ApiOperation(value = "Busca un heroe por el nombre")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Hero encontrado exitosamente"),
            @ApiResponse(code= 400, message = "La peticion es inválida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")
    })
    public ResponseEntity<List<Hero>> searchHeroes(@ApiParam("Nombre del heroe que desea buscar")
                                                   @PathVariable("name") String name) {
        return ResponseEntity.ok(heroService.searchHeroes(name));
    }


}
