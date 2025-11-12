/* (C)2025 */
package dev.springpr.springpr.yugabytedb.crmd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.springpr.springpr.yugabytedb.crmd.model.Merchant;

public interface MerchantRepository extends JpaRepository<Merchant, String> {}
