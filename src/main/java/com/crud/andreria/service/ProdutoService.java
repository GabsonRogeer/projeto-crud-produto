package com.crud.andreria.service;

import com.crud.andreria.controller.dto.ProdutoDTO;
import com.crud.andreria.exceptions.ProductNotFoundException;
import com.crud.andreria.exceptions.ProductNullException;
import com.crud.andreria.exceptions.ProductPriceException;
import com.crud.andreria.model.Produto;
import com.crud.andreria.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }

    public Produto findById(Long id){
        return produtoRepository.findById(id).orElseThrow( () ->
                new ProductNotFoundException(id));
    }

    public Produto create(Produto produtoCreate) {
        if(produtoCreate.getNome() == null || produtoCreate.getDescricao() == null)
            throw new ProductNullException();
        if(produtoCreate.getPreco() == null || produtoCreate.getPreco() < 0)
            throw new ProductPriceException();
        produtoRepository.save(produtoCreate);
        return produtoCreate;
    }

    public void delete(Long id){
        findById(id);
        produtoRepository.deleteById(id);
    }

    public Produto update(Long id, Produto produtoCreate){
        Produto produto = findById(id);
        produto.setNome(produtoCreate.getNome());
        if(produtoCreate.getNome() == null || produtoCreate.getDescricao() == null)
            throw new ProductNullException();
        produto.setPreco(produtoCreate.getPreco());
        if(produtoCreate.getPreco() == null || produtoCreate.getPreco() < 0)
            throw new ProductPriceException();
        produto.setDescricao(produtoCreate.getDescricao());
        return produtoRepository.save(produtoCreate);
    }

}
