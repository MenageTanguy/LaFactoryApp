import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IStagiaire } from 'app/shared/model/stagiaire.model';
import { getEntities as getStagiaires } from 'app/entities/stagiaire/stagiaire.reducer';
import { getEntity, updateEntity, createEntity, reset } from './ordinateur.reducer';
import { IOrdinateur } from 'app/shared/model/ordinateur.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IOrdinateurUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const OrdinateurUpdate = (props: IOrdinateurUpdateProps) => {
  const [stagiaireId, setStagiaireId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { ordinateurEntity, stagiaires, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/ordinateur');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getStagiaires();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...ordinateurEntity,
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
          <h2 id="laFactoryApp.ordinateur.home.createOrEditLabel">Create or edit a Ordinateur</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : ordinateurEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="ordinateur-id">ID</Label>
                  <AvInput id="ordinateur-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="codeLabel" for="ordinateur-code">
                  Code
                </Label>
                <AvField id="ordinateur-code" type="text" name="code" />
              </AvGroup>
              <AvGroup>
                <Label id="coutLabel" for="ordinateur-cout">
                  Cout
                </Label>
                <AvField id="ordinateur-cout" type="string" className="form-control" name="cout" />
              </AvGroup>
              <AvGroup>
                <Label id="processeurLabel" for="ordinateur-processeur">
                  Processeur
                </Label>
                <AvField id="ordinateur-processeur" type="text" name="processeur" />
              </AvGroup>
              <AvGroup>
                <Label id="ramLabel" for="ordinateur-ram">
                  Ram
                </Label>
                <AvField id="ordinateur-ram" type="string" className="form-control" name="ram" />
              </AvGroup>
              <AvGroup>
                <Label id="ddLabel" for="ordinateur-dd">
                  Dd
                </Label>
                <AvField id="ordinateur-dd" type="string" className="form-control" name="dd" />
              </AvGroup>
              <AvGroup>
                <Label id="dateAchatLabel" for="ordinateur-dateAchat">
                  Date Achat
                </Label>
                <AvField id="ordinateur-dateAchat" type="date" className="form-control" name="dateAchat" />
              </AvGroup>
              <AvGroup>
                <Label id="stockLabel" for="ordinateur-stock">
                  Stock
                </Label>
                <AvField id="ordinateur-stock" type="string" className="form-control" name="stock" />
              </AvGroup>
              <AvGroup>
                <Label for="ordinateur-stagiaire">Stagiaire</Label>
                <AvInput id="ordinateur-stagiaire" type="select" className="form-control" name="stagiaire.id">
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
              <Button tag={Link} id="cancel-save" to="/ordinateur" replace color="info">
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
  stagiaires: storeState.stagiaire.entities,
  ordinateurEntity: storeState.ordinateur.entity,
  loading: storeState.ordinateur.loading,
  updating: storeState.ordinateur.updating,
  updateSuccess: storeState.ordinateur.updateSuccess
});

const mapDispatchToProps = {
  getStagiaires,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(OrdinateurUpdate);
