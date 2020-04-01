import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './ordinateur.reducer';
import { IOrdinateur } from 'app/shared/model/ordinateur.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOrdinateurDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const OrdinateurDetail = (props: IOrdinateurDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { ordinateurEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Ordinateur [<b>{ordinateurEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="code">Code</span>
          </dt>
          <dd>{ordinateurEntity.code}</dd>
          <dt>
            <span id="cout">Cout</span>
          </dt>
          <dd>{ordinateurEntity.cout}</dd>
          <dt>
            <span id="processeur">Processeur</span>
          </dt>
          <dd>{ordinateurEntity.processeur}</dd>
          <dt>
            <span id="ram">Ram</span>
          </dt>
          <dd>{ordinateurEntity.ram}</dd>
          <dt>
            <span id="dd">Dd</span>
          </dt>
          <dd>{ordinateurEntity.dd}</dd>
          <dt>
            <span id="dateAchat">Date Achat</span>
          </dt>
          <dd>
            <TextFormat value={ordinateurEntity.dateAchat} type="date" format={APP_LOCAL_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="stock">Stock</span>
          </dt>
          <dd>{ordinateurEntity.stock}</dd>
          <dt>Stagiaire</dt>
          <dd>{ordinateurEntity.stagiaire ? ordinateurEntity.stagiaire.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/ordinateur" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/ordinateur/${ordinateurEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ ordinateur }: IRootState) => ({
  ordinateurEntity: ordinateur.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(OrdinateurDetail);
