package com.lafactory.app.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.lafactory.app.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.lafactory.app.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.lafactory.app.domain.User.class.getName());
            createCache(cm, com.lafactory.app.domain.Authority.class.getName());
            createCache(cm, com.lafactory.app.domain.User.class.getName() + ".authorities");
            createCache(cm, com.lafactory.app.domain.Formateur.class.getName());
            createCache(cm, com.lafactory.app.domain.Formateur.class.getName() + ".matieresDebutants");
            createCache(cm, com.lafactory.app.domain.Formateur.class.getName() + ".matieresIntermedaires");
            createCache(cm, com.lafactory.app.domain.Formateur.class.getName() + ".matieresAvances");
            createCache(cm, com.lafactory.app.domain.Formateur.class.getName() + ".matieresConfirmes");
            createCache(cm, com.lafactory.app.domain.Formateur.class.getName() + ".matieres");
            createCache(cm, com.lafactory.app.domain.Formateur.class.getName() + ".modules");
            createCache(cm, com.lafactory.app.domain.Stagiaire.class.getName());
            createCache(cm, com.lafactory.app.domain.Stagiaire.class.getName() + ".cursuses");
            createCache(cm, com.lafactory.app.domain.Technicien.class.getName());
            createCache(cm, com.lafactory.app.domain.Gestionnaire.class.getName());
            createCache(cm, com.lafactory.app.domain.Gestionnaire.class.getName() + ".cursuses");
            createCache(cm, com.lafactory.app.domain.Module.class.getName());
            createCache(cm, com.lafactory.app.domain.Module.class.getName() + ".matieres");
            createCache(cm, com.lafactory.app.domain.Module.class.getName() + ".formateurs");
            createCache(cm, com.lafactory.app.domain.Module.class.getName() + ".cursuses");
            createCache(cm, com.lafactory.app.domain.Cursus.class.getName());
            createCache(cm, com.lafactory.app.domain.Cursus.class.getName() + ".stagiaires");
            createCache(cm, com.lafactory.app.domain.Cursus.class.getName() + ".modules");
            createCache(cm, com.lafactory.app.domain.Matiere.class.getName());
            createCache(cm, com.lafactory.app.domain.Matiere.class.getName() + ".formateurs");
            createCache(cm, com.lafactory.app.domain.Matiere.class.getName() + ".modules");
            createCache(cm, com.lafactory.app.domain.Matiere.class.getName() + ".debutantMatieres");
            createCache(cm, com.lafactory.app.domain.Matiere.class.getName() + ".intermediaireMatieres");
            createCache(cm, com.lafactory.app.domain.Matiere.class.getName() + ".avanceMatieres");
            createCache(cm, com.lafactory.app.domain.Matiere.class.getName() + ".confirmeMatieres");
            createCache(cm, com.lafactory.app.domain.Ordinateur.class.getName());
            createCache(cm, com.lafactory.app.domain.Projecteur.class.getName());
            createCache(cm, com.lafactory.app.domain.Salle.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

}
