import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './salle.reducer';
import { ISalle } from 'app/shared/model/salle.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISalleDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SalleDetail = (props: ISalleDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { salleEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Salle [<b>{salleEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="code">Code</span>
          </dt>
          <dd>{salleEntity.code}</dd>
          <dt>
            <span id="cout">Cout</span>
          </dt>
          <dd>{salleEntity.cout}</dd>
          <dt>
            <span id="capaciteMax">Capacite Max</span>
          </dt>
          <dd>{salleEntity.capaciteMax}</dd>
        </dl>
        <Button tag={Link} to="/salle" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/salle/${salleEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ salle }: IRootState) => ({
  salleEntity: salle.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SalleDetail);
