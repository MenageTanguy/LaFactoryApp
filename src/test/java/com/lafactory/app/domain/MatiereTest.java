package com.lafactory.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lafactory.app.web.rest.TestUtil;

public class MatiereTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Matiere.class);
        Matiere matiere1 = new Matiere();
        matiere1.setId(1L);
        Matiere matiere2 = new Matiere();
        matiere2.setId(matiere1.getId());
        assertThat(matiere1).isEqualTo(matiere2);
        matiere2.setId(2L);
        assertThat(matiere1).isNotEqualTo(matiere2);
        matiere1.setId(null);
        assertThat(matiere1).isNotEqualTo(matiere2);
    }
}
