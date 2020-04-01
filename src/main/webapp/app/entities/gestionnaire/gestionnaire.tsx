import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './gestionnaire.reducer';
import { IGestionnaire } from 'app/shared/model/gestionnaire.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IGestionnaireProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Gestionnaire = (props: IGestionnaireProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { gestionnaireList, match, loading } = props;
  return (
    <div>
      <h2 id="gestionnaire-heading">
        Gestionnaires
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Gestionnaire
        </Link>
      </h2>
      <div className="table-responsive">
        {gestionnaireList && gestionnaireList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Prenom</th>
                <th>Coordonnees</th>
                <th>Numero Rue</th>
                <th>Rue</th>
                <th>Code Postal</th>
                <th>Ville</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {gestionnaireList.map((gestionnaire, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${gestionnaire.id}`} color="link" size="sm">
                      {gestionnaire.id}
                    </Button>
                  </td>
                  <td>{gestionnaire.nom}</td>
                  <td>{gestionnaire.prenom}</td>
                  <td>{gestionnaire.coordonnees}</td>
                  <td>{gestionnaire.numeroRue}</td>
                  <td>{gestionnaire.rue}</td>
                  <td>{gestionnaire.codePostal}</td>
                  <td>{gestionnaire.ville}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${gestionnaire.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${gestionnaire.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${gestionnaire.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Gestionnaires found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ gestionnaire }: IRootState) => ({
  gestionnaireList: gestionnaire.entities,
  loading: gestionnaire.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Gestionnaire);
