import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './cursus.reducer';
import { ICursus } from 'app/shared/model/cursus.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICursusProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Cursus = (props: ICursusProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { cursusList, match, loading } = props;
  return (
    <div>
      <h2 id="cursus-heading">
        Cursuses
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Cursus
        </Link>
      </h2>
      <div className="table-responsive">
        {cursusList && cursusList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Date Debut</th>
                <th>Date Fin</th>
                <th>Prerequis</th>
                <th>Objectifs</th>
                <th>Contenu</th>
                <th>Salle</th>
                <th>Stagiaires</th>
                <th>Modules</th>
                <th>Gestionnaire</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {cursusList.map((cursus, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${cursus.id}`} color="link" size="sm">
                      {cursus.id}
                    </Button>
                  </td>
                  <td>{cursus.nom}</td>
                  <td>
                    <TextFormat type="date" value={cursus.dateDebut} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={cursus.dateFin} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{cursus.prerequis}</td>
                  <td>{cursus.objectifs}</td>
                  <td>{cursus.contenu}</td>
                  <td>{cursus.salle ? <Link to={`salle/${cursus.salle.id}`}>{cursus.salle.id}</Link> : ''}</td>
                  <td>
                    {cursus.stagiaires
                      ? cursus.stagiaires.map((val, j) => (
                          <span key={j}>
                            <Link to={`stagiaire/${val.id}`}>{val.id}</Link>
                            {j === cursus.stagiaires.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td>
                    {cursus.modules
                      ? cursus.modules.map((val, j) => (
                          <span key={j}>
                            <Link to={`module/${val.id}`}>{val.id}</Link>
                            {j === cursus.modules.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td>{cursus.gestionnaire ? <Link to={`gestionnaire/${cursus.gestionnaire.id}`}>{cursus.gestionnaire.id}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${cursus.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${cursus.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${cursus.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Cursuses found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ cursus }: IRootState) => ({
  cursusList: cursus.entities,
  loading: cursus.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Cursus);
