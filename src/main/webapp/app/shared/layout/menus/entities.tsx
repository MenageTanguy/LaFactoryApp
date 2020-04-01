import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown icon="th-list" name="Entities" id="entity-menu" style={{ maxHeight: '80vh', overflow: 'auto' }}>
    <MenuItem icon="asterisk" to="/formateur">
      Formateur
    </MenuItem>
    <MenuItem icon="asterisk" to="/stagiaire">
      Stagiaire
    </MenuItem>
    <MenuItem icon="asterisk" to="/technicien">
      Technicien
    </MenuItem>
    <MenuItem icon="asterisk" to="/gestionnaire">
      Gestionnaire
    </MenuItem>
    <MenuItem icon="asterisk" to="/module">
      Module
    </MenuItem>
    <MenuItem icon="asterisk" to="/cursus">
      Cursus
    </MenuItem>
    <MenuItem icon="asterisk" to="/matiere">
      Matiere
    </MenuItem>
    <MenuItem icon="asterisk" to="/ordinateur">
      Ordinateur
    </MenuItem>
    <MenuItem icon="asterisk" to="/projecteur">
      Projecteur
    </MenuItem>
    <MenuItem icon="asterisk" to="/salle">
      Salle
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
