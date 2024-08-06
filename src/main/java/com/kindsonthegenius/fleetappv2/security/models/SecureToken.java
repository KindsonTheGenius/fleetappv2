package com.kindsonthegenius.fleetappv2.security.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "secureTokens")
public class SecureToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String token;

    @Column(updatable = false)
    @Basic(optional = false)
    private LocalDateTime expiredAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(LocalDateTime expireAt) {
        this.expiredAt = expireAt;
    }

    public boolean isExpired() {
        return getExpiredAt().isBefore(LocalDateTime.now()); // this is generic implementation, you can always make it timezone specific
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
