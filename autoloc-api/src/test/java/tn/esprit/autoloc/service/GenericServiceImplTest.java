package tn.esprit.autoloc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GenericServiceImplTest {

    static class Item {
        Long id;
        String name;

        Item(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    static class ItemService extends GenericServiceImpl<Item, Long> {
        ItemService(JpaRepository<Item, Long> repository) {
            super(repository);
        }

        @Override
        protected String getEntityName() {
            return "Item";
        }

        @Override
        protected Long getId(Item entity) {
            return entity == null ? null : entity.id;
        }

        @Override
        protected void setFields(Item source, Item target) {
            target.name = source.name;
        }
    }

    @Mock
    private JpaRepository<Item, Long> repository;

    private ItemService service;

    @BeforeEach
    void setUp() {
        service = new ItemService(repository);
    }

    @Test
    void createEntiteValide() {
        Item item = new Item(null, "a");
        when(repository.save(item)).thenReturn(item);

        assertSame(item, service.create(item));
    }

    @Test
    void createAvecIdLeveException() {
        Item item = new Item(1L, "a");

        assertThrows(IllegalArgumentException.class, () -> service.create(item));
        verify(repository, never()).save(any());
    }

    @Test
    void createNullLeveException() {
        assertThrows(IllegalArgumentException.class, () -> service.create(null));
        verify(repository, never()).save(any());
    }

    @Test
    void findByIdExistant() {
        Item item = new Item(1L, "a");
        when(repository.findById(1L)).thenReturn(Optional.of(item));

        assertSame(item, service.findById(1L));
    }

    @Test
    void findByIdIntrouvable() {
        when(repository.findById(9L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.findById(9L));
    }

    @Test
    void findAllRetourneLaListe() {
        when(repository.findAll()).thenReturn(List.of(new Item(1L, "a"), new Item(2L, "b")));

        assertEquals(2, service.findAll().size());
    }

    @Test
    void updateCopieLesChamps() {
        Item existing = new Item(1L, "avant");
        Item modif = new Item(null, "apres");
        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(existing);

        Item result = service.update(1L, modif);

        assertEquals("apres", result.name);
    }

    @Test
    void updateIntrouvableLeveException() {
        when(repository.findById(9L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.update(9L, new Item(null, "x")));
    }

    @Test
    void deleteExistant() {
        when(repository.existsById(1L)).thenReturn(true);

        service.deleteById(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void deleteIntrouvableLeveException() {
        when(repository.existsById(9L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> service.deleteById(9L));
        verify(repository, never()).deleteById(any());
    }
}