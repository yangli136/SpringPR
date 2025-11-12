/* (C)2025 */
package dev.springpr.springpr.rest.example.employee.web;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import dev.springpr.springpr.rest.example.employee.internal.Application;

@RestController
@RequestMapping(path = "/api/v1/admin")
@Validated
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    @PostMapping("/restart")
    public ResponseEntity<String> restart() {
        Application.restart();
        return ResponseEntity.ok("success.");
    }
}
