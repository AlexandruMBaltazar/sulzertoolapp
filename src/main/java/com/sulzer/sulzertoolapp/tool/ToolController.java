package com.sulzer.sulzertoolapp.tool;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/tools")
@RequiredArgsConstructor
public class ToolController {
    private final ToolService toolService;

    @GetMapping
    public Page<Tool> getTools(Pageable pageable) {
        return toolService.getTools(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tool> getTool(@PathVariable Long id) {
        Tool tool = toolService.getTool(id);
        return ResponseEntity.ok(tool);
    }

    @PostMapping
    public ResponseEntity<Tool> saveTool(@Valid @RequestBody Tool tool) {
        Tool savedTool = toolService.saveTool(tool);
        return ResponseEntity.ok(savedTool);
    }

    @DeleteMapping("/{id}")
    public void deleteTool(@PathVariable Long id) {
        toolService.deleteTool(id);
    }
}
