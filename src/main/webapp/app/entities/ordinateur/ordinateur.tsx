import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './ordinateur.reducer';
import { IOrdinateur } from 'app/shared/model/ordinateur.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOrdinateurProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Ordinateur = (props: IOrdinateurProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { ordinateurList, match, loading } = props;
  return (
    <div>
      <h2 id="ordinateur-heading">
        Ordinateurs
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Ordinateur
        </Link>
      </h2>
      <div className="table-responsive">
        {ordinateurList && ordinateurList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Code</th>
                <th>Cout</th>
                <th>Processeur</th>
                <th>Ram</th>
                <th>Dd</th>
                <th>Date Achat</th>
                <th>Stock</th>
                <th>Stagiaire</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {ordinateurList.map((ordinateur, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${ordinateur.id}`} color="link" size="sm">
                      {ordinateur.id}
                    </Button>
                  </td>
                  <td>{ordinateur.code}</td>
                  <td>{ordinateur.cout}</td>
                  <td>{ordinateur.processeur}</td>
                  <td>{ordinateur.ram}</td>
                  <td>{ordinateur.dd}</td>
                  <td>
                    <TextFormat type="date" value={ordinateur.dateAchat} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{ordinateur.stock}</td>
                  <td>{ordinateur.stagiaire ? <Link to={`stagiaire/${ordinateur.stagiaire.id}`}>{ordinateur.stagiaire.id}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${ordinateur.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${ordinateur.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${ordinateur.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Ordinateurs found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ ordinateur }: IRootState) => ({
  ordinateurList: ordinateur.entities,
  loading: ordinateur.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Ordinateur);
