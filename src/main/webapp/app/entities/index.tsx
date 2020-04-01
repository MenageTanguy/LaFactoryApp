import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Formateur from './formateur';
import Stagiaire from './stagiaire';
import Technicien from './technicien';
import Gestionnaire from './gestionnaire';
import Module from './module';
import Cursus from './cursus';
import Matiere from './matiere';
import Ordinateur from './ordinateur';
import Projecteur from './projecteur';
import Salle from './salle';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}formateur`} component={Formateur} />
      <ErrorBoundaryRoute path={`${match.url}stagiaire`} component={Stagiaire} />
      <ErrorBoundaryRoute path={`${match.url}technicien`} component={Technicien} />
      <ErrorBoundaryRoute path={`${match.url}gestionnaire`} component={Gestionnaire} />
      <ErrorBoundaryRoute path={`${match.url}module`} component={Module} />
      <ErrorBoundaryRoute path={`${match.url}cursus`} component={Cursus} />
      <ErrorBoundaryRoute path={`${match.url}matiere`} component={Matiere} />
      <ErrorBoundaryRoute path={`${match.url}ordinateur`} component={Ordinateur} />
      <ErrorBoundaryRoute path={`${match.url}projecteur`} component={Projecteur} />
      <ErrorBoundaryRoute path={`${match.url}salle`} component={Salle} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
