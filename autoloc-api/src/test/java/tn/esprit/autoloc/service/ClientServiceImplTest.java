package tn.esprit.autoloc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.IClientRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @Mock
    private IClientRepository repository;

    private ClientServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new ClientServiceImpl(repository);
    }

    private Client clientValide() {
        return Client.builder()
                .nom("Ben Ali")
                .prenom("Sami")
                .email("sami@mail.com")
                .build();
    }

    @Test
    void createClientValide() {
        Client c = clientValide();
        when(repository.save(c)).thenReturn(c);

        assertEquals("Ben Ali", service.create(c).getNom());
    }

    @Test
    void createClientInvalideLeveException() {
        Client c = Client.builder().nom("").build();

        assertThrows(IllegalArgumentException.class, () -> service.create(c));
        verify(repository, never()).save(any());
    }

    @Test
    void findByIdIntrouvable() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.findById(99L));
    }

    @Test
    void updateClient() {
        Client existing = clientValide();
        Client modif = clientValide();
        modif.setNom("Trabelsi");
        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(existing);

        assertEquals("Trabelsi", service.update(1L, modif).getNom());
    }

    @Test
    void deleteByIdIntrouvable() {
        when(repository.existsById(99L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> service.deleteById(99L));
    }

    @Test
    void deleteByIdExistant() {
        when(repository.existsById(1L)).thenReturn(true);

        service.deleteById(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void estValide() {
        assertTrue(service.estValide(clientValide()));
        assertFalse(service.estValide(new Client()));
    }
}