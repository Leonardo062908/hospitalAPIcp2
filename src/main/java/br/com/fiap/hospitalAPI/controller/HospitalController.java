package br.com.fiap.hospitalAPI.controller;

import br.com.fiap.hospitalAPI.dto.HospitalRequestDTO;
import br.com.fiap.hospitalAPI.dto.HospitalResponseDTO;
import br.com.fiap.hospitalAPI.service.HospitalService;
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
@RequestMapping(value = "/hospitais")
@Tag(name = "hospital")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @Operation(summary = "Cria um novo hospital")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Hospital criado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = HospitalResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Parâmetros informados são inválidos",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<HospitalResponseDTO> criarHospital(@Valid @RequestBody HospitalRequestDTO hospitalRequest) {
        HospitalResponseDTO hospital = hospitalService.criar(hospitalRequest);
        return new ResponseEntity<>(hospital, HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todos os hospitais")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de hospitais retornada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = HospitalResponseDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<HospitalResponseDTO>> listarHospitais() {
        List<HospitalResponseDTO> hospitais = hospitalService.listarTodos();
        return new ResponseEntity<>(hospitais, HttpStatus.OK);
    }

    @Operation(summary = "Retorna um hospital por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hospital encontrado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = HospitalResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum hospital encontrado para o ID fornecido",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponseDTO> buscarHospitalPorId(@PathVariable Long id) {
        HospitalResponseDTO hospital = hospitalService.buscarPorId(id);
        if (hospital == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(hospital, HttpStatus.OK);
    }

    @Operation(summary = "Atualiza um hospital existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hospital atualizado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = HospitalResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Parâmetros informados são inválidos",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Nenhum hospital encontrado para o ID fornecido",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping("/{id}")
    public ResponseEntity<HospitalResponseDTO> atualizarHospital(@PathVariable Long id,
                                                                 @Valid @RequestBody HospitalRequestDTO hospitalRequest) {
        HospitalResponseDTO hospital = hospitalService.atualizar(id, hospitalRequest);
        if (hospital == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(hospital, HttpStatus.OK);
    }

    @Operation(summary = "Exclui um hospital por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Hospital excluído com sucesso",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Nenhum hospital encontrado para o ID fornecido",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarHospital(@PathVariable Long id) {
        boolean deletado = hospitalService.deletar(id);
        if (!deletado) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}