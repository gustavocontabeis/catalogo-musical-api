package br.com.codersistemas.condominiosadm.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.codersistemas.condominiosadm.constantes.RabbitmqConstantes;
import br.com.codersistemas.condominiosadm.domain.Condominio;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CondominioConsumer {

	@RabbitListener(queues = RabbitmqConstantes.FILA_CONDOMINIO)
	private void consumidor(String mensagem) throws JsonProcessingException, InterruptedException {
		log.info("{}", mensagem);
		//Condominio condominio = new ObjectMapper().readValue(mensagem, Condominio.class);
		//log.info("{}", condominio);
	}
}
