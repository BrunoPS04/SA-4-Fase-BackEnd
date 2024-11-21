package com.jambolao.bgfinancas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jambolao.bgfinancas.model.categoria.Categoria;
import com.jambolao.bgfinancas.model.categoria.CategoriaRepository;
import com.jambolao.bgfinancas.model.movimentacao.Movimentacao;
import com.jambolao.bgfinancas.model.movimentacao.MovimentacaoRepository;
// import com.jambolao.bgfinancas.model.user.User;
import com.jambolao.bgfinancas.model.movimentacao.MovimentacaoRequestDTO;
import com.jambolao.bgfinancas.model.user.User;
import com.jambolao.bgfinancas.model.user.UserRepository;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Movimentacao createMovimentacao(MovimentacaoRequestDTO movimentacao) {

        Movimentacao movimentacaoToSave = new Movimentacao();
        movimentacaoToSave.setDescricao(movimentacao.descricao());
        movimentacaoToSave.setValor(movimentacao.valor());
        movimentacaoToSave.setTipo(movimentacao.tipo());
        movimentacaoToSave.setData(movimentacao.data());

        Optional<Categoria> categoriaOptional = categoriaRepository.findByNomeCategoria(movimentacao.categoria());
        Categoria categoria = new Categoria();
        if (categoriaOptional.isPresent()) {
            categoria.setId(categoriaOptional.get().getId());
            categoria.setNomeCategoria(categoriaOptional.get().getNomeCategoria());
        }

        movimentacaoToSave.setCategoria(categoria);

        User usuario = new User();

        Optional<User> usuarioOptional = userRepository.findById(movimentacao.user_id());
        if (usuarioOptional.isPresent()) {

            usuario.setId(usuarioOptional.get().getId());
            usuario.setNome(usuarioOptional.get().getNome());
            usuario.setEmail(usuarioOptional.get().getEmail());
            usuario.setSenha(usuarioOptional.get().getSenha());
            usuario.setUltimoNome(usuarioOptional.get().getUltimoNome());

        }

        movimentacaoToSave.setUserId(usuario);

        return repository.save(movimentacaoToSave);
    }

    @Transactional
    public List<Movimentacao> listMovimentacoes() {
        return (List<Movimentacao>) repository.findAll();
    }

    @Transactional
    public List<Movimentacao> listByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    @Transactional
    public Movimentacao updateMovimentacao(Long id, Movimentacao movimentacao) {
        movimentacao.setId(id);
        return repository.save(movimentacao);
    }

    @Transactional
    public boolean deleteMovimentacao(Long id) {
        Optional<Movimentacao> existingMovimentacao = repository.findById(id);
        if (existingMovimentacao.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Movimentacao readMovimentacao(Long id) {
        if (repository.existsById(id)) {
            return repository.findById(id).get();
        }
        return null;
    }

    @Transactional
    public Optional<Categoria> findCategoriaByName(String nomeCategoria) {
        return categoriaRepository.findByNomeCategoria(nomeCategoria);
    }

    @Transactional
    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }

}
