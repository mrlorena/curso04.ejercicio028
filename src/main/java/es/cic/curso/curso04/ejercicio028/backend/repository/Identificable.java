package es.cic.curso.curso04.ejercicio028.backend.repository;

import java.io.Serializable;

public interface Identificable<K> extends Serializable {

	K getId();

	void setId(K id);

}