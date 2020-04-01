import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IProjecteur } from 'app/shared/model/projecteur.model';
import { getEntities as getProjecteurs } from 'app/entities/projecteur/projecteur.reducer';
import { ICursus } from 'app/shared/model/cursus.model';
import { getEntities as getCursuses } from 'app/entities/cursus/cursus.reducer';
import { getEntity, updateEntity, createEntity, reset } from './salle.reducer';
import { ISalle } from 'app/shared/model/salle.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISalleUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SalleUpdate = (props: ISalleUpdateProps) => {
  const [projecteurId, setProjecteurId] = useState('0');
  const [cursusId, setCursusId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { salleEntity, projecteurs, cursuses, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/salle');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getProjecteurs();
    props.getCursuses();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...salleEntity,
        ...values
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="laFactoryApp.salle.home.createOrEditLabel">Create or edit a Salle</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : salleEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="salle-id">ID</Label>
                  <AvInput id="salle-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="codeLabel" for="salle-code">
                  Code
                </Label>
                <AvField id="salle-code" type="text" name="code" />
              </AvGroup>
              <AvGroup>
                <Label id="coutLabel" for="salle-cout">
                  Cout
                </Label>
                <AvField id="salle-cout" type="string" className="form-control" name="cout" />
              </AvGroup>
              <AvGroup>
                <Label id="capaciteMaxLabel" for="salle-capaciteMax">
                  Capacite Max
                </Label>
                <AvField id="salle-capaciteMax" type="string" className="form-control" name="capaciteMax" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/salle" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  projecteurs: storeState.projecteur.entities,
  cursuses: storeState.cursus.entities,
  salleEntity: storeState.salle.entity,
  loading: storeState.salle.loading,
  updating: storeState.salle.updating,
  updateSuccess: storeState.salle.updateSuccess
});

const mapDispatchToProps = {
  getProjecteurs,
  getCursuses,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SalleUpdate);
