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
import { IModule } from 'app/shared/model/module.model';
import { getEntities as getModules } from 'app/entities/module/module.reducer';
import { getEntity, updateEntity, createEntity, reset } from './formateur.reducer';
import { IFormateur } from 'app/shared/model/formateur.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IFormateurUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const FormateurUpdate = (props: IFormateurUpdateProps) => {
  const [idsmatieresDebutant, setIdsmatieresDebutant] = useState([]);
  const [idsmatieresIntermedaire, setIdsmatieresIntermedaire] = useState([]);
  const [idsmatieresAvance, setIdsmatieresAvance] = useState([]);
  const [idsmatieresConfirme, setIdsmatieresConfirme] = useState([]);
  const [matieresId, setMatieresId] = useState('0');
  const [modulesId, setModulesId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { formateurEntity, matieres, modules, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/formateur');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getMatieres();
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
        ...formateurEntity,
        ...values,
        matieresDebutants: mapIdList(values.matieresDebutants),
        matieresIntermedaires: mapIdList(values.matieresIntermedaires),
        matieresAvances: mapIdList(values.matieresAvances),
        matieresConfirmes: mapIdList(values.matieresConfirmes)
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
          <h2 id="laFactoryApp.formateur.home.createOrEditLabel">Create or edit a Formateur</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : formateurEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="formateur-id">ID</Label>
                  <AvInput id="formateur-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nomLabel" for="formateur-nom">
                  Nom
                </Label>
                <AvField id="formateur-nom" type="text" name="nom" />
              </AvGroup>
              <AvGroup>
                <Label id="prenomLabel" for="formateur-prenom">
                  Prenom
                </Label>
                <AvField id="formateur-prenom" type="text" name="prenom" />
              </AvGroup>
              <AvGroup>
                <Label id="coordonneesLabel" for="formateur-coordonnees">
                  Coordonnees
                </Label>
                <AvField id="formateur-coordonnees" type="text" name="coordonnees" />
              </AvGroup>
              <AvGroup>
                <Label id="numeroRueLabel" for="formateur-numeroRue">
                  Numero Rue
                </Label>
                <AvField id="formateur-numeroRue" type="string" className="form-control" name="numeroRue" />
              </AvGroup>
              <AvGroup>
                <Label id="rueLabel" for="formateur-rue">
                  Rue
                </Label>
                <AvField id="formateur-rue" type="text" name="rue" />
              </AvGroup>
              <AvGroup>
                <Label id="codePostalLabel" for="formateur-codePostal">
                  Code Postal
                </Label>
                <AvField id="formateur-codePostal" type="text" name="codePostal" />
              </AvGroup>
              <AvGroup>
                <Label id="villeLabel" for="formateur-ville">
                  Ville
                </Label>
                <AvField id="formateur-ville" type="text" name="ville" />
              </AvGroup>
              <AvGroup>
                <Label for="formateur-matieresDebutant">Matieres Debutant</Label>
                <AvInput
                  id="formateur-matieresDebutant"
                  type="select"
                  multiple
                  className="form-control"
                  name="matieresDebutants"
                  value={formateurEntity.matieresDebutants && formateurEntity.matieresDebutants.map(e => e.id)}
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
                <Label for="formateur-matieresIntermedaire">Matieres Intermedaire</Label>
                <AvInput
                  id="formateur-matieresIntermedaire"
                  type="select"
                  multiple
                  className="form-control"
                  name="matieresIntermedaires"
                  value={formateurEntity.matieresIntermedaires && formateurEntity.matieresIntermedaires.map(e => e.id)}
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
                <Label for="formateur-matieresAvance">Matieres Avance</Label>
                <AvInput
                  id="formateur-matieresAvance"
                  type="select"
                  multiple
                  className="form-control"
                  name="matieresAvances"
                  value={formateurEntity.matieresAvances && formateurEntity.matieresAvances.map(e => e.id)}
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
                <Label for="formateur-matieresConfirme">Matieres Confirme</Label>
                <AvInput
                  id="formateur-matieresConfirme"
                  type="select"
                  multiple
                  className="form-control"
                  name="matieresConfirmes"
                  value={formateurEntity.matieresConfirmes && formateurEntity.matieresConfirmes.map(e => e.id)}
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
              <Button tag={Link} id="cancel-save" to="/formateur" replace color="info">
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
  modules: storeState.module.entities,
  formateurEntity: storeState.formateur.entity,
  loading: storeState.formateur.loading,
  updating: storeState.formateur.updating,
  updateSuccess: storeState.formateur.updateSuccess
});

const mapDispatchToProps = {
  getMatieres,
  getModules,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(FormateurUpdate);
