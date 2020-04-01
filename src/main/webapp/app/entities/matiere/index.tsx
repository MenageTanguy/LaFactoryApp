import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Matiere from './matiere';
import MatiereDetail from './matiere-detail';
import MatiereUpdate from './matiere-update';
import MatiereDeleteDialog from './matiere-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={MatiereDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={MatiereUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={MatiereUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={MatiereDetail} />
      <ErrorBoundaryRoute path={match.url} component={Matiere} />
    </Switch>
  </>
);

export default Routes;
