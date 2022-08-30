package com.sulzer.sulzertoolapp.tool;

import com.sulzer.sulzertoolapp.tool.validation.annotation.Unique;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Unique.List({
        @Unique(
                uniqueField = "toolNumber",
                idField = "id",
                message = "Tool Number must be unique"
        ),
        @Unique(
                uniqueField = "toolAtmsNumber",
                idField = "id",
                message = "Tool ATMS Number must be unique"
        )
})
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Tool Number is required")
    @Valid
    private String toolNumber;

    @NotNull(message = "Tool Class is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ToolClass toolClass;

    @NotNull(message = "Tool ATMS is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ToolAtms toolAtms;

    @NotBlank(message = "Tool ATMS Number is required")
    @Valid
    private String toolAtmsNumber;

    private Double diameter;

    private Double tipRadius;

    private Double length;

    private String comment;

    @Column(updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
