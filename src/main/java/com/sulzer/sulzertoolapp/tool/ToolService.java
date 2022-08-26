package com.sulzer.sulzertoolapp.tool;

import com.sulzer.sulzertoolapp.exception.ApiRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ToolService {
    private final ToolRepository toolRepository;

    public Page<Tool> getTools(Pageable pageable) {
        return toolRepository.findAll(pageable);
    }

    public Tool getTool(Long id) {
        return toolRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Tool not found", HttpStatus.NOT_FOUND));
    }

    public Tool saveTool(Tool tool) {
        return toolRepository.save(tool);
    }

    public void deleteTool(Long id) {
        toolRepository.deleteById(id);
    }
}
