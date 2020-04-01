import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './technicien.reducer';
import { ITechnicien } from 'app/shared/model/technicien.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITechnicienProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Technicien = (props: ITechnicienProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { technicienList, match, loading } = props;
  return (
    <div>
      <h2 id="technicien-heading">
        Techniciens
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Technicien
        </Link>
      </h2>
      <div className="table-responsive">
        {technicienList && technicienList.length > 0 ? (
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
              {technicienList.map((technicien, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${technicien.id}`} color="link" size="sm">
                      {technicien.id}
                    </Button>
                  </td>
                  <td>{technicien.nom}</td>
                  <td>{technicien.prenom}</td>
                  <td>{technicien.coordonnees}</td>
                  <td>{technicien.numeroRue}</td>
                  <td>{technicien.rue}</td>
                  <td>{technicien.codePostal}</td>
                  <td>{technicien.ville}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${technicien.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${technicien.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${technicien.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Techniciens found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ technicien }: IRootState) => ({
  technicienList: technicien.entities,
  loading: technicien.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Technicien);
