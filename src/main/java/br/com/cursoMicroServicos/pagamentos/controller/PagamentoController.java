package br.com.cursoMicroServicos.pagamentos.controller;

import br.com.cursoMicroServicos.pagamentos.dto.PagamentoDto;
import br.com.cursoMicroServicos.pagamentos.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService service;

    @GetMapping
    public List<PagamentoDto> listar(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDto> buscarPorId(@PathVariable @NotNull Long id){
        PagamentoDto dto = service.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PagamentoDto> cadastrar(@RequestBody @Valid PagamentoDto dto,
                                                  UriComponentsBuilder uriBuilder){

        PagamentoDto pagamento = service.createPagamento(dto);
        var uri = uriBuilder.path("/pagamentos/{id}").buildAndExpand(pagamento.getId()).toUri();

        return ResponseEntity.created(uri).body(pagamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDto> atualizar(@PathVariable @NotNull Long id,
                                                  @RequestBody @Valid PagamentoDto dto){

        PagamentoDto pagamentoAtualizado = service.updatePagamento(id, dto);

        return ResponseEntity.ok(pagamentoAtualizado);
    }
}
