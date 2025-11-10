/* (C)2023 */
package org.springpr.springpr.base.example.user.service;

import java.util.concurrent.CompletableFuture;

import jakarta.validation.constraints.NotNull;

import org.springpr.springpr.base.example.user.model.User;

public interface UserService {
    String getAccount(@NotNull User user);

    CompletableFuture<String> getIdAndName(@NotNull User user);

    void asyncFailure(User user);

    User getUser(long id);

    User updateUser(long id);
}
