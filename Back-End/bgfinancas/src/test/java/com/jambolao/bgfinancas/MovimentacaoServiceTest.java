package com.jambolao.bgfinancas;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.jambolao.bgfinancas.model.categoria.Categoria;
import com.jambolao.bgfinancas.model.movimentacao.Movimentacao;
import com.jambolao.bgfinancas.model.movimentacao.MovimentacaoRepository;
import com.jambolao.bgfinancas.service.MovimentacaoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MovimentacaoServiceTest {

    @Mock
    private MovimentacaoRepository movimentacaoRepository;

    @InjectMocks
    private MovimentacaoService movimentacaoService;

    private Movimentacao movimentacao1;
    private Movimentacao movimentacao2;

    private Categoria categoria1 = new Categoria((long) 8, "categoria1");
    private Categoria categoria2 = new Categoria((long) 9, "categoria2");

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        movimentacao1 = new Movimentacao((long) 8, "Deposito 1", (float) 17.99, "Entrada", categoria1, LocalDate.now(), null);
        movimentacao2 = new Movimentacao((long) 9, "Retirada 1", (float) 17.99, "Saida", categoria2,LocalDate.now(), null);
    }

    @Test
    void shouldCreateMovimentacao() {
        when(movimentacaoRepository.save(any(Movimentacao.class))).thenReturn(movimentacao1);

        Movimentacao createdMovimentacao = movimentacaoService.createMovimentacao(movimentacao1);

        assertNotNull(createdMovimentacao);
        assertEquals(movimentacao1.getId(), createdMovimentacao.getId());
        verify(movimentacaoRepository, times(1)).save(movimentacao1);
    }

    @Test
    void shouldReturnListOfMovimentacoes() {
        List<Movimentacao> movimentacoes = Arrays.asList(movimentacao1, movimentacao2);
        when(movimentacaoRepository.findAll()).thenReturn(movimentacoes);

        List<Movimentacao> result = movimentacaoService.listMovimentacoes();

        assertEquals(2, result.size());
        verify(movimentacaoRepository, times(1)).findAll();
    }

    @Test
    void shouldUpdateMovimentacao() {
        when(movimentacaoRepository.save(any(Movimentacao.class))).thenReturn(movimentacao1);

        Movimentacao updatedMovimentacao = movimentacaoService.updateMovimentacao(movimentacao1.getId(), movimentacao1);

        assertEquals(movimentacao1.getId(), updatedMovimentacao.getId());
        verify(movimentacaoRepository, times(1)).save(movimentacao1);
    }

    @Test
    void shouldDeleteMovimentacaoWhenExists() {
        when(movimentacaoRepository.findById(movimentacao1.getId())).thenReturn(Optional.of(movimentacao1));

        boolean isDeleted = movimentacaoService.deleteMovimentacao(movimentacao1.getId());

        assertTrue(isDeleted);
        verify(movimentacaoRepository, times(1)).deleteById(movimentacao1.getId());
    }

    @Test
    void shouldNotDeleteMovimentacaoWhenNotExists() {
        when(movimentacaoRepository.findById(movimentacao1.getId())).thenReturn(Optional.empty());

        boolean isDeleted = movimentacaoService.deleteMovimentacao(movimentacao1.getId());

        assertFalse(isDeleted);
        verify(movimentacaoRepository, never()).deleteById(anyLong());
    }

    @Test
    void shouldReadMovimentacaoWhenExists() {
        when(movimentacaoRepository.existsById(movimentacao1.getId())).thenReturn(true);
        when(movimentacaoRepository.findById(movimentacao1.getId())).thenReturn(Optional.of(movimentacao1));

        Movimentacao result = movimentacaoService.readMovimentacao(movimentacao1.getId());

        assertNotNull(result);
        assertEquals(movimentacao1.getId(), result.getId());
        verify(movimentacaoRepository, times(1)).existsById(movimentacao1.getId());
        verify(movimentacaoRepository, times(1)).findById(movimentacao1.getId());
    }

    @Test
    void shouldReturnNullWhenMovimentacaoNotExists() {
        when(movimentacaoRepository.existsById(movimentacao1.getId())).thenReturn(false);

        Movimentacao result = movimentacaoService.readMovimentacao(movimentacao1.getId());

        assertNull(result);
        verify(movimentacaoRepository, times(1)).existsById(movimentacao1.getId());
    }
}
