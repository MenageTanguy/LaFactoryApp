package com.lafactory.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lafactory.app.web.rest.TestUtil;

public class ProjecteurTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Projecteur.class);
        Projecteur projecteur1 = new Projecteur();
        projecteur1.setId(1L);
        Projecteur projecteur2 = new Projecteur();
        projecteur2.setId(projecteur1.getId());
        assertThat(projecteur1).isEqualTo(projecteur2);
        projecteur2.setId(2L);
        assertThat(projecteur1).isNotEqualTo(projecteur2);
        projecteur1.setId(null);
        assertThat(projecteur1).isNotEqualTo(projecteur2);
    }
}
