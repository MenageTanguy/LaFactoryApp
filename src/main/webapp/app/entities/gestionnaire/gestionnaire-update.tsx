import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './gestionnaire.reducer';
import { IGestionnaire } from 'app/shared/model/gestionnaire.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IGestionnaireUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const GestionnaireUpdate = (props: IGestionnaireUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { gestionnaireEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/gestionnaire');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...gestionnaireEntity,
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
          <h2 id="laFactoryApp.gestionnaire.home.createOrEditLabel">Create or edit a Gestionnaire</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : gestionnaireEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="gestionnaire-id">ID</Label>
                  <AvInput id="gestionnaire-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nomLabel" for="gestionnaire-nom">
                  Nom
                </Label>
                <AvField id="gestionnaire-nom" type="text" name="nom" />
              </AvGroup>
              <AvGroup>
                <Label id="prenomLabel" for="gestionnaire-prenom">
                  Prenom
                </Label>
                <AvField id="gestionnaire-prenom" type="text" name="prenom" />
              </AvGroup>
              <AvGroup>
                <Label id="coordonneesLabel" for="gestionnaire-coordonnees">
                  Coordonnees
                </Label>
                <AvField id="gestionnaire-coordonnees" type="text" name="coordonnees" />
              </AvGroup>
              <AvGroup>
                <Label id="numeroRueLabel" for="gestionnaire-numeroRue">
                  Numero Rue
                </Label>
                <AvField id="gestionnaire-numeroRue" type="string" className="form-control" name="numeroRue" />
              </AvGroup>
              <AvGroup>
                <Label id="rueLabel" for="gestionnaire-rue">
                  Rue
                </Label>
                <AvField id="gestionnaire-rue" type="text" name="rue" />
              </AvGroup>
              <AvGroup>
                <Label id="codePostalLabel" for="gestionnaire-codePostal">
                  Code Postal
                </Label>
                <AvField id="gestionnaire-codePostal" type="text" name="codePostal" />
              </AvGroup>
              <AvGroup>
                <Label id="villeLabel" for="gestionnaire-ville">
                  Ville
                </Label>
                <AvField id="gestionnaire-ville" type="text" name="ville" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/gestionnaire" replace color="info">
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
  gestionnaireEntity: storeState.gestionnaire.entity,
  loading: storeState.gestionnaire.loading,
  updating: storeState.gestionnaire.updating,
  updateSuccess: storeState.gestionnaire.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(GestionnaireUpdate);
