import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './module.reducer';
import { IModule } from 'app/shared/model/module.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IModuleDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ModuleDetail = (props: IModuleDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { moduleEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Module [<b>{moduleEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="nom">Nom</span>
          </dt>
          <dd>{moduleEntity.nom}</dd>
          <dt>
            <span id="dateDebut">Date Debut</span>
          </dt>
          <dd>
            <TextFormat value={moduleEntity.dateDebut} type="date" format={APP_LOCAL_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="dateFin">Date Fin</span>
          </dt>
          <dd>
            <TextFormat value={moduleEntity.dateFin} type="date" format={APP_LOCAL_DATE_FORMAT} />
          </dd>
          <dt>Matieres</dt>
          <dd>
            {moduleEntity.matieres
              ? moduleEntity.matieres.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {i === moduleEntity.matieres.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>Formateurs</dt>
          <dd>
            {moduleEntity.formateurs
              ? moduleEntity.formateurs.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {i === moduleEntity.formateurs.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/module" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/module/${moduleEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ module }: IRootState) => ({
  moduleEntity: module.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ModuleDetail);
