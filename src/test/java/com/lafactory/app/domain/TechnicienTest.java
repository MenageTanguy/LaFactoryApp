package com.lafactory.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lafactory.app.web.rest.TestUtil;

public class TechnicienTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Technicien.class);
        Technicien technicien1 = new Technicien();
        technicien1.setId(1L);
        Technicien technicien2 = new Technicien();
        technicien2.setId(technicien1.getId());
        assertThat(technicien1).isEqualTo(technicien2);
        technicien2.setId(2L);
        assertThat(technicien1).isNotEqualTo(technicien2);
        technicien1.setId(null);
        assertThat(technicien1).isNotEqualTo(technicien2);
    }
}
