import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Technicien from './technicien';
import TechnicienDetail from './technicien-detail';
import TechnicienUpdate from './technicien-update';
import TechnicienDeleteDialog from './technicien-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TechnicienDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TechnicienUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TechnicienUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TechnicienDetail} />
      <ErrorBoundaryRoute path={match.url} component={Technicien} />
    </Switch>
  </>
);

export default Routes;
