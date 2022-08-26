package com.crud.andreria.controller;


import com.crud.andreria.controller.dto.ProdutoCreateDTO;
import com.crud.andreria.controller.dto.ProdutoDTO;
import com.crud.andreria.controller.mapper.ProdutoMapper;
import com.crud.andreria.model.Produto;
import com.crud.andreria.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@Api(tags = "Produto Controller")
public class ProdutoController {

    @Autowired
    private final ProdutoService produtoService;

    private final ProdutoMapper produtoMapper;

    public ProdutoController(ProdutoService produtoService, ProdutoMapper produtoMapper) {
        this.produtoService = produtoService;
        this.produtoMapper = produtoMapper;
    }

    @GetMapping
    @ApiOperation("Find all products")
    public ResponseEntity<List<ProdutoDTO>> findAll(){
        List<Produto> produtosList = produtoService.findAll();
        List<ProdutoDTO> result = produtoMapper.produtoDTOList(produtosList);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id){
        Produto produto = produtoService.findById(id);
        ProdutoDTO result = produtoMapper.produtoDTO(produto);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> create(@RequestBody ProdutoCreateDTO dto){
        var produtoCreate = produtoMapper.toProdutoCreate(dto);
        var produto = produtoService.create(produtoCreate);
        var result = produtoMapper.produtoDTO(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @RequestBody ProdutoDTO dto){
        var parkingCreate = produtoMapper.toProduto(dto);
        var parking = produtoService.update(id, parkingCreate);
        var result = produtoMapper.produtoDTO(parking);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


}
