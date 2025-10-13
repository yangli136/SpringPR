/* (C)2023 */
package dev.springpr.springpr.base.example.user.service;

import java.util.concurrent.CompletableFuture;

import dev.springpr.springpr.base.example.user.model.User;
import jakarta.validation.constraints.NotNull;

public interface UserService {
    String getAccount(@NotNull User user);

    CompletableFuture<String> getIdAndName(@NotNull User user);

    void asyncFailure(User user);

    User getUser(long id);

    User updateUser(long id);
}
