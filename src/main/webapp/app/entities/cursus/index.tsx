import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Cursus from './cursus';
import CursusDetail from './cursus-detail';
import CursusUpdate from './cursus-update';
import CursusDeleteDialog from './cursus-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CursusDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CursusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CursusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CursusDetail} />
      <ErrorBoundaryRoute path={match.url} component={Cursus} />
    </Switch>
  </>
);

export default Routes;
