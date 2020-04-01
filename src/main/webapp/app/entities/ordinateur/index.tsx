import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Ordinateur from './ordinateur';
import OrdinateurDetail from './ordinateur-detail';
import OrdinateurUpdate from './ordinateur-update';
import OrdinateurDeleteDialog from './ordinateur-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={OrdinateurDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={OrdinateurUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={OrdinateurUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={OrdinateurDetail} />
      <ErrorBoundaryRoute path={match.url} component={Ordinateur} />
    </Switch>
  </>
);

export default Routes;
