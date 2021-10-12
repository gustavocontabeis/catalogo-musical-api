package br.com.codersistemas.condominiosadm.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Documento {
	Long id;
	byte[] dados;
}
