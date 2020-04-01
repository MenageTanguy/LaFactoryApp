package com.lafactory.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lafactory.app.web.rest.TestUtil;

public class CursusTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cursus.class);
        Cursus cursus1 = new Cursus();
        cursus1.setId(1L);
        Cursus cursus2 = new Cursus();
        cursus2.setId(cursus1.getId());
        assertThat(cursus1).isEqualTo(cursus2);
        cursus2.setId(2L);
        assertThat(cursus1).isNotEqualTo(cursus2);
        cursus1.setId(null);
        assertThat(cursus1).isNotEqualTo(cursus2);
    }
}
