package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.UserEntity;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<UserEntity, Integer> {
}
