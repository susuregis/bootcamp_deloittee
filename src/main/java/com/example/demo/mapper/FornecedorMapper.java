package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dto.FornecedorDTO;
import com.example.demo.model.Fornecedor;

/**
 * Mapper para converter entre Fornecedor e FornecedorDTO
 * Implementa EntityMapper seguindo SRP (Single Responsibility Principle)
 */
@Component
public class FornecedorMapper implements EntityMapper<Fornecedor, FornecedorDTO> {
    
    @Override
    public FornecedorDTO toDTO(Fornecedor entity) {
        if (entity == null) {
            return null;
        }
        
        FornecedorDTO dto = new FornecedorDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCnpj(entity.getCnpj());
        dto.setCpf(entity.getCpf());
        dto.setEmail(entity.getEmail());
        
        // Mapear contato info se existir
        if (entity.getContatoInfo() != null) {
            dto.setTelefone(entity.getContatoInfo().getTelefone());
        }
        
        // Mapear endereço se existir
        if (entity.getEndereco() != null) {
            dto.setEndereco(entity.getEndereco().getNumero());
            dto.setEnderecoCompleto(entity.getEndereco().getEnderecoCompleto());
            dto.setCidade(entity.getEndereco().getCidade());
            dto.setEstado(entity.getEndereco().getEstado());
            dto.setCep(entity.getEndereco().getCep());
        }
        
        return dto;
    }
    
    @Override
    public Fornecedor toEntity(FornecedorDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Fornecedor entity = new Fornecedor();
        // Inicializar objetos embutidos para evitar NPE
        entity.setContatoInfo(new com.example.demo.model.ContatoInfo());
        entity.setEndereco(new com.example.demo.model.Endereco());
        
        return updateEntityFromDTO(dto, entity);
    }
    
    @Override
    public Fornecedor updateEntityFromDTO(FornecedorDTO dto, Fornecedor entity) {
        if (dto == null) {
            return entity;
        }
        
        entity.setNome(dto.getNome());
        entity.setCnpj(dto.getCnpj());
        entity.setCpf(dto.getCpf());
        
        if (dto.getEmail() != null && !dto.getEmail().isBlank()) {
            entity.setEmail(dto.getEmail());
        }
        
        // Atualizar contato info
        if (dto.getTelefone() != null) {
            if (entity.getContatoInfo() == null) {
                entity.setContatoInfo(new com.example.demo.model.ContatoInfo());
            }
            entity.getContatoInfo().setTelefone(dto.getTelefone());
        }
        
        // Atualizar endereço
        if (dto.getEndereco() != null || dto.getCidade() != null || 
            dto.getEstado() != null || dto.getCep() != null) {
            if (entity.getEndereco() == null) {
                entity.setEndereco(new com.example.demo.model.Endereco());
            }
            // Usar endereço como rua/logradouro completo
            if (dto.getEndereco() != null && !dto.getEndereco().isBlank()) {
                String endereco = dto.getEndereco().trim();
                if (endereco.length() > 10) {
                    endereco = endereco.substring(0, 10);
                }
                entity.getEndereco().setNumero(endereco);
            }
            if (dto.getCidade() != null && !dto.getCidade().isBlank()) {
                entity.getEndereco().setCidade(dto.getCidade());
            }
            if (dto.getEstado() != null && !dto.getEstado().isBlank()) {
                entity.getEndereco().setEstado(dto.getEstado());
            }
            if (dto.getCep() != null && !dto.getCep().isBlank()) {
                entity.getEndereco().setCep(dto.getCep());
            }
        }
        
        return entity;
    }

    private String gerarCnpj() {
        String digits = java.util.UUID.randomUUID().toString().replaceAll("[^0-9]", "");
        if (digits.length() < 14) {
            digits = (digits + "00000000000000").substring(0, 14);
        }
        String cnpj = digits.substring(0, 2) + "." + digits.substring(2, 5) + "." + digits.substring(5, 8) + "/" + digits.substring(8, 12) + "-" + digits.substring(12, 14);
        return cnpj;
    }
    
    private String gerarCpf() {
        String digits = java.util.UUID.randomUUID().toString().replaceAll("[^0-9]", "");
        if (digits.length() < 11) {
            digits = (digits + "00000000000").substring(0, 11);
        }
        String cpf = digits.substring(0, 3) + "." + digits.substring(3, 6) + "." + digits.substring(6, 9) + "-" + digits.substring(9, 11);
        return cpf;
    }
}
