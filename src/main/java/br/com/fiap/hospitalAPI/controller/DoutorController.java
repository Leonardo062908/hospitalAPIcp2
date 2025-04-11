package br.com.fiap.hospitalAPI.controller;

import br.com.fiap.hospitalAPI.dto.DoutorRequestDTO;
import br.com.fiap.hospitalAPI.dto.DoutorResponseDTO;
import br.com.fiap.hospitalAPI.service.DoutorService;
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
@RequestMapping(value = "/doutores")
@Tag(name = "doutor")
public class DoutorController {

    @Autowired
    private DoutorService doutorService;

    @Operation(summary = "Cria um novo doutor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Doutor criado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DoutorResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Parâmetros informados são inválidos",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<DoutorResponseDTO> criarDoutor(@Valid @RequestBody DoutorRequestDTO doutorRequest) {
        try {
            DoutorResponseDTO doutor = doutorService.criar(doutorRequest);
            return new ResponseEntity<>(doutor, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Lista todos os doutores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de doutores retornada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DoutorResponseDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<DoutorResponseDTO>> listarDoutores() {
        List<DoutorResponseDTO> doutores = doutorService.listarTodos();
        return new ResponseEntity<>(doutores, HttpStatus.OK);
    }

    @Operation(summary = "Retorna um doutor por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doutor encontrado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DoutorResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum doutor encontrado para o ID fornecido",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public ResponseEntity<DoutorResponseDTO> buscarDoutorPorId(@PathVariable Long id) {
        DoutorResponseDTO doutor = doutorService.buscarPorId(id);
        if (doutor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(doutor, HttpStatus.OK);
    }

    @Operation(summary = "Atualiza um doutor existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doutor atualizado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DoutorResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Parâmetros informados são inválidos",
                            content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Nenhum doutor encontrado para o ID fornecido",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping("/{id}")
    public ResponseEntity<DoutorResponseDTO> atualizarDoutor(@PathVariable Long id,
                                                             @Valid @RequestBody DoutorRequestDTO doutorRequest) {
        try {
            DoutorResponseDTO doutor = doutorService.atualizar(id, doutorRequest);
            if (doutor == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(doutor, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Exclui um doutor por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Doutor excluído com sucesso",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Nenhum doutor encontrado para o ID fornecido",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDoutor(@PathVariable Long id) {
        boolean deletado = doutorService.deletar(id);
        if (!deletado) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}