package com.luiza.cadastro_usuario.business;

import com.luiza.cadastro_usuario.infrastructure.entitys.Animal;
import com.luiza.cadastro_usuario.infrastructure.repository.AnimalRepository;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

    private final AnimalRepository repository;

    public AnimalService(AnimalRepository repository) {
        this.repository = repository;
    }

    public void salvarAnimal(Animal animal) {
        repository.saveAndFlush(animal);
    }

    public Animal buscarAnimalPorNome(String nome) {
        return repository.findByNome(nome).orElseThrow(
                () -> new RuntimeException("Animal não encontrado")
        );
    }

    public void deletarAnimalPorNome(String nome) {
        repository.deleteByNome(nome);
    }

    public void atualizarAnimalPorId(Integer id, Animal animal) {
        Animal animalAntigo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));

        Animal animalAtualizado = Animal.builder()
                .id(animalAntigo.getId())
                .nome(animal.getNome() != null ? animal.getNome() : animalAntigo.getNome())
                .raca(animal.getRaca() != null ? animal.getRaca() : animalAntigo.getRaca())
                .idade(animal.getIdade() != null ? animal.getIdade() : animalAntigo.getIdade())
                .build();

        repository.saveAndFlush(animalAtualizado);
    }
}
