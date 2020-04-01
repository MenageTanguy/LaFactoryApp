import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './gestionnaire.reducer';
import { IGestionnaire } from 'app/shared/model/gestionnaire.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IGestionnaireDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const GestionnaireDetail = (props: IGestionnaireDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { gestionnaireEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Gestionnaire [<b>{gestionnaireEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="nom">Nom</span>
          </dt>
          <dd>{gestionnaireEntity.nom}</dd>
          <dt>
            <span id="prenom">Prenom</span>
          </dt>
          <dd>{gestionnaireEntity.prenom}</dd>
          <dt>
            <span id="coordonnees">Coordonnees</span>
          </dt>
          <dd>{gestionnaireEntity.coordonnees}</dd>
          <dt>
            <span id="numeroRue">Numero Rue</span>
          </dt>
          <dd>{gestionnaireEntity.numeroRue}</dd>
          <dt>
            <span id="rue">Rue</span>
          </dt>
          <dd>{gestionnaireEntity.rue}</dd>
          <dt>
            <span id="codePostal">Code Postal</span>
          </dt>
          <dd>{gestionnaireEntity.codePostal}</dd>
          <dt>
            <span id="ville">Ville</span>
          </dt>
          <dd>{gestionnaireEntity.ville}</dd>
        </dl>
        <Button tag={Link} to="/gestionnaire" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/gestionnaire/${gestionnaireEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ gestionnaire }: IRootState) => ({
  gestionnaireEntity: gestionnaire.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(GestionnaireDetail);
