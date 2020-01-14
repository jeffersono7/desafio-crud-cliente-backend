package br.com.surittec.cliente.resource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClienteResourceTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void criar() {
    }

    @Test
    void atualizar() {
    }

    @Test
    void obterTodos() {
    }

    @Test
    void obter() {
    }

    @Test
    void deletar() {
    }
}