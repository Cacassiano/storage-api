package dev.cacassiano.sistema_de_estoque.adapters.services;

import java.time.LocalDateTime;
import java.util.List;

import dev.cacassiano.sistema_de_estoque.adapters.DTOs.batch.BatchDetailsDTO;
import dev.cacassiano.sistema_de_estoque.adapters.DTOs.batch.BatchRequestDTO;
import dev.cacassiano.sistema_de_estoque.entities.Batch;
import dev.cacassiano.sistema_de_estoque.handlers.exceptions.NotFoundException;

public interface BatchService {
    public Batch create(Batch batch);
    public void delete(Long batch_id) throws NotFoundException;
    public Batch update(Long batch_id, BatchRequestDTO requestDTO) throws NotFoundException;
    
    public Batch get(Long batch_id) throws NotFoundException;
    List<Batch> getExpiratedBatchesBetween(LocalDateTime minDate, LocalDateTime maxDate);
    List<Batch> getAllExpiredBatches(LocalDateTime maxDate);
    public BatchDetailsDTO getBatchDetails(Long batch_id) throws NotFoundException;
}
