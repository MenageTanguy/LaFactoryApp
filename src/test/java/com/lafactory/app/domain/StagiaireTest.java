package com.lafactory.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lafactory.app.web.rest.TestUtil;

public class StagiaireTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Stagiaire.class);
        Stagiaire stagiaire1 = new Stagiaire();
        stagiaire1.setId(1L);
        Stagiaire stagiaire2 = new Stagiaire();
        stagiaire2.setId(stagiaire1.getId());
        assertThat(stagiaire1).isEqualTo(stagiaire2);
        stagiaire2.setId(2L);
        assertThat(stagiaire1).isNotEqualTo(stagiaire2);
        stagiaire1.setId(null);
        assertThat(stagiaire1).isNotEqualTo(stagiaire2);
    }
}
