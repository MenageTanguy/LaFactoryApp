import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './stagiaire.reducer';
import { IStagiaire } from 'app/shared/model/stagiaire.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IStagiaireDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const StagiaireDetail = (props: IStagiaireDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { stagiaireEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Stagiaire [<b>{stagiaireEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="nom">Nom</span>
          </dt>
          <dd>{stagiaireEntity.nom}</dd>
          <dt>
            <span id="prenom">Prenom</span>
          </dt>
          <dd>{stagiaireEntity.prenom}</dd>
          <dt>
            <span id="coordonnees">Coordonnees</span>
          </dt>
          <dd>{stagiaireEntity.coordonnees}</dd>
          <dt>
            <span id="numeroRue">Numero Rue</span>
          </dt>
          <dd>{stagiaireEntity.numeroRue}</dd>
          <dt>
            <span id="rue">Rue</span>
          </dt>
          <dd>{stagiaireEntity.rue}</dd>
          <dt>
            <span id="codePostal">Code Postal</span>
          </dt>
          <dd>{stagiaireEntity.codePostal}</dd>
          <dt>
            <span id="ville">Ville</span>
          </dt>
          <dd>{stagiaireEntity.ville}</dd>
        </dl>
        <Button tag={Link} to="/stagiaire" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/stagiaire/${stagiaireEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ stagiaire }: IRootState) => ({
  stagiaireEntity: stagiaire.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(StagiaireDetail);
