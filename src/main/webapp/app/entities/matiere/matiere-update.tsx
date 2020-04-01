import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IFormateur } from 'app/shared/model/formateur.model';
import { getEntities as getFormateurs } from 'app/entities/formateur/formateur.reducer';
import { IModule } from 'app/shared/model/module.model';
import { getEntities as getModules } from 'app/entities/module/module.reducer';
import { getEntity, updateEntity, createEntity, reset } from './matiere.reducer';
import { IMatiere } from 'app/shared/model/matiere.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IMatiereUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const MatiereUpdate = (props: IMatiereUpdateProps) => {
  const [idsformateurs, setIdsformateurs] = useState([]);
  const [debutantMatieresId, setDebutantMatieresId] = useState('0');
  const [intermediaireMatieresId, setIntermediaireMatieresId] = useState('0');
  const [avanceMatieresId, setAvanceMatieresId] = useState('0');
  const [confirmeMatieresId, setConfirmeMatieresId] = useState('0');
  const [modulesId, setModulesId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { matiereEntity, formateurs, modules, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/matiere');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getFormateurs();
    props.getModules();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...matiereEntity,
        ...values,
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
          <h2 id="laFactoryApp.matiere.home.createOrEditLabel">Create or edit a Matiere</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : matiereEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="matiere-id">ID</Label>
                  <AvInput id="matiere-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nomLabel" for="matiere-nom">
                  Nom
                </Label>
                <AvField id="matiere-nom" type="text" name="nom" />
              </AvGroup>
              <AvGroup>
                <Label id="dureeLabel" for="matiere-duree">
                  Duree
                </Label>
                <AvField id="matiere-duree" type="string" className="form-control" name="duree" />
              </AvGroup>
              <AvGroup>
                <Label for="matiere-formateurs">Formateurs</Label>
                <AvInput
                  id="matiere-formateurs"
                  type="select"
                  multiple
                  className="form-control"
                  name="formateurs"
                  value={matiereEntity.formateurs && matiereEntity.formateurs.map(e => e.id)}
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
              <Button tag={Link} id="cancel-save" to="/matiere" replace color="info">
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
  formateurs: storeState.formateur.entities,
  modules: storeState.module.entities,
  matiereEntity: storeState.matiere.entity,
  loading: storeState.matiere.loading,
  updating: storeState.matiere.updating,
  updateSuccess: storeState.matiere.updateSuccess
});

const mapDispatchToProps = {
  getFormateurs,
  getModules,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MatiereUpdate);
