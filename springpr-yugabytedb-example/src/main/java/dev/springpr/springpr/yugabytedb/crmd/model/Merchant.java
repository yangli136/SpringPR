/* (C)2025 */
package dev.springpr.springpr.yugabytedb.crmd.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.databind.JsonNode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "merchant")
@Data
public class Merchant {
    @Id
    @Column(name = "mer_id")
    private String merId;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "doc", columnDefinition = "jsonb")
    private JsonNode document;

    @Column(name = "creat_ts")
    private LocalDateTime createTimestamp;

    @Column(name = "lst_updt_ts")
    private LocalDateTime lastUpdateTimestamp;
}
