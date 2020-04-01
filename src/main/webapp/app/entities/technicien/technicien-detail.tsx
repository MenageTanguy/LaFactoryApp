import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './technicien.reducer';
import { ITechnicien } from 'app/shared/model/technicien.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITechnicienDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TechnicienDetail = (props: ITechnicienDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { technicienEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Technicien [<b>{technicienEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="nom">Nom</span>
          </dt>
          <dd>{technicienEntity.nom}</dd>
          <dt>
            <span id="prenom">Prenom</span>
          </dt>
          <dd>{technicienEntity.prenom}</dd>
          <dt>
            <span id="coordonnees">Coordonnees</span>
          </dt>
          <dd>{technicienEntity.coordonnees}</dd>
          <dt>
            <span id="numeroRue">Numero Rue</span>
          </dt>
          <dd>{technicienEntity.numeroRue}</dd>
          <dt>
            <span id="rue">Rue</span>
          </dt>
          <dd>{technicienEntity.rue}</dd>
          <dt>
            <span id="codePostal">Code Postal</span>
          </dt>
          <dd>{technicienEntity.codePostal}</dd>
          <dt>
            <span id="ville">Ville</span>
          </dt>
          <dd>{technicienEntity.ville}</dd>
        </dl>
        <Button tag={Link} to="/technicien" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/technicien/${technicienEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ technicien }: IRootState) => ({
  technicienEntity: technicien.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TechnicienDetail);
