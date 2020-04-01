import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ISalle } from 'app/shared/model/salle.model';
import { getEntities as getSalles } from 'app/entities/salle/salle.reducer';
import { IStagiaire } from 'app/shared/model/stagiaire.model';
import { getEntities as getStagiaires } from 'app/entities/stagiaire/stagiaire.reducer';
import { IModule } from 'app/shared/model/module.model';
import { getEntities as getModules } from 'app/entities/module/module.reducer';
import { IGestionnaire } from 'app/shared/model/gestionnaire.model';
import { getEntities as getGestionnaires } from 'app/entities/gestionnaire/gestionnaire.reducer';
import { getEntity, updateEntity, createEntity, reset } from './cursus.reducer';
import { ICursus } from 'app/shared/model/cursus.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICursusUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CursusUpdate = (props: ICursusUpdateProps) => {
  const [idsstagiaires, setIdsstagiaires] = useState([]);
  const [idsmodules, setIdsmodules] = useState([]);
  const [salleId, setSalleId] = useState('0');
  const [gestionnaireId, setGestionnaireId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { cursusEntity, salles, stagiaires, modules, gestionnaires, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/cursus');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getSalles();
    props.getStagiaires();
    props.getModules();
    props.getGestionnaires();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...cursusEntity,
        ...values,
        stagiaires: mapIdList(values.stagiaires),
        modules: mapIdList(values.modules)
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
          <h2 id="laFactoryApp.cursus.home.createOrEditLabel">Create or edit a Cursus</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : cursusEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="cursus-id">ID</Label>
                  <AvInput id="cursus-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nomLabel" for="cursus-nom">
                  Nom
                </Label>
                <AvField id="cursus-nom" type="text" name="nom" />
              </AvGroup>
              <AvGroup>
                <Label id="dateDebutLabel" for="cursus-dateDebut">
                  Date Debut
                </Label>
                <AvField id="cursus-dateDebut" type="date" className="form-control" name="dateDebut" />
              </AvGroup>
              <AvGroup>
                <Label id="dateFinLabel" for="cursus-dateFin">
                  Date Fin
                </Label>
                <AvField id="cursus-dateFin" type="date" className="form-control" name="dateFin" />
              </AvGroup>
              <AvGroup>
                <Label id="prerequisLabel" for="cursus-prerequis">
                  Prerequis
                </Label>
                <AvField id="cursus-prerequis" type="text" name="prerequis" />
              </AvGroup>
              <AvGroup>
                <Label id="objectifsLabel" for="cursus-objectifs">
                  Objectifs
                </Label>
                <AvField id="cursus-objectifs" type="text" name="objectifs" />
              </AvGroup>
              <AvGroup>
                <Label id="contenuLabel" for="cursus-contenu">
                  Contenu
                </Label>
                <AvField id="cursus-contenu" type="text" name="contenu" />
              </AvGroup>
              <AvGroup>
                <Label for="cursus-salle">Salle</Label>
                <AvInput id="cursus-salle" type="select" className="form-control" name="salle.id">
                  <option value="" key="0" />
                  {salles
                    ? salles.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="cursus-stagiaires">Stagiaires</Label>
                <AvInput
                  id="cursus-stagiaires"
                  type="select"
                  multiple
                  className="form-control"
                  name="stagiaires"
                  value={cursusEntity.stagiaires && cursusEntity.stagiaires.map(e => e.id)}
                >
                  <option value="" key="0" />
                  {stagiaires
                    ? stagiaires.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="cursus-modules">Modules</Label>
                <AvInput
                  id="cursus-modules"
                  type="select"
                  multiple
                  className="form-control"
                  name="modules"
                  value={cursusEntity.modules && cursusEntity.modules.map(e => e.id)}
                >
                  <option value="" key="0" />
                  {modules
                    ? modules.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="cursus-gestionnaire">Gestionnaire</Label>
                <AvInput id="cursus-gestionnaire" type="select" className="form-control" name="gestionnaire.id">
                  <option value="" key="0" />
                  {gestionnaires
                    ? gestionnaires.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/cursus" replace color="info">
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
  salles: storeState.salle.entities,
  stagiaires: storeState.stagiaire.entities,
  modules: storeState.module.entities,
  gestionnaires: storeState.gestionnaire.entities,
  cursusEntity: storeState.cursus.entity,
  loading: storeState.cursus.loading,
  updating: storeState.cursus.updating,
  updateSuccess: storeState.cursus.updateSuccess
});

const mapDispatchToProps = {
  getSalles,
  getStagiaires,
  getModules,
  getGestionnaires,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CursusUpdate);
