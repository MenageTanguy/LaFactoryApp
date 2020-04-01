import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './formateur.reducer';
import { IFormateur } from 'app/shared/model/formateur.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFormateurDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const FormateurDetail = (props: IFormateurDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { formateurEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Formateur [<b>{formateurEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="nom">Nom</span>
          </dt>
          <dd>{formateurEntity.nom}</dd>
          <dt>
            <span id="prenom">Prenom</span>
          </dt>
          <dd>{formateurEntity.prenom}</dd>
          <dt>
            <span id="coordonnees">Coordonnees</span>
          </dt>
          <dd>{formateurEntity.coordonnees}</dd>
          <dt>
            <span id="numeroRue">Numero Rue</span>
          </dt>
          <dd>{formateurEntity.numeroRue}</dd>
          <dt>
            <span id="rue">Rue</span>
          </dt>
          <dd>{formateurEntity.rue}</dd>
          <dt>
            <span id="codePostal">Code Postal</span>
          </dt>
          <dd>{formateurEntity.codePostal}</dd>
          <dt>
            <span id="ville">Ville</span>
          </dt>
          <dd>{formateurEntity.ville}</dd>
          <dt>Matieres Debutant</dt>
          <dd>
            {formateurEntity.matieresDebutants
              ? formateurEntity.matieresDebutants.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {i === formateurEntity.matieresDebutants.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>Matieres Intermedaire</dt>
          <dd>
            {formateurEntity.matieresIntermedaires
              ? formateurEntity.matieresIntermedaires.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {i === formateurEntity.matieresIntermedaires.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>Matieres Avance</dt>
          <dd>
            {formateurEntity.matieresAvances
              ? formateurEntity.matieresAvances.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {i === formateurEntity.matieresAvances.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>Matieres Confirme</dt>
          <dd>
            {formateurEntity.matieresConfirmes
              ? formateurEntity.matieresConfirmes.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {i === formateurEntity.matieresConfirmes.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/formateur" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/formateur/${formateurEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ formateur }: IRootState) => ({
  formateurEntity: formateur.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(FormateurDetail);
