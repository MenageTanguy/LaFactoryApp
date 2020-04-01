import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IOrdinateur } from 'app/shared/model/ordinateur.model';
import { getEntities as getOrdinateurs } from 'app/entities/ordinateur/ordinateur.reducer';
import { ICursus } from 'app/shared/model/cursus.model';
import { getEntities as getCursuses } from 'app/entities/cursus/cursus.reducer';
import { getEntity, updateEntity, createEntity, reset } from './stagiaire.reducer';
import { IStagiaire } from 'app/shared/model/stagiaire.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IStagiaireUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const StagiaireUpdate = (props: IStagiaireUpdateProps) => {
  const [ordinateurId, setOrdinateurId] = useState('0');
  const [cursusId, setCursusId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { stagiaireEntity, ordinateurs, cursuses, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/stagiaire');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getOrdinateurs();
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
        ...stagiaireEntity,
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
          <h2 id="laFactoryApp.stagiaire.home.createOrEditLabel">Create or edit a Stagiaire</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : stagiaireEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="stagiaire-id">ID</Label>
                  <AvInput id="stagiaire-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nomLabel" for="stagiaire-nom">
                  Nom
                </Label>
                <AvField id="stagiaire-nom" type="text" name="nom" />
              </AvGroup>
              <AvGroup>
                <Label id="prenomLabel" for="stagiaire-prenom">
                  Prenom
                </Label>
                <AvField id="stagiaire-prenom" type="text" name="prenom" />
              </AvGroup>
              <AvGroup>
                <Label id="coordonneesLabel" for="stagiaire-coordonnees">
                  Coordonnees
                </Label>
                <AvField id="stagiaire-coordonnees" type="text" name="coordonnees" />
              </AvGroup>
              <AvGroup>
                <Label id="numeroRueLabel" for="stagiaire-numeroRue">
                  Numero Rue
                </Label>
                <AvField id="stagiaire-numeroRue" type="string" className="form-control" name="numeroRue" />
              </AvGroup>
              <AvGroup>
                <Label id="rueLabel" for="stagiaire-rue">
                  Rue
                </Label>
                <AvField id="stagiaire-rue" type="text" name="rue" />
              </AvGroup>
              <AvGroup>
                <Label id="codePostalLabel" for="stagiaire-codePostal">
                  Code Postal
                </Label>
                <AvField id="stagiaire-codePostal" type="text" name="codePostal" />
              </AvGroup>
              <AvGroup>
                <Label id="villeLabel" for="stagiaire-ville">
                  Ville
                </Label>
                <AvField id="stagiaire-ville" type="text" name="ville" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/stagiaire" replace color="info">
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
  ordinateurs: storeState.ordinateur.entities,
  cursuses: storeState.cursus.entities,
  stagiaireEntity: storeState.stagiaire.entity,
  loading: storeState.stagiaire.loading,
  updating: storeState.stagiaire.updating,
  updateSuccess: storeState.stagiaire.updateSuccess
});

const mapDispatchToProps = {
  getOrdinateurs,
  getCursuses,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(StagiaireUpdate);
