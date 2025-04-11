package br.com.fiap.hospitalAPI.controller;

import br.com.fiap.hospitalAPI.dto.PacienteRequestDTO;
import br.com.fiap.hospitalAPI.dto.PacienteResponseDTO;
import br.com.fiap.hospitalAPI.service.PacienteService;
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
@RequestMapping(value = "/pacientes")
@Tag(name = "paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Operation(summary = "Cria um novo paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Paciente criado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PacienteResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Parâmetros informados são inválidos",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<PacienteResponseDTO> criarPaciente(@Valid @RequestBody PacienteRequestDTO pacienteRequest) {
        PacienteResponseDTO paciente = pacienteService.criar(pacienteRequest);
        return new ResponseEntity<>(paciente, HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todos os pacientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pacientes retornada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PacienteResponseDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> listarPacientes() {
        List<PacienteResponseDTO> pacientes = pacienteService.listarTodos();
        return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }

    @Operation(summary = "Retorna um paciente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente encontrado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PacienteResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum paciente encontrado para o ID fornecido",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> buscarPacientePorId(@PathVariable Long id) {
        PacienteResponseDTO paciente = pacienteService.buscarPorId(id);
        if (paciente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(paciente, HttpStatus.OK);
    }

    @Operation(summary = "Atualiza um paciente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente atualizado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PacienteResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Parâmetros informados são inválidos",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Nenhum paciente encontrado para o ID fornecido",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> atualizarPaciente(@PathVariable Long id,
                                                                 @Valid @RequestBody PacienteRequestDTO pacienteRequest) {
        PacienteResponseDTO paciente = pacienteService.atualizar(id, pacienteRequest);
        if (paciente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(paciente, HttpStatus.OK);
    }

    @Operation(summary = "Exclui um paciente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Paciente excluído com sucesso",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Nenhum paciente encontrado para o ID fornecido",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPaciente(@PathVariable Long id) {
        boolean deletado = pacienteService.deletar(id);
        if (!deletado) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}