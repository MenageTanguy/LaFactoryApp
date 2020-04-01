import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './formateur.reducer';
import { IFormateur } from 'app/shared/model/formateur.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFormateurProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Formateur = (props: IFormateurProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { formateurList, match, loading } = props;
  return (
    <div>
      <h2 id="formateur-heading">
        Formateurs
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Formateur
        </Link>
      </h2>
      <div className="table-responsive">
        {formateurList && formateurList.length > 0 ? (
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
                <th>Matieres Debutant</th>
                <th>Matieres Intermedaire</th>
                <th>Matieres Avance</th>
                <th>Matieres Confirme</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {formateurList.map((formateur, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${formateur.id}`} color="link" size="sm">
                      {formateur.id}
                    </Button>
                  </td>
                  <td>{formateur.nom}</td>
                  <td>{formateur.prenom}</td>
                  <td>{formateur.coordonnees}</td>
                  <td>{formateur.numeroRue}</td>
                  <td>{formateur.rue}</td>
                  <td>{formateur.codePostal}</td>
                  <td>{formateur.ville}</td>
                  <td>
                    {formateur.matieresDebutants
                      ? formateur.matieresDebutants.map((val, j) => (
                          <span key={j}>
                            <Link to={`matiere/${val.id}`}>{val.id}</Link>
                            {j === formateur.matieresDebutants.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td>
                    {formateur.matieresIntermedaires
                      ? formateur.matieresIntermedaires.map((val, j) => (
                          <span key={j}>
                            <Link to={`matiere/${val.id}`}>{val.id}</Link>
                            {j === formateur.matieresIntermedaires.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td>
                    {formateur.matieresAvances
                      ? formateur.matieresAvances.map((val, j) => (
                          <span key={j}>
                            <Link to={`matiere/${val.id}`}>{val.id}</Link>
                            {j === formateur.matieresAvances.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td>
                    {formateur.matieresConfirmes
                      ? formateur.matieresConfirmes.map((val, j) => (
                          <span key={j}>
                            <Link to={`matiere/${val.id}`}>{val.id}</Link>
                            {j === formateur.matieresConfirmes.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${formateur.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${formateur.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${formateur.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Formateurs found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ formateur }: IRootState) => ({
  formateurList: formateur.entities,
  loading: formateur.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Formateur);
