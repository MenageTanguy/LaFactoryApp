import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './matiere.reducer';
import { IMatiere } from 'app/shared/model/matiere.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMatiereProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Matiere = (props: IMatiereProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { matiereList, match, loading } = props;
  return (
    <div>
      <h2 id="matiere-heading">
        Matieres
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Matiere
        </Link>
      </h2>
      <div className="table-responsive">
        {matiereList && matiereList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Duree</th>
                <th>Formateurs</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {matiereList.map((matiere, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${matiere.id}`} color="link" size="sm">
                      {matiere.id}
                    </Button>
                  </td>
                  <td>{matiere.nom}</td>
                  <td>{matiere.duree}</td>
                  <td>
                    {matiere.formateurs
                      ? matiere.formateurs.map((val, j) => (
                          <span key={j}>
                            <Link to={`formateur/${val.id}`}>{val.id}</Link>
                            {j === matiere.formateurs.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${matiere.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${matiere.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${matiere.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Matieres found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ matiere }: IRootState) => ({
  matiereList: matiere.entities,
  loading: matiere.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Matiere);
