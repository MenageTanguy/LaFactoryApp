import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './matiere.reducer';
import { IMatiere } from 'app/shared/model/matiere.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMatiereDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const MatiereDetail = (props: IMatiereDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { matiereEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Matiere [<b>{matiereEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="nom">Nom</span>
          </dt>
          <dd>{matiereEntity.nom}</dd>
          <dt>
            <span id="duree">Duree</span>
          </dt>
          <dd>{matiereEntity.duree}</dd>
          <dt>Formateurs</dt>
          <dd>
            {matiereEntity.formateurs
              ? matiereEntity.formateurs.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {i === matiereEntity.formateurs.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/matiere" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/matiere/${matiereEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ matiere }: IRootState) => ({
  matiereEntity: matiere.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MatiereDetail);
