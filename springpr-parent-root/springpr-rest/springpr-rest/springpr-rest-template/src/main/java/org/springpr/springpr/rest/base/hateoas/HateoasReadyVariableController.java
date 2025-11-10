/* (C)2023 */
package org.springpr.springpr.rest.base.hateoas;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/variables")
public class HateoasReadyVariableController {

    @GetMapping("/{variableName}/{variableVersion}")
    ResponseEntity<EntityModel<Variable>> one(
            @PathVariable String variableName, @PathVariable Long variableVersion) {
        Variable result = new Variable(variableName, variableVersion);
        return ResponseEntity.ok(
                EntityModel.of(
                        result,
                        linkTo(
                                        methodOn(HateoasReadyVariableController.class)
                                                .one(variableName, variableVersion))
                                .withSelfRel(),
                        linkTo(methodOn(HateoasReadyVariableController.class).update(result))
                                .withRel("update"),
                        linkTo(methodOn(HateoasReadyVariableController.class).all(variableName))
                                .withRel("all")));
    }

    @GetMapping("/{variableName}")
    ResponseEntity<CollectionModel<EntityModel<Variable>>> all(@PathVariable String variableName) {
        Variable result1 = new Variable(variableName, 1);
        Variable result2 = new Variable(variableName, 2);
        Variable result3 = new Variable(variableName, 3);
        List<Variable> results = new ArrayList<>();
        results.add(result1);
        results.add(result2);
        results.add(result3);

        List<EntityModel<Variable>> resultList =
                StreamSupport.stream(results.spliterator(), false)
                        .map(
                                result ->
                                        EntityModel.of(
                                                result,
                                                linkTo(
                                                                methodOn(
                                                                                HateoasReadyVariableController
                                                                                        .class)
                                                                        .one(
                                                                                variableName,
                                                                                result
                                                                                        .getVariableVersion()))
                                                        .withSelfRel(),
                                                linkTo(
                                                                methodOn(
                                                                                HateoasReadyVariableController
                                                                                        .class)
                                                                        .all(variableName))
                                                        .withRel("orders")))
                        .collect(toList());

        return ResponseEntity.ok(
                CollectionModel.of(
                        resultList,
                        linkTo(methodOn(HateoasReadyVariableController.class).all(variableName))
                                .withSelfRel()));
    }

    @PutMapping()
    ResponseEntity<EntityModel<Variable>> update(@RequestBody Variable variable) {
        System.out.println("********************put variable:" + variable);

        variable.setVariableName(variable.getVariableName() + "a");
        variable.setVariableVersion(variable.getVariableVersion() + 100);
        // Link newlyCreatedLink = linkTo(methodOn(VariableController.class)
        // .one(variable.getVariableName(), variable.getVariableVersion())).withSelfRel();

        try {
            return ResponseEntity.ok(
                    EntityModel.of(
                            variable,
                            linkTo(
                                            methodOn(HateoasReadyVariableController.class)
                                                    .one(
                                                            variable.getVariableName(),
                                                            variable.getVariableVersion()))
                                    .withSelfRel(),
                            linkTo(methodOn(HateoasReadyVariableController.class).update(variable))
                                    .withRel("update"),
                            linkTo(
                                            methodOn(HateoasReadyVariableController.class)
                                                    .all(variable.getVariableName()))
                                    .withRel("all")));
            // return ResponseEntity.noContent().location(new
            // URI(newlyCreatedLink.getHref())).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(EntityModel.of(variable));
        }
    }

    @PostMapping()
    ResponseEntity<EntityModel<Variable>> create(@RequestBody Variable variable) {
        System.out.println("********************post variable:" + variable);

        try {
            EntityModel<Variable> varaibleSource =
                    EntityModel.of(
                            variable,
                            linkTo(
                                            methodOn(HateoasReadyVariableController.class)
                                                    .one(
                                                            variable.getVariableName(),
                                                            variable.getVariableVersion()))
                                    .withSelfRel(),
                            linkTo(methodOn(HateoasReadyVariableController.class).update(variable))
                                    .withRel("update"),
                            linkTo(
                                            methodOn(HateoasReadyVariableController.class)
                                                    .all(variable.getVariableName()))
                                    .withRel("all"));
            return ResponseEntity //
                    .created(
                            new URI(
                                    varaibleSource
                                            .getRequiredLink(IanaLinkRelations.SELF)
                                            .getHref())) //
                    .body(varaibleSource);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(EntityModel.of(variable));
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Variable {
        private String variableName;
        private long variableVersion;
    }
}
