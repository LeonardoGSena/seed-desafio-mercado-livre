package br.com.leonardo.adicionapergunta;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Emails {

    @Autowired
    private Mailer mailer;
    
    public void novaPergunta(@NotNull @Valid Pergunta novaPergunta) {
//        return new RestTemplate().postForEntity("https://api.mandril.com", mandrilMessage, String.class);
        mailer.send("<html>...</html>", "Nova Pergunta...", novaPergunta.getInteressado().getEmail(), "novapergunta@nossomercadolivre.com",novaPergunta.getDonoProduto().getEmail());
    }
}
