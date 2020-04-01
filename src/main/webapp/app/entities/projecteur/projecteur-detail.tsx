import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './projecteur.reducer';
import { IProjecteur } from 'app/shared/model/projecteur.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProjecteurDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ProjecteurDetail = (props: IProjecteurDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { projecteurEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Projecteur [<b>{projecteurEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="code">Code</span>
          </dt>
          <dd>{projecteurEntity.code}</dd>
          <dt>
            <span id="cout">Cout</span>
          </dt>
          <dd>{projecteurEntity.cout}</dd>
          <dt>
            <span id="stock">Stock</span>
          </dt>
          <dd>{projecteurEntity.stock}</dd>
          <dt>Salle</dt>
          <dd>{projecteurEntity.salle ? projecteurEntity.salle.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/projecteur" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/projecteur/${projecteurEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ projecteur }: IRootState) => ({
  projecteurEntity: projecteur.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProjecteurDetail);
