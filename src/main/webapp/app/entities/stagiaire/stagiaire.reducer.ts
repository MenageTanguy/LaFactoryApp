import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IStagiaire, defaultValue } from 'app/shared/model/stagiaire.model';

export const ACTION_TYPES = {
  FETCH_STAGIAIRE_LIST: 'stagiaire/FETCH_STAGIAIRE_LIST',
  FETCH_STAGIAIRE: 'stagiaire/FETCH_STAGIAIRE',
  CREATE_STAGIAIRE: 'stagiaire/CREATE_STAGIAIRE',
  UPDATE_STAGIAIRE: 'stagiaire/UPDATE_STAGIAIRE',
  DELETE_STAGIAIRE: 'stagiaire/DELETE_STAGIAIRE',
  RESET: 'stagiaire/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IStagiaire>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type StagiaireState = Readonly<typeof initialState>;

// Reducer

export default (state: StagiaireState = initialState, action): StagiaireState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_STAGIAIRE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_STAGIAIRE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_STAGIAIRE):
    case REQUEST(ACTION_TYPES.UPDATE_STAGIAIRE):
    case REQUEST(ACTION_TYPES.DELETE_STAGIAIRE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_STAGIAIRE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_STAGIAIRE):
    case FAILURE(ACTION_TYPES.CREATE_STAGIAIRE):
    case FAILURE(ACTION_TYPES.UPDATE_STAGIAIRE):
    case FAILURE(ACTION_TYPES.DELETE_STAGIAIRE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_STAGIAIRE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_STAGIAIRE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_STAGIAIRE):
    case SUCCESS(ACTION_TYPES.UPDATE_STAGIAIRE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_STAGIAIRE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/stagiaires';

// Actions

export const getEntities: ICrudGetAllAction<IStagiaire> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_STAGIAIRE_LIST,
  payload: axios.get<IStagiaire>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IStagiaire> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_STAGIAIRE,
    payload: axios.get<IStagiaire>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IStagiaire> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_STAGIAIRE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IStagiaire> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_STAGIAIRE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IStagiaire> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_STAGIAIRE,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
