import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './technicien.reducer';
import { ITechnicien } from 'app/shared/model/technicien.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITechnicienUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TechnicienUpdate = (props: ITechnicienUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { technicienEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/technicien');
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
        ...technicienEntity,
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
          <h2 id="laFactoryApp.technicien.home.createOrEditLabel">Create or edit a Technicien</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : technicienEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="technicien-id">ID</Label>
                  <AvInput id="technicien-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nomLabel" for="technicien-nom">
                  Nom
                </Label>
                <AvField id="technicien-nom" type="text" name="nom" />
              </AvGroup>
              <AvGroup>
                <Label id="prenomLabel" for="technicien-prenom">
                  Prenom
                </Label>
                <AvField id="technicien-prenom" type="text" name="prenom" />
              </AvGroup>
              <AvGroup>
                <Label id="coordonneesLabel" for="technicien-coordonnees">
                  Coordonnees
                </Label>
                <AvField id="technicien-coordonnees" type="text" name="coordonnees" />
              </AvGroup>
              <AvGroup>
                <Label id="numeroRueLabel" for="technicien-numeroRue">
                  Numero Rue
                </Label>
                <AvField id="technicien-numeroRue" type="string" className="form-control" name="numeroRue" />
              </AvGroup>
              <AvGroup>
                <Label id="rueLabel" for="technicien-rue">
                  Rue
                </Label>
                <AvField id="technicien-rue" type="text" name="rue" />
              </AvGroup>
              <AvGroup>
                <Label id="codePostalLabel" for="technicien-codePostal">
                  Code Postal
                </Label>
                <AvField id="technicien-codePostal" type="text" name="codePostal" />
              </AvGroup>
              <AvGroup>
                <Label id="villeLabel" for="technicien-ville">
                  Ville
                </Label>
                <AvField id="technicien-ville" type="text" name="ville" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/technicien" replace color="info">
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
  technicienEntity: storeState.technicien.entity,
  loading: storeState.technicien.loading,
  updating: storeState.technicien.updating,
  updateSuccess: storeState.technicien.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TechnicienUpdate);
