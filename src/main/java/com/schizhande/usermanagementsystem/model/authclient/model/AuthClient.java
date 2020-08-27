package com.schizhande.usermanagementsystem.model.authclient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.*;
import java.util.stream.Collectors;

@Entity
//@Table(name = "oauth_client_details")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthClient implements ClientDetails {

    @Id
    @Column(name = "client_id")
    private String clientId;

    @Column(name = "client_secret")
    private String clientSecret;

    @Column(name = "authorized_grant_types")
    private String grantTypes;

    @Column(name = "access_token_validity")
    private Integer accessTokenValiditySeconds;

    @Column(name = "refresh_token_validity")
    private Integer refreshTokenValiditySeconds;

    @Column(name = "scope")
    private String scope;

    @Override
    public Set<String> getResourceIds() {
        return Collections.emptySet();
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public boolean isScoped() {
        return !this.scope.isEmpty();
    }

    @Override
    public Set<String> getScope() {
        return splitStringToSetEntries(this.scope, ",");
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return splitStringToSetEntries(this.grantTypes, ",");
    }

    private Set<String> splitStringToSetEntries(final String stringToSplit, String regex){
        return Arrays.stream(stringToSplit.split(regex)).map(String::trim)
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return Collections.emptySet();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Collections.emptySet();
    }

    @Override
    public boolean isAutoApprove(String s) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return new HashMap<>();
    }
}
