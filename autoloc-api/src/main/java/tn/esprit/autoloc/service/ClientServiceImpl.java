package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.repository.IClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class ClientServiceImpl extends GenericServiceImpl<Client, Long> implements IClientService {

    public ClientServiceImpl(IClientRepository repository) {
        super(repository);
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceImpl.class);
    @Override
    protected String getEntityName() {
        return "Client";
    }

    @Override
    protected Long getId(Client c) {
        return c.getIdClient();
    }

    @Override
    protected void setFields(Client source, Client target) {
        target.setNom(source.getNom());
        target.setPrenom(source.getPrenom());
        target.setEmail(source.getEmail());
        target.setTelephone(source.getTelephone());
        target.setNumPermis(source.getNumPermis());
        target.setDateInscription(source.getDateInscription());
    }

    @Override
    public boolean check(Client c, boolean isNew) {
        return c != null
                && Utils.isNotEmpty(c.getNom())
                && Utils.isNotEmpty(c.getPrenom())
                && Utils.isNotEmpty(c.getEmail());
    }

    // ---------- Méthodes ajoutées pour l'analyse ----------

    public boolean estValide(Client c) {
        return c.getNom() != null;
    }

    public void afficher(Client c) {
        LOGGER.info("Client : {}", c.getNom());
    }
}