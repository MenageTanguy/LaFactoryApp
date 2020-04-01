import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './module.reducer';
import { IModule } from 'app/shared/model/module.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IModuleProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Module = (props: IModuleProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { moduleList, match, loading } = props;
  return (
    <div>
      <h2 id="module-heading">
        Modules
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Module
        </Link>
      </h2>
      <div className="table-responsive">
        {moduleList && moduleList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Date Debut</th>
                <th>Date Fin</th>
                <th>Matieres</th>
                <th>Formateurs</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {moduleList.map((module, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${module.id}`} color="link" size="sm">
                      {module.id}
                    </Button>
                  </td>
                  <td>{module.nom}</td>
                  <td>
                    <TextFormat type="date" value={module.dateDebut} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={module.dateFin} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    {module.matieres
                      ? module.matieres.map((val, j) => (
                          <span key={j}>
                            <Link to={`matiere/${val.id}`}>{val.id}</Link>
                            {j === module.matieres.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td>
                    {module.formateurs
                      ? module.formateurs.map((val, j) => (
                          <span key={j}>
                            <Link to={`formateur/${val.id}`}>{val.id}</Link>
                            {j === module.formateurs.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${module.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${module.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${module.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Modules found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ module }: IRootState) => ({
  moduleList: module.entities,
  loading: module.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Module);
