import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Projecteur from './projecteur';
import ProjecteurDetail from './projecteur-detail';
import ProjecteurUpdate from './projecteur-update';
import ProjecteurDeleteDialog from './projecteur-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ProjecteurDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ProjecteurUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ProjecteurUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ProjecteurDetail} />
      <ErrorBoundaryRoute path={match.url} component={Projecteur} />
    </Switch>
  </>
);

export default Routes;
