import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Stagiaire from './stagiaire';
import StagiaireDetail from './stagiaire-detail';
import StagiaireUpdate from './stagiaire-update';
import StagiaireDeleteDialog from './stagiaire-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={StagiaireDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={StagiaireUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={StagiaireUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={StagiaireDetail} />
      <ErrorBoundaryRoute path={match.url} component={Stagiaire} />
    </Switch>
  </>
);

export default Routes;
