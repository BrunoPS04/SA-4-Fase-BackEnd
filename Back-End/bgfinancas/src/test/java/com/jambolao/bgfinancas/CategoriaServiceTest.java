package com.jambolao.bgfinancas;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.jambolao.bgfinancas.model.categoria.Categoria;
import com.jambolao.bgfinancas.model.categoria.CategoriaRepository;
import com.jambolao.bgfinancas.service.CategoriaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    private Categoria categoria1;
    private Categoria categoria2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoria1 = new Categoria((long) 5, "Alimentação");
        categoria2 = new Categoria((long) 6, "Transporte");
    }

    @Test
    void shouldCreateCategoria() {
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria1);

        Categoria createdCategoria = categoriaService.createCategoria(categoria1);

        assertNotNull(createdCategoria);
        assertEquals(categoria1.getId(), createdCategoria.getId());
        verify(categoriaRepository, times(1)).save(categoria1);
    }

    @Test
    void shouldReturnListOfCategorias() {
        List<Categoria> categorias = Arrays.asList(categoria1, categoria2);
        when(categoriaRepository.findAll()).thenReturn(categorias);

        List<Categoria> result = categoriaService.listCategorias();

        assertEquals(2, result.size());
        verify(categoriaRepository, times(1)).findAll();
    }

    @Test
    void shouldUpdateCategoria() {
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria1);

        Categoria updatedCategoria = categoriaService.updateCategoria(categoria1.getId(), categoria1);

        assertEquals(categoria1.getId(), updatedCategoria.getId());
        verify(categoriaRepository, times(1)).save(categoria1);
    }

    @Test
    void shouldDeleteCategoriaWhenExists() {
        when(categoriaRepository.findById(categoria1.getId())).thenReturn(Optional.of(categoria1));

        boolean isDeleted = categoriaService.deleteCategoria(categoria1.getId());

        assertTrue(isDeleted);
        verify(categoriaRepository, times(1)).deleteById(categoria1.getId());
    }

    @Test
    void shouldNotDeleteCategoriaWhenNotExists() {
        when(categoriaRepository.findById(categoria1.getId())).thenReturn(Optional.empty());

        boolean isDeleted = categoriaService.deleteCategoria(categoria1.getId());

        assertFalse(isDeleted);
        verify(categoriaRepository, never()).deleteById(anyLong());
    }

    @Test
    void shouldReadCategoriaWhenExists() {
        when(categoriaRepository.existsById(categoria1.getId())).thenReturn(true);
        when(categoriaRepository.findById(categoria1.getId())).thenReturn(Optional.of(categoria1));

        Categoria result = categoriaService.readCategoria(categoria1.getId());

        assertNotNull(result);
        assertEquals(categoria1.getId(), result.getId());
        verify(categoriaRepository, times(1)).existsById(categoria1.getId());
        verify(categoriaRepository, times(1)).findById(categoria1.getId());
    }

    @Test
    void shouldReturnNullWhenCategoriaNotExists() {
        when(categoriaRepository.existsById(categoria1.getId())).thenReturn(false);

        Categoria result = categoriaService.readCategoria(categoria1.getId());

        assertNull(result);
        verify(categoriaRepository, times(1)).existsById(categoria1.getId());
    }
}
