package br.com.fiap.hospitalAPI.controller;

import br.com.fiap.hospitalAPI.dto.EspecialidadeRequestDTO;
import br.com.fiap.hospitalAPI.dto.EspecialidadeResponseDTO;
import br.com.fiap.hospitalAPI.service.EspecialidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/especialidades")
@Tag(name = "especialidade")
public class EspecialidadeController {

    private final EspecialidadeService especialidadeService;

    @Autowired
    public EspecialidadeController (EspecialidadeService especialidadeService) {
        this.especialidadeService = especialidadeService;
    }

    @Operation(summary = "Cria uma nova especialidade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Especialidade criada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EspecialidadeResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Parâmetros informados são inválidos",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<EspecialidadeResponseDTO> criarEspecialidade(@Valid @RequestBody EspecialidadeRequestDTO especialidadeRequest) {
        EspecialidadeResponseDTO especialidade = especialidadeService.criar(especialidadeRequest);
        return new ResponseEntity<>(especialidade, HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todas as especialidades")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de especialidades retornada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EspecialidadeResponseDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<EspecialidadeResponseDTO>> listarEspecialidades() {
        List<EspecialidadeResponseDTO> especialidades = especialidadeService.listarTodos();
        return new ResponseEntity<>(especialidades, HttpStatus.OK);
    }

    @Operation(summary = "Retorna uma especialidade por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Especialidade encontrada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EspecialidadeResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Nenhuma especialidade encontrada para o ID fornecido",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadeResponseDTO> buscarEspecialidadePorId(@PathVariable Long id) {
        EspecialidadeResponseDTO especialidade = especialidadeService.buscarPorId(id);
        if (especialidade == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(especialidade, HttpStatus.OK);
    }

    @Operation(summary = "Atualiza uma especialidade existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Especialidade atualizada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EspecialidadeResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Parâmetros informados são inválidos",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Nenhuma especialidade encontrada para o ID fornecido",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping("/{id}")
    public ResponseEntity<EspecialidadeResponseDTO> atualizarEspecialidade(@PathVariable Long id,
                                                                           @Valid @RequestBody EspecialidadeRequestDTO especialidadeRequest) {
        EspecialidadeResponseDTO especialidade = especialidadeService.atualizar(id, especialidadeRequest);
        if (especialidade == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(especialidade, HttpStatus.OK);
    }

    @Operation(summary = "Exclui uma especialidade por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Especialidade excluída com sucesso",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Nenhuma especialidade encontrada para o ID fornecido",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEspecialidade(@PathVariable Long id) {
        boolean deletado = especialidadeService.deletar(id);
        if (!deletado) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}