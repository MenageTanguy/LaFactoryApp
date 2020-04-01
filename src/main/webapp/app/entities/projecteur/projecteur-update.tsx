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
import { getEntity, updateEntity, createEntity, reset } from './projecteur.reducer';
import { IProjecteur } from 'app/shared/model/projecteur.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IProjecteurUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ProjecteurUpdate = (props: IProjecteurUpdateProps) => {
  const [salleId, setSalleId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { projecteurEntity, salles, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/projecteur');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getSalles();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...projecteurEntity,
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
          <h2 id="laFactoryApp.projecteur.home.createOrEditLabel">Create or edit a Projecteur</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : projecteurEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="projecteur-id">ID</Label>
                  <AvInput id="projecteur-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="codeLabel" for="projecteur-code">
                  Code
                </Label>
                <AvField id="projecteur-code" type="text" name="code" />
              </AvGroup>
              <AvGroup>
                <Label id="coutLabel" for="projecteur-cout">
                  Cout
                </Label>
                <AvField id="projecteur-cout" type="string" className="form-control" name="cout" />
              </AvGroup>
              <AvGroup>
                <Label id="stockLabel" for="projecteur-stock">
                  Stock
                </Label>
                <AvField id="projecteur-stock" type="string" className="form-control" name="stock" />
              </AvGroup>
              <AvGroup>
                <Label for="projecteur-salle">Salle</Label>
                <AvInput id="projecteur-salle" type="select" className="form-control" name="salle.id">
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
              <Button tag={Link} id="cancel-save" to="/projecteur" replace color="info">
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
  projecteurEntity: storeState.projecteur.entity,
  loading: storeState.projecteur.loading,
  updating: storeState.projecteur.updating,
  updateSuccess: storeState.projecteur.updateSuccess
});

const mapDispatchToProps = {
  getSalles,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProjecteurUpdate);
