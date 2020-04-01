import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Gestionnaire from './gestionnaire';
import GestionnaireDetail from './gestionnaire-detail';
import GestionnaireUpdate from './gestionnaire-update';
import GestionnaireDeleteDialog from './gestionnaire-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={GestionnaireDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={GestionnaireUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={GestionnaireUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={GestionnaireDetail} />
      <ErrorBoundaryRoute path={match.url} component={Gestionnaire} />
    </Switch>
  </>
);

export default Routes;
