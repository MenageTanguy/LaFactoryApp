package com.lafactory.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lafactory.app.web.rest.TestUtil;

public class OrdinateurTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ordinateur.class);
        Ordinateur ordinateur1 = new Ordinateur();
        ordinateur1.setId(1L);
        Ordinateur ordinateur2 = new Ordinateur();
        ordinateur2.setId(ordinateur1.getId());
        assertThat(ordinateur1).isEqualTo(ordinateur2);
        ordinateur2.setId(2L);
        assertThat(ordinateur1).isNotEqualTo(ordinateur2);
        ordinateur1.setId(null);
        assertThat(ordinateur1).isNotEqualTo(ordinateur2);
    }
}
