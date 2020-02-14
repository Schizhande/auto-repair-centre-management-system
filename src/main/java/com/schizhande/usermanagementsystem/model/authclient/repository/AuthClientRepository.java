package com.schizhande.usermanagementsystem.model.authclient.repository;


import com.schizhande.usermanagementsystem.dao.jpa.BaseRepository;
import com.schizhande.usermanagementsystem.model.authclient.model.AuthClient;

import java.util.Optional;


public interface AuthClientRepository extends BaseRepository<AuthClient> {

    Optional<AuthClient> findByClientId(String clientId);

}
