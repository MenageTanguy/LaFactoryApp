import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './cursus.reducer';
import { ICursus } from 'app/shared/model/cursus.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICursusDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CursusDetail = (props: ICursusDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { cursusEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Cursus [<b>{cursusEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="nom">Nom</span>
          </dt>
          <dd>{cursusEntity.nom}</dd>
          <dt>
            <span id="dateDebut">Date Debut</span>
          </dt>
          <dd>
            <TextFormat value={cursusEntity.dateDebut} type="date" format={APP_LOCAL_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="dateFin">Date Fin</span>
          </dt>
          <dd>
            <TextFormat value={cursusEntity.dateFin} type="date" format={APP_LOCAL_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="prerequis">Prerequis</span>
          </dt>
          <dd>{cursusEntity.prerequis}</dd>
          <dt>
            <span id="objectifs">Objectifs</span>
          </dt>
          <dd>{cursusEntity.objectifs}</dd>
          <dt>
            <span id="contenu">Contenu</span>
          </dt>
          <dd>{cursusEntity.contenu}</dd>
          <dt>Salle</dt>
          <dd>{cursusEntity.salle ? cursusEntity.salle.id : ''}</dd>
          <dt>Stagiaires</dt>
          <dd>
            {cursusEntity.stagiaires
              ? cursusEntity.stagiaires.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {i === cursusEntity.stagiaires.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>Modules</dt>
          <dd>
            {cursusEntity.modules
              ? cursusEntity.modules.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {i === cursusEntity.modules.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>Gestionnaire</dt>
          <dd>{cursusEntity.gestionnaire ? cursusEntity.gestionnaire.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/cursus" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/cursus/${cursusEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ cursus }: IRootState) => ({
  cursusEntity: cursus.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CursusDetail);
