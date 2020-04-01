import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Formateur from './formateur';
import FormateurDetail from './formateur-detail';
import FormateurUpdate from './formateur-update';
import FormateurDeleteDialog from './formateur-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={FormateurDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FormateurUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FormateurUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FormateurDetail} />
      <ErrorBoundaryRoute path={match.url} component={Formateur} />
    </Switch>
  </>
);

export default Routes;
