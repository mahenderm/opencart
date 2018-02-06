package com.centurylink.pctl.mod.user.domain.user;

import com.centurylink.pctl.mod.core.model.user.LoggedUser;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the User entity.
 */
public interface PctlUserRepository extends MongoRepository<LoggedUser, String> {

    LoggedUser findOneByEmail(String email);

    LoggedUser findOneByLogin(String login);

    LoggedUser findOneByFirstName(String name);

    LoggedUser findOneByFingerPrint(String name);

     @Override
    void delete(LoggedUser t);

}
