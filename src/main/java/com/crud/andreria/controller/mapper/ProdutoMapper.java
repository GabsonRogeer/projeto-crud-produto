package com.crud.andreria.controller.mapper;

import com.crud.andreria.controller.dto.ProdutoCreateDTO;
import com.crud.andreria.controller.dto.ProdutoDTO;
import com.crud.andreria.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ProdutoDTO produtoDTO(Produto produto){
        return MODEL_MAPPER.map(produto, ProdutoDTO.class);
    }

    public List<ProdutoDTO> produtoDTOList(List<Produto> produtoList){
        return produtoList.stream().map(this::produtoDTO).collect(Collectors.toList());
    }

    public Produto toProduto(ProdutoDTO dto) {
        return MODEL_MAPPER.map(dto, Produto.class);
    }

    public Produto toProdutoCreate(ProdutoCreateDTO dto) {
        return MODEL_MAPPER.map(dto, Produto.class);
    }

}
