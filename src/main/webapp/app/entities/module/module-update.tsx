import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IMatiere } from 'app/shared/model/matiere.model';
import { getEntities as getMatieres } from 'app/entities/matiere/matiere.reducer';
import { IFormateur } from 'app/shared/model/formateur.model';
import { getEntities as getFormateurs } from 'app/entities/formateur/formateur.reducer';
import { ICursus } from 'app/shared/model/cursus.model';
import { getEntities as getCursuses } from 'app/entities/cursus/cursus.reducer';
import { getEntity, updateEntity, createEntity, reset } from './module.reducer';
import { IModule } from 'app/shared/model/module.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IModuleUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ModuleUpdate = (props: IModuleUpdateProps) => {
  const [idsmatieres, setIdsmatieres] = useState([]);
  const [idsformateurs, setIdsformateurs] = useState([]);
  const [cursusId, setCursusId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { moduleEntity, matieres, formateurs, cursuses, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/module');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getMatieres();
    props.getFormateurs();
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
        ...moduleEntity,
        ...values,
        matieres: mapIdList(values.matieres),
        formateurs: mapIdList(values.formateurs)
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
          <h2 id="laFactoryApp.module.home.createOrEditLabel">Create or edit a Module</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : moduleEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="module-id">ID</Label>
                  <AvInput id="module-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nomLabel" for="module-nom">
                  Nom
                </Label>
                <AvField id="module-nom" type="text" name="nom" />
              </AvGroup>
              <AvGroup>
                <Label id="dateDebutLabel" for="module-dateDebut">
                  Date Debut
                </Label>
                <AvField id="module-dateDebut" type="date" className="form-control" name="dateDebut" />
              </AvGroup>
              <AvGroup>
                <Label id="dateFinLabel" for="module-dateFin">
                  Date Fin
                </Label>
                <AvField id="module-dateFin" type="date" className="form-control" name="dateFin" />
              </AvGroup>
              <AvGroup>
                <Label for="module-matieres">Matieres</Label>
                <AvInput
                  id="module-matieres"
                  type="select"
                  multiple
                  className="form-control"
                  name="matieres"
                  value={moduleEntity.matieres && moduleEntity.matieres.map(e => e.id)}
                >
                  <option value="" key="0" />
                  {matieres
                    ? matieres.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="module-formateurs">Formateurs</Label>
                <AvInput
                  id="module-formateurs"
                  type="select"
                  multiple
                  className="form-control"
                  name="formateurs"
                  value={moduleEntity.formateurs && moduleEntity.formateurs.map(e => e.id)}
                >
                  <option value="" key="0" />
                  {formateurs
                    ? formateurs.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/module" replace color="info">
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
  matieres: storeState.matiere.entities,
  formateurs: storeState.formateur.entities,
  cursuses: storeState.cursus.entities,
  moduleEntity: storeState.module.entity,
  loading: storeState.module.loading,
  updating: storeState.module.updating,
  updateSuccess: storeState.module.updateSuccess
});

const mapDispatchToProps = {
  getMatieres,
  getFormateurs,
  getCursuses,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ModuleUpdate);
